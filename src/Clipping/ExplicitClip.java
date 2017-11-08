package Clipping;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Erik on 7/7/2017.
 */
public class ExplicitClip {
    private int left;
    private int right;
    private int top;
    private int bottom;
    private boolean clipStatus;   // true = done, false = not done

    private LinkedList<Point> points;

    /**
     * Constructor that gets all the points of the figures,
     * used to know where to cut
     * @param pointVector a list of points (lines)
     */
    public ExplicitClip(LinkedList<Point> pointVector) {
        points = pointVector;
        clipStatus = true;
    }

    /**
     * Set every value from the clip area
     * left is the lower x value
     * right is the highest x value
     * top is the lower y value
     * bottom is the highest y value
     * @param p0  Starting point of the rectangle
     * @param p1  Ending point of the rectangle
     */
    public void setClipArea(Point p0, Point p1) {
        left = p0.getX() < p1.getX() ? (int)p0.getX() : (int)p1.getX();
        right = p0.getX() > p1.getX() ? (int)p0.getX() : (int)p1.getX();
        top = p0.getY() < p1.getY() ? (int)p0.getY() : (int)p1.getY();
        bottom = p0.getY() > p1.getY() ? (int)p0.getY() : (int)p1.getY();
    }

    /**
     * This method is used to clip all the lines in the point list.
     * It evaluates 2 points at time, getting it's region codes
     * and with them evaluates how the line needs to be clipped.
     */
    public void clip() {
        for(int i = 0, j = 1; j < points.size(); i += 2, j += 2) {
            String[] regionCodes = new String[2];
            regionCodes[0] = getRegionCode(points.get(i));
            regionCodes[1] = getRegionCode(points.get(j));

            if(regionCodes[0].equals("0000") && regionCodes[1].equals("0000")) {
                System.out.println("Totally Visible");
                continue;
            }  // The line is totally inside the clipping area
                if(!getANDCodes(regionCodes[0], regionCodes[1]).equals("0000")) {
                System.out.println("Totally Invisible");
                points.get(i).setLocation(new Point(-1, -1));
                points.get(j).setLocation(new Point(-1, -1));
                continue;
            }  // The line is totally outside the clipping area

            double deltaX = points.get(j).getX() - points.get(i).getX();
            double deltaY = points.get(j).getY() - points.get(i).getY();
            double slope = 0;
            try {
                slope = deltaX != 0 ? deltaY / deltaX : 0;
            } catch (ArithmeticException e) {
                e.printStackTrace();
            }

            //-----------------For the first Point----------------------
            int x_i = -1, y_i = -1;
            if (regionCodes[0].charAt(3) == '1') { //left
                x_i = left;
                y_i = (int)points.get(i).getY() + (int)Math.round(slope * (left - points.get(i).getX()));
            }
            else if(regionCodes[0].charAt(2) == '1') {  //right
                x_i = right;
                y_i = (int)points.get(i).getY() + (int)Math.round(slope * (right - points.get(i).getX()));
            }
            else if(regionCodes[0].charAt(1) == '1') {  //bottom
                int divisionValue;
                if(slope == 0) {
                    divisionValue = 0;
                } else {
                    divisionValue = (int) Math.round((bottom - points.get(i).getY()) / slope);
                }
                x_i = (int)points.get(i).getX() + divisionValue;
                y_i = bottom;
            }
            else if(regionCodes[0].charAt(0) == '1') {  //top
                int divisionValue;
                if(slope == 0) {
                    divisionValue = 0;
                } // to avoid getting -1 as a result of the
                // round method because of dividing by 0
                else {
                    divisionValue = (int) Math.round((top - points.get(i).getY()) / slope);
                }
                x_i = (int)points.get(i).getX() + divisionValue;
                y_i = top;
            }
            // --------------End of the first Point------------------
            // --------------For the second Point------------------
            int x_j = -1, y_j = -1;
            if (regionCodes[1].charAt(3) == '1') { //left
                x_j = left;
                y_j = (int)points.get(j).getY() + (int)Math.round(slope * (left - points.get(j).getX()));
            }
            else if(regionCodes[1].charAt(2) == '1') {  //right
                x_j = right;
                y_j = (int)points.get(j).getY() + (int)Math.round(slope * (right - points.get(j).getX()));
            }
            else if(regionCodes[1].charAt(1) == '1') {  //bottom
                int divisionValue;
                if(slope == 0) {
                    divisionValue = 0;
                } else {
                    divisionValue = (int) Math.round((bottom - points.get(j).getY()) / slope);
                }

                x_j = (int)points.get(j).getX() + divisionValue;
                y_j = bottom;
            }
            else if(regionCodes[1].charAt(0) == '1') {  //top
                int divisionValue;
                if(slope == 0) {
                    divisionValue = 0;
                }  // to avoid getting -1 as a result of the
                   // round method because of dividing by 0
                else {
                    divisionValue = (int) Math.round((top - points.get(j).getY()) / slope);
                }

                x_j = (int)points.get(j).getX() + divisionValue;
                y_j = top;
            }
            // --------------End of the second Point------------------

            if(x_i != -1 || y_i != -1) {
                points.get(i).setLocation(new Point(x_i, y_i));
                if(x_i < left || x_i > right || y_i < top || y_i > bottom) {
                    clipStatus = false;
                }   //check if any region in the first point is unfinished
            }  // the x and y value of the line needs to be updated
            if(x_j != -1 || y_j != -1) {
                points.get(j).setLocation(new Point(x_j, y_j));
                if(x_j < left || x_j > right || y_j < top || y_j > bottom) {
                    clipStatus = false;
                }  //check if any region in the second point is unfinished
            }   // the x and y value of the line needs to be updated


            if(!clipStatus) {
                i -= 2;
                j -= 2;
                clipStatus = true;
            }   // if any region was left unclipped, go back to
               // the same point and keep clipping regions

        }
    }

    /**
     * Uses a string to store the location of each point
     * using Cohen Sutherland region code
     * @param point the point to evaluate
     * @return a string with the point region code
     */
    private String getRegionCode(Point point) {
        StringBuilder regionCode = new StringBuilder("0000");
        if(point.getX() < left) {
            regionCode.setCharAt(3, '1');
        } else if(point.getX() > right){
            regionCode.setCharAt(2, '1');
        }
        if(point.getY() > bottom) {
            regionCode.setCharAt(1, '1');
        } else if(point.getY() < top){
            regionCode.setCharAt(0, '1');
        }
        return regionCode.toString();
    }

    /**
     * Compares two region code strings with AND operator
     * to check if the line of whether totally visible,
     * totally invisible or partially visible
     * @param code1 the first point code
     * @param code2 the second point code
     * @return the AND comparision of the strings
     */
    private String getANDCodes(String code1, String code2) {
        StringBuilder regionCode = new StringBuilder("0000");
        for(int i = 0; i < 4; i++) {
            if(code1.charAt(i) == '1' && code2.charAt(i) == '1') {
                regionCode.setCharAt(i, '1');
            }
        }
        return regionCode.toString();
    }

}
