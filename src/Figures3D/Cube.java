package Figures3D;

import Core.Point3D;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by erik on 5/31/17.
 * Saves the 3D points and draws a cube with them
 * Usage of the class:
 *  1. Every drawn cube should be a different cube object
 *  2.
 */
public class Cube {

    public static final double DEFAULT_ASPECT_RATIO = 20;
    public ArrayList<Point3D> point3DArrayList;

    private ArrayList<ArrayList<Point3D>> face3DArrayList;

    private AbstractLine line3D;

    /**
     * Constructor used for full control of the drawing mode
     * (for example double buffering)
     * @param graphics the container's graphics
     */
    public Cube(Graphics graphics) {
        point3DArrayList = new ArrayList<>();
        face3DArrayList = new ArrayList<>();
        line3D = new BresenhamLine(graphics);
    }

    /**
     * Draws a cube with the given points, the result depends on the implementation
     * of the Projection and derived classes
     * @param p0 the origin 3D point
     * @param p1 the destine 3D point
     * @param ASPECT_RATIO_UNIT the aspect-ratio to use on the cube
     */
    public void drawCube(Point3D p0, Point3D p1, final double ASPECT_RATIO_UNIT) {

//        line3D.drawLine3D(new Point3D(p0.getX(), p0.getY(), p0.getZ()), new Point3D(p1.getX(), p0.getY(), p0.getZ()));
//        line3D.drawLine3D(new Point3D(p0.getX(), p0.getY(), p0.getZ()), new Point3D(p0.getX(), p1.getY(), p0.getZ()));
//        line3D.drawLine3D(new Point3D(p0.getX(), p1.getY(), p0.getZ()), new Point3D(p1.getX(), p1.getY(), p0.getZ()));
//        line3D.drawLine3D(new Point3D(p1.getX(), p0.getY(), p0.getZ()), new Point3D(p1.getX(), p1.getY(), p0.getZ()));
//
//        line3D.drawLine3D(new Point3D(p0.getX(), p0.getY(), p1.getZ()), new Point3D(p1.getX(), p0.getY(), p1.getZ()));
//        line3D.drawLine3D(new Point3D(p0.getX(), p0.getY(), p1.getZ()), new Point3D(p0.getX(), p1.getY(), p1.getZ()));
//        line3D.drawLine3D(new Point3D(p0.getX(), p1.getY(), p1.getZ()), new Point3D(p1.getX(), p1.getY(), p1.getZ()));
//        line3D.drawLine3D(new Point3D(p1.getX(), p0.getY(), p1.getZ()), new Point3D(p1.getX(), p1.getY(), p1.getZ()));
//
//        line3D.drawLine3D(new Point3D(p0.getX(), p0.getY(), p0.getZ()), new Point3D(p0.getX(), p0.getY(), p1.getZ()));
//        line3D.drawLine3D(new Point3D(p1.getX(), p0.getY(), p0.getZ()), new Point3D(p1.getX(), p0.getY(), p1.getZ()));
//        line3D.drawLine3D(new Point3D(p0.getX(), p1.getY(), p0.getZ()), new Point3D(p0.getX(), p1.getY(), p1.getZ()));
//        line3D.drawLine3D(new Point3D(p1.getX(), p1.getY(), p0.getZ()), new Point3D(p1.getX(), p1.getY(), p1.getZ()));
//
//        modelCube(p0, p1, ASPECT_RATIO_UNIT);
//        for(int i = 0; i < point3DArrayList.size() - 2; i++) {
//            line3D.drawLine3D(point3DArrayList.get(i), point3DArrayList.get(i + 1));
//        }
        modelCube(p0, p1, ASPECT_RATIO_UNIT);
        drawModeledCube();
//        drawFaces();
    }

//    public void drawCube() {
//        drawFaces();
//    }

