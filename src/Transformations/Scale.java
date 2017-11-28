package Transformations;

import Core.Point3D;
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
     * For two points
     * @param p0
     * @param p1
     * @return
     */
    public LinkedList<Point3D> scale(Point3D p0, Point3D p1, double scaleWidth, double scaleHeight, double scaleDepth) {
        LinkedList<Point3D> scaledPoint = new LinkedList<>();
        if(scaleWidth != 1 || scaleHeight != 1) {
            p0 = new Point3D(p0.x * scaleWidth, p0.y * scaleHeight, p0.z * scaleDepth);
            p1 = new Point3D(p1.x * scaleWidth, p1.y * scaleHeight, p1.z * scaleDepth);
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
     * @param scaleRatioZ
     * @return
     */
    public Point3D scale(Point3D point, double scaleRatioX, double scaleRatioY, double scaleRatioZ) {
        Point3D scaledPoint = new Point3D(point);
        scaledPoint.setLocation(point.x * scaleRatioX, point.y * scaleRatioY, point.z * scaleRatioZ);
        return scaledPoint;
    }
}
