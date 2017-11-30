package Projection;

import Core.Point3D;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by erik on 6/1/17.
 * Uses a focal point to project the 3D lines
 */
public class PerspectiveProjection implements Projection {
    double x0, y0, x1, y1;
    private Point3D centerPoint;

    /**
     * Creates a object with the focal point outside of the visible realm
     */
    public PerspectiveProjection() {
        centerPoint = new Point3D(-1, -1, -1);
    }

    public PerspectiveProjection(PerspectiveProjection p) {
        this.centerPoint = p.centerPoint;
    }

    /*public PerspectiveProjection(Point3D p0_2D, Point3D p1_2D) {
        centerPoint = new Point3D(300, 300, 300);
        projectWithCurrentFocalPoint(p0_2D, p1_2D);
    }

    public PerspectiveProjection(Point3D p0_2D, Point3D p1_2D, Point3D pc) {
        centerPoint = new Point3D(pc);
        projectWithCurrentFocalPoint(p0_2D, p1_2D);
    }*/

    /**
     * Used when the desired focal point is already set.
     * @param p0 the origin 3D point
     * @param p1 the destine 3D point
     * @return a list with two 2D point
     */
    @Override
    public LinkedList<Point> project(Point3D p0, Point3D p1) {
        LinkedList<Point> v = new LinkedList<>();
        try {
            x0 = centerPoint.x - (centerPoint.z * (p0.x - centerPoint.x) / (p0.z - centerPoint.z));
            y0 = centerPoint.y - (centerPoint.z * (p0.y - centerPoint.y) / (p0.z - centerPoint.z));

            x1 = centerPoint.x - (centerPoint.z * (p1.x - centerPoint.x) / (p1.z - centerPoint.z));
            y1 = centerPoint.y - (centerPoint.z * (p1.y - centerPoint.y) / (p1.z - centerPoint.z));
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        Point point0 = new Point();
        point0.setLocation(x0, y0);
        v.add(point0);

        Point point1 = new Point();
        point1.setLocation(x1, y1);
        v.add(point1);

        return v;
    }

    /**
     * Used when you want to set a new focal point to
     * the perspectivep1.y - centerPoint.y) / (p1.z - centerPoint.z)
     * @param p0 the origin 3D point
     * @param p1 the destine 3D point
     * @param cp the focal(center) 3D point
     * @return a list with two 2D Points
     */
    @Override
    public LinkedList<Point> project(Point3D p0, Point3D p1, Point3D cp) {
        centerPoint = new Point3D(cp);
        LinkedList<Point> v = new LinkedList<>();

        x0 = centerPoint.x - (centerPoint.z * (p0.x - centerPoint.x) / (p0.z - centerPoint.z));
        y0 = centerPoint.y - (centerPoint.z * (p0.y - centerPoint.y) / (p0.z - centerPoint.z));
        Point point0 = new Point();
        point0.setLocation(x0, y0);
        v.add(point0);

        x1 = centerPoint.x - (centerPoint.z * (p1.x - centerPoint.x) / (p1.z - centerPoint.z));
        y1 = centerPoint.y - (centerPoint.z * (p1.y - centerPoint.y) / (p1.z - centerPoint.z));
        Point point1 = new Point();
        point1.setLocation(x1, y1);
        v.add(point1);

        return v;
    }

    /**
     * Set a new focal point
     * @param center the focal point
     */
    @Override
    public void setAdjustment(Point3D center) {
        this.centerPoint = center;
    }


}
