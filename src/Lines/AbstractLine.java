package Lines;

/**
 * Created by erik on 4/25/17.
 */

import Core.Pixel;
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

    protected Point p0;
    protected Point p1;

    public AbstractLine() {
        pixel = new Pixel();
        setLineWidth(1);
        setLineType((byte)0);
        scaleWidth = 1.0;
        scaleHeight = 1.0;
    }

    /**
     * Makes all the operations to draw a 2D line, from obtaining the pendent
     * to using a line algorithm to calculate the coordinates of each pixel
     * @param p0 the origin 2D point
     * @param p1 the origin 2D point
     */
    public void drawLine(Point p0, Point p1) {
        this.p0 = new Point(p0);
        this.p1 = new Point(p1);

       //Scale the points before drawing
        scale();

        //Obtain the pendent
        setPendent();

        //draw the line
        drawingMethod();
    }

    public void drawRectangle(Point p0, Point p1) {
        drawLine(p0, p1);
    }

    /**
     * Abstract method which consists in the algorithm to draw
     * the line.
     * It's main purpose is to offer flexibility to the implementer
     * and incite the use of polymorphism.
     */
    public abstract void drawingMethod();

    /**
     * Calculates the pendent of the line, useful for drawing lines
     * with width.
     */
    private void setPendent() {
        double x0tmp = p0.getX(), x1tmp = p1.getX(), y0tmp = p0.getY(), y1tmp = p1.getY();
        if(p1.getY() - p0.getY() == 0) {
            double tmp = x0tmp;
            x0tmp = y0tmp;
            y0tmp = tmp;
            tmp = x1tmp;
            x1tmp = y1tmp;
            y1tmp = tmp;
        }
        try {
            if(p1.getX() - p0.getX() != 0 && p1.getY() - p0.getY() == 0) {
                m = 1;
            } else
                m = ((p1.getX() - p0.getX()) == 0 && (p1.getY() - p0.getY()) == 0) ? -100000 : Math.round((x1tmp - x0tmp) / (y1tmp - y0tmp));
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
        LinkedList<Point> scaledPoints;
        Scale scale = new Scale();
        scaledPoints = scale.scale(p0, p1, scaleWidth, scaleHeight);
        p0 = scaledPoints.getFirst();
        p1 = scaledPoints.getLast();
    }

    public void setScale(double width, double height) {
        this.scaleWidth = width;
        this.scaleHeight = height;
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
