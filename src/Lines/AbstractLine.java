package Lines;

/**
 * Created by erik on 4/25/17.
 */

import Core.Pixel;
import Core.Point3D;
import Projection.PerspectiveProjection;
import Projection.ParallelProjection;
import Projection.Projection;
import Transformations.Scale;

import java.awt.*;
import java.util.LinkedList;

/**
 * A class that manages the process of drawing a line,
 * offers flexibility to select the algorithm to be used
 * to get the coordinates of each pixel of the line
 */
public abstract class AbstractLine {
    protected Pixel pixel;
    protected byte lineType;   // 0 continuous, 1 discontinuous, 2+ custom
    protected boolean[] mask;
    protected int lineWidth;
    protected double m;
    protected double scaleWidth;
    protected double scaleHeight;
    protected double scaleDetph;
    protected String projectionType;
    protected Point3D projectionAdjustment;


    protected Point3D p0_3D;
    protected Point3D p1_3D;
    protected Point p0_2D;
    protected Point p1_2D;

    public AbstractLine() {
        pixel = new Pixel();
        setLineWidth(1);
        setLineType((byte)0);
        scaleWidth = 1.0;
        scaleHeight = 1.0;
        scaleDetph = 1.0;
        projectionType = "perspective";
        projectionAdjustment = new Point3D(-1070, -1070, -1070);
    }


    /**
     * Makes all the operations to draw a 3D line, from obtaining the points
     * to project them in a 2D plane.
     * @param p0 the origin 3D point
     * @param p1 the origin 3D point
     */
    public void drawLine3D(Point3D p0, Point3D p1) {
        //assign primitives from 3D space
        this.p0_3D = new Point3D(p0);
        this.p1_3D = new Point3D(p1);

        //set clip area according to view volume

        //Scale the points before drawing
        scale();

        //Project to the plane
        project();

        //draw the line in a 2D space
        drawLine();
    }

    /**
     * Makes all the operations to draw a 2D line, from obtaining the pendent
     * to using a line algorithm to calculate the coordinates of each pixel
     * Used for drawing 3D lines
     */
    public void drawLine() {
        //Obtain the pendent
        setPendent();

        //draw the line
        drawingMethod();
    }

    /**
     * Makes all the operations to draw a 2D line, from obtaining the pendent
     * to using a line algorithm to calculate the coordinates of each pixel
     * @param p0 the origin 2D point
     * @param p1 the origin 2D point
     */
    public void drawLine(Point p0, Point p1) {
        this.p0_2D = new Point(p0);
        this.p1_2D = new Point(p1);

       //Scale the points before drawing, not needed anymore as already scaled in 3D
//        scale();

        //Obtain the pendent
        setPendent();

        //draw the line
        drawingMethod();
    }

//    public void drawRectangle(Point p0, Point p1) {
//        drawLine();
//    }

    /**
     * Abstract method which consists in the algorithm to draw
     * the line.
     * It's main purpose is to offer flexibility to the implementer
     * and incite the use of polymorphism.
     */
    protected abstract void drawingMethod();

