package Transformations;

import Core.Point3D;
import Figures.*;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Erik on 9/22/2017.
 */
public class Transformation {
    private ArrayList<Point3D> point3DArrayList;

    public Transformation() {
    }

    /**
     * @param points
     * @param translateRatio
     * @return
     */
    public ArrayList<Point3D> translate(ArrayList<Point3D> points, Point3D translateRatio) {
        ArrayList<Point3D> translatedPoints = new ArrayList<>();
        for(Point3D p : points) {
            translatedPoints.add(new Point3D(p.x + translateRatio.x, p.y + translateRatio.y, p.z + translateRatio.z));
        }
        return translatedPoints;
    }

    /**
     * @param points
     * @param scaleRatio
     * @return
     */
    public ArrayList<Point3D> scale(ArrayList<Point3D> points, Point3D scaleRatio) {
        ArrayList<Point3D> scaledPoints = new ArrayList<>();
        Point3D basePointLocation = new Point3D(points.get(0)); // we get the first point as the base location

        for(Point3D p : points) {
            Point3D scaledPoint = new Point3D(p);
            p.setLocation(p.x - (basePointLocation.x - 1), p.y - (basePointLocation.y - 1), p.z - (basePointLocation.z - 1));  //try to get point near pos (1, 1, 1)
            p.setLocation(p.x * scaleRatio.x, p.y * scaleRatio.y, p.z * scaleRatio.z);  //do the scale
            p.setLocation(p.x + (basePointLocation.x + 1), p.y + (basePointLocation.y + 1), p.z + (basePointLocation.z + 1));  //return to original point
            scaledPoints.add(p);
        }
        return scaledPoints;
    }

    /**
     * @param points
     * @param rotateAngle
     * @return
     */
    public ArrayList<Point3D> rotateX(ArrayList<Point3D> points, double rotateAngle) {
        ArrayList<Point3D> scaledPoints = new ArrayList<>();
        if(rotateAngle != 0) {
            Point3D basePointLocation = new Point3D(points.get(0)); // we get the first point as the base location
            // For x axis
            for (Point3D p : points) {
                p.setLocation(p.x - (basePointLocation.x - 1), p.y - (basePointLocation.y - 1), p.z - (basePointLocation.z - 1));  //try to get point near pos (1, 1, 1)
                Point3D movedPoint = new Point3D(p);
//            p.x = p.x;
                p.y = ((movedPoint.y * Math.cos(Math.toRadians(rotateAngle))) + (movedPoint.z * Math.sin(Math.toRadians(rotateAngle))));
                p.z = ((movedPoint.y * (-Math.sin(Math.toRadians(rotateAngle)))) + (movedPoint.z * Math.cos(Math.toRadians(rotateAngle))));
                p.setLocation(p.x + (basePointLocation.x + 1), p.y + (basePointLocation.y + 1), p.z + (basePointLocation.z + 1));  //return to original point
                scaledPoints.add(p);
            }
            if (scaledPoints.size() == 0) {
                scaledPoints = points;
            }
        }
        return scaledPoints;
    }

    public ArrayList<Point3D> rotateY(ArrayList<Point3D> points, double rotateAngle) {
        ArrayList<Point3D> scaledPoints = new ArrayList<>();
        if(rotateAngle != 0) {
            Point3D basePointLocation = new Point3D(points.get(0)); // we get the first point as the base location
            for (Point3D p : points) {
                p.setLocation(p.x - (basePointLocation.x - 1), p.y - (basePointLocation.y - 1), p.z - (basePointLocation.z - 1));  //try to get point near pos (1, 1, 1)
                Point3D movedPoint = new Point3D(p);
                p.x = ((movedPoint.x * Math.cos(Math.toRadians(rotateAngle))) + (-movedPoint.z * Math.sin(Math.toRadians(rotateAngle))));
                //scaledPoint.y = point.y;
                p.z = (((movedPoint.x * Math.sin(Math.toRadians(rotateAngle)))) + (movedPoint.z * Math.cos(Math.toRadians(rotateAngle))));
                p.setLocation(p.x + (basePointLocation.x + 1), p.y + (basePointLocation.y + 1), p.z + (basePointLocation.z + 1));  //return to original point
                scaledPoints.add(p);
            }
        }
        if (scaledPoints.size() == 0) {
            scaledPoints = points;
        }
        return scaledPoints;
    }

    public ArrayList<Point3D> rotateZ(ArrayList<Point3D> points, double rotateAngle) {
        ArrayList<Point3D> scaledPoints = new ArrayList<>();
        if(rotateAngle != 0) {
            Point3D basePointLocation = new Point3D(points.get(0)); // we get the first point as the base location
            // For z axis
            for (Point3D p : points) {
                p.setLocation(p.x - (basePointLocation.x - 1), p.y - (basePointLocation.y - 1), p.z - (basePointLocation.z - 1));  //try to get point near pos (1, 1, 1)
                Point3D movedPoint = new Point3D(p);
                p.x = ((movedPoint.x * Math.cos(Math.toRadians(rotateAngle))) + (movedPoint.y * Math.sin(Math.toRadians(rotateAngle))));
                p.y = ((movedPoint.x * -Math.sin(Math.toRadians(rotateAngle))) + (movedPoint.y * Math.cos(Math.toRadians(rotateAngle))));
                //scaledPoint.z = point.z;
                p.setLocation(p.x + (basePointLocation.x + 1), p.y + (basePointLocation.y + 1), p.z + (basePointLocation.z + 1));  //return to original point
                scaledPoints.add(p);
            }
        }
        if (scaledPoints.size() == 0) {
            scaledPoints = points;
        }

        return scaledPoints;
    }


}