    public void modelCube(Point3D p0, Point3D p1, final double ASPECT_RATIO_UNIT) {
//        point3DArrayList.add(new Point3D(p0.getX(), p0.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p0.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p0.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p1.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p1.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p1.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p0.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p1.getY(), p0.getZ()));
//
//        point3DArrayList.add(new Point3D(p0.getX(), p0.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p0.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p0.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p1.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p1.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p1.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p0.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p1.getY(), p1.getZ()));
//
//        point3DArrayList.add(new Point3D(p0.getX(), p0.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p0.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p0.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p0.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p1.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p0.getX(), p1.getY(), p1.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p1.getY(), p0.getZ()));
//        point3DArrayList.add(new Point3D(p1.getX(), p1.getY(), p1.getZ()));

        point3DArrayList.add(new Point3D(p0.x, p0.y, p0.z));
        point3DArrayList.add(new Point3D(p0.x, p0.y + ASPECT_RATIO_UNIT, p0.z));
        point3DArrayList.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y, p0.z));
        point3DArrayList.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p0.z));

        point3DArrayList.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        point3DArrayList.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y, p0.z + ASPECT_RATIO_UNIT));
        point3DArrayList.add(new Point3D(p0.x, p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        point3DArrayList.add(new Point3D(p0.x, p0.y, p0.z + ASPECT_RATIO_UNIT));


//        point3DArrayList.add(new Point3D(p1.x, p1.y, p1.z));
//        point3DArrayList.add(new Point3D(p1.x, p1.y - ASPECT_RATIO_UNIT, p1.z));
//        point3DArrayList.add(new Point3D(p1.x - ASPECT_RATIO_UNIT, p1.y, p1.z));
//        point3DArrayList.add(new Point3D(p1.x - ASPECT_RATIO_UNIT, p1.y - ASPECT_RATIO_UNIT, p1.z));

        //Draws from point3DArrayList (All significant points)
//        for(int i = 0; i <= point3DArrayList.size() - 2; i+=2) {
//            line3D.drawLine3D(point3DArrayList.get(i), point3DArrayList.get(i + 1));
//        }

//        modelFaces(p0, p1, ASPECT_RATIO_UNIT);

        /*point3DArrayList.sort(new ComparatorPoint3D());*/
    }

    private void modelFaces(Point3D p0, Point3D p1, final double ASPECT_RATIO_UNIT) {
        //Face 1
        ArrayList<Point3D> face3D = new ArrayList<>();

        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT , p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3DArrayList.add(face3D);

        //Face 2
        face3D = new ArrayList<>();

        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3DArrayList.add(face3D);

        //Face 3
        face3D = new ArrayList<>();

        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p0.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p0.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p0.z + ASPECT_RATIO_UNIT));
        face3D.add(new Point3D(p1.x + ASPECT_RATIO_UNIT, p1.y + ASPECT_RATIO_UNIT, p1.z + ASPECT_RATIO_UNIT));
        face3DArrayList.add(face3D);
    }

    public void drawModeledCube() {
        //Draws from point3DArrayList (All significant points)
        line3D.drawLine3D(point3DArrayList.get(0),point3DArrayList.get(1));
        line3D.drawLine3D(point3DArrayList.get(0),point3DArrayList.get(2));
        line3D.drawLine3D(point3DArrayList.get(0),point3DArrayList.get(7));
        line3D.drawLine3D(point3DArrayList.get(1),point3DArrayList.get(3));
        line3D.drawLine3D(point3DArrayList.get(1),point3DArrayList.get(6));
        line3D.drawLine3D(point3DArrayList.get(2),point3DArrayList.get(3));
        line3D.drawLine3D(point3DArrayList.get(2),point3DArrayList.get(5));
        line3D.drawLine3D(point3DArrayList.get(3),point3DArrayList.get(4));
        line3D.drawLine3D(point3DArrayList.get(4),point3DArrayList.get(5));
        line3D.drawLine3D(point3DArrayList.get(4),point3DArrayList.get(6));
        line3D.drawLine3D(point3DArrayList.get(5),point3DArrayList.get(7));
        line3D.drawLine3D(point3DArrayList.get(6),point3DArrayList.get(7));
    }

    private void drawFaces() {
        //Draws from face3DArrayList
        for(int i = 0; i < face3DArrayList.size(); i++) {
            for(int j = 0; j < face3DArrayList.get(i).size() - 1; j += 2) {
                Point3D p0 = new Point3D(face3DArrayList.get(i).get(j));
                Point3D p1 = new Point3D(face3DArrayList.get(i).get(j + 1));

                line3D.drawLine3D(p0, p1);
            }
        }
    }

    public void setPoint3DArrayList(ArrayList<Point3D> arrayList) {
        this.point3DArrayList = arrayList;
    }

    public ArrayList<Point3D> getPoint3DArrayList() {
        return point3DArrayList;
    }

    public AbstractLine getLine() {
        return line3D;
    }

    public void setPerspectiveType(String type) {
        line3D.setProjectionType(type);
    }

    public void setPerspectiveAdjustment(Point3D adjustment) {
        line3D.setProjectionAdjustment(adjustment);
    }

//    public void setPerspective(PerspectiveProjection p ) {
//        line3D.setPerspective(p);
//    }
}
