package Figures;

import Core.Pixel;
import Lines.AbstractLine;
import Transformations.Scale;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by erik on 4/25/17.
 */
public abstract class AbstractCircle {
    protected Pixel pixel;
    protected Point pc;
    protected Point radius;
    protected int lineWidth;
    protected double scaleWidth;
    protected double scaleHeight;

    public AbstractCircle() {
        pixel = new Pixel();
        setLineWidth(1);
//        setLineType((byte)0);
        scaleWidth = 1.0;
        scaleHeight = 1.0;
    }

    public void drawCircle(Point pc, Point radius) {
        this.pc = new Point(pc);
        this.radius = new Point(radius);
        scale();
        drawingMethod();
    }

    public void setColor(Color color) {
        pixel.setPixelColor(color);
    }

    protected void drawWithWidth(int xc, int yc) {
        int original_xc = xc;
        int original_yc = yc;
        if(lineWidth == 1) {
            lineWidth = 2;
        }

        int leftWidth = lineWidth / 2;
        int rightWidth = lineWidth / 2;

        if(lineWidth % 2 == 1) {
            leftWidth = lineWidth / 2;
            rightWidth = (lineWidth / 2) + 1;
        }

        for(int i = 0; i < leftWidth; i++) {
            xc = original_xc + i;
            pixel.drawPixel(xc, yc);
            for(int j = 0; j < leftWidth; j++) {
                yc = original_yc + j;
                pixel.drawPixel(xc, yc);
            }
            for(int j = 0; j < rightWidth; j++) {
                yc = original_yc - j;
                pixel.drawPixel(xc, yc);
            }
        }

        for(int i = 0; i < leftWidth; i++) {
            xc = original_xc - i;
            pixel.drawPixel(xc, yc);
            for(int j = 0; j < leftWidth; j++) {
                yc = original_yc + j;
                pixel.drawPixel(xc, yc);
            }
            for(int j = 0; j < rightWidth; j++) {
                yc = original_yc - j;
                pixel.drawPixel(xc, yc);
            }
        }
    }

    public abstract void drawingMethod();

    protected void scale() {
        LinkedList<Point> scaledPoints;
        Scale scale = new Scale();
        scaledPoints = scale.scale(pc, radius, scaleWidth, scaleHeight);
        pc = scaledPoints.getFirst();
        radius = scaledPoints.getLast();
    }

    public void setScale(double width, double height) {
        this.scaleWidth = width;
        this.scaleHeight = height;
    }

//    public byte getLineType() {
//        return lineType;
//    }
//
//    /**
//     * Set the type of the line (continuous or discontinuous)
//     * @param lineType the line type
//     */
//    public void setLineType(byte lineType) {
//        this.lineType = lineType;
//    }

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
