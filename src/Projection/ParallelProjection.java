package Projection;

import Core.Point3D;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by erik on 6/1/17.
 * Project the all the lines to the "infinity"
 */
public class ParallelProjection implements Projection {
    double x0, y0, x1, y1;
    Point3D depth;
    double xp, yp;
    /**
     * Constructs an ParallelProjection object
     */
    public ParallelProjection() {
        xp = 2;
        yp = 2;
        depth = new Point3D(0, 0, 4);
    }

    /**
     * Used when the desired focal point is already set.
     * @param p0 the origin 3D point
     * @param p1 the destine 3D point
     * @return a list with two 2D point
     */
    @Override
    public LinkedList<Point> project(Point3D p0, Point3D p1) {
        LinkedList<Point> v = new LinkedList<>();
        x0 = p0.x - (2 * p0.z) / depth.z;
        y0 = p0.y - (2 * p0.z) / depth.z;
        Point point0 = new Point();
        point0.setLocation(x0, y0);
        v.add(point0);

        x1 = p1.x - (2 * p1.z) / depth.z;
        y1 = p1.y - (2 * p1.z / depth.z);
        Point point1 = new Point();
        point1.setLocation(x1, y1);
        v.add(point1);

        return v;
    }

    /**
     * All the points are projected by the same depth
     * @param p0 the origin 3D point
     * @param p1 the destine 3D point
     * @param depth the profundity of the projection
     * @return a list with two 2D point
     */
    @Override
    public LinkedList<Point> project(Point3D p0, Point3D p1, Point3D depth) {
        LinkedList<Point> v = new LinkedList<>();
        x0 = p0.x - (2 * p0.z) / depth.z;
        y0 = p0.y - (2 * p0.z / depth.z);
        Point point0 = new Point();
        point0.setLocation(x0, y0);
        v.add(point0);

        x1 = p1.x - (2 * p1.z) / depth.z;
        y1 = p1.y - (2 * p1.z / depth.z);
        Point point1 = new Point();
        point1.setLocation(x1, y1);
        v.add(point1);

        return v;
    }

    /**
     * Sets the depth of the projection
     * @param depth profundity of the projection
     */
    @Override
    public void setAdjustment(Point3D depth) {
        this.depth = depth;
    }

}
