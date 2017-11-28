package Projection;

import Core.Point3D;

import java.awt.*;
import java.util.LinkedList;

public interface Projection {
    LinkedList<Point> project(Point3D p0, Point3D p1);
    LinkedList<Point> project(Point3D p0, Point3D p1, Point3D adjustment);

    void setAdjustment(Point3D adjustment);
}