    /**
     * Calculates the pendent of the line, useful for drawing lines
     * with width.
     */
    private void setPendent() {
        double x0tmp = p0_2D.getX(), x1tmp = p1_2D.getX(), y0tmp = p0_2D.getY(), y1tmp = p1_2D.getY();
        if(p1_2D.getY() - p0_2D.getY() == 0) {
            double tmp = x0tmp;
            x0tmp = y0tmp;
            y0tmp = tmp;
            tmp = x1tmp;
            x1tmp = y1tmp;
            y1tmp = tmp;
        }
        try {
            if(p1_2D.getX() - p0_2D.getX() != 0 && p1_2D.getY() - p0_2D.getY() == 0) {
                m = 1;
            } else
                m = ((p1_2D.getX() - p0_2D.getX()) == 0 && (p1_2D.getY() - p0_2D.getY()) == 0) ? -100000 : Math.round((x1tmp - x0tmp) / (y1tmp - y0tmp));
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the pixel color
     * @param color and RGB color
     */
    public void setColor(Color color) {
        pixel.setPixelColor(color);
    }

    public Color getColor() {
        return pixel.getPixelColor();
    }

    /**
     * With help of the pendent calculates the position of
     * each pixel when drawing lines with width.
     * @param x current x pixel position
     * @param y current y pixel position
     */
    protected void drawWithWidth(int x, int y) {
        int xk = x, yk = y;
        for(int i = 1; i <= lineWidth; i++) {
            if(m == -100000) {      //is pixel
                for(int j = 1; j <= lineWidth; j++) {
                    pixel.drawPixel(xk, yk);
                    xk = j % 2 == 0 ? xk + j : xk - j;
                }
                xk = x;
                yk = i % 2 == 0 ? yk + i : yk - i;
            }
            else if(m == 0) {
                pixel.drawPixel(xk, yk);
                xk = i % 2 == 0 ? xk + i : xk - i;
            } else if(m < 1 || m == 1) {
                pixel.drawPixel(xk, yk);
                yk = i % 2 == 0 ? yk + i : yk - i;
            } else if (m > 1) {
                pixel.drawPixel(xk, yk);
                xk = i % 2 == 0 ? xk + i : xk - i;
                yk = i % 2 == 0 ? yk + i : yk - i;
            }
        }
        //pixel.drawPixel(x, y);
    }

    /**
     * Creates custom line types using masking
     * @param lineLength the length of the line
     */
    protected void createMaskByType(int lineLength) {
        switch (lineType) {
            case 0:
                mask = new boolean[lineLength];
                for(int i = 0; i < mask.length; i++) {
                    mask[i] = true;
                }
                break;
            case 1:
                mask = new boolean[lineLength];
                for(int i = 0; i < mask.length; i++) {
                    if(!(i > (mask.length / 3) && i < (mask.length / 3 * 2)))
                        mask[i] = true;
                }
                break;
            case 2:
                mask = new boolean[lineLength];
                for(int i = 0; i < mask.length; i++) {
                    if(i % 2 == 0) {
                        mask[i] = true;
                    }
                }
                break;
            default:
                System.out.println("This mask has not been implemented, using continuous one");
                mask = new boolean[lineLength];
                for(int i = 0; i < mask.length; i++) {
                    mask[i] = true;
                }
                break;
        }
    }

    protected void scale() {
        LinkedList<Point3D> scaledPoints;
        Scale scale = new Scale();
        scaledPoints = scale.scale(p0_3D, p1_3D, scaleWidth, scaleHeight, scaleDetph);
        p0_3D = scaledPoints.getFirst();
        p1_3D = scaledPoints.getLast();
    }

    protected void project() {
        LinkedList<Point> projectedPoints;
        Projection perspective;
        if(projectionType.equals("parallel")) {
            perspective = new ParallelProjection();
        } else {
            perspective = new PerspectiveProjection();
        }

        projectedPoints = perspective.project(p0_3D, p1_3D, projectionAdjustment);
        p0_2D = projectedPoints.getFirst();
        p1_2D = projectedPoints.getLast();
    }

    public void setProjectionType(String type) {
        projectionType = type;
    }

    public void setProjectionAdjustment(Point3D adjustment) {
        this.projectionAdjustment = adjustment;
    }

    public void setScale(double width, double height, double depth) {
        this.scaleWidth = width;
        this.scaleHeight = height;
        this.scaleDetph = depth;
    }

    public byte getLineType() {
        return lineType;
    }

    /**
     * Set the type of the line (continuous or discontinuous)
     * @param lineType the line type
     */
    public void setLineType(byte lineType) {
        this.lineType = lineType;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    /**
     * How thick the line will be
     * @param lineWidth the thickness of the line
     */
    public void setLineWidth(int lineWidth) {
        if(lineWidth <= 0) {
            System.out.println("Can't set line to 0 or lower, using deafult one value \" 1\"");
            lineWidth = 1;
        }
        this.lineWidth = lineWidth;
    }

    public void setGraphics(Graphics g) {
        pixel.setGraphics(g);
    }
}
