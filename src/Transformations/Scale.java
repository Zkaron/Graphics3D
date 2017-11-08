package Transformations;

import Figures.*;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Erik on 9/22/2017.
 */
public class Scale {


    public Scale() {
    }

    /**
     * For lines and rectangles
     * @param p0
     * @param p1
     * @return
     */
    public LinkedList<Point> scale(Point p0, Point p1, double scaleWidth, double scaleHeight) {
        LinkedList<Point> scaledPoint = new LinkedList<>();
        if(scaleWidth != 1 || scaleHeight != 1) {
            int startX = p0.x < p1.x ? p0.x : p1.x;
            int startY = p0.y < p1.y ? p0.y : p1.y;

            p0 = new Point((int)Math.round(p0.x * scaleWidth),(int)Math.round(p0.y * scaleHeight));
            p1 = new Point((int)Math.round(p1.x * scaleWidth), (int)Math.round(p1.y * scaleHeight));

//            p0.x += startX;
//            p0.y += startY;
//            p1.x += startX;
//            p1.y += startY;
        }
        scaledPoint.add(p0);
        scaledPoint.add(p1);
        return scaledPoint;
    }

    /**
     * For single Point
     * @param point
     * @param scaleRatioX
     * @param scaleRatioY
     * @return
     */
    public Point scale(Point point, double scaleRatioX, double scaleRatioY) {
        Point scaledPoint = new Point(point);
        scaledPoint.setLocation(point.x * scaleRatioX, point.y * scaleRatioY);
        return scaledPoint;
    }
}
