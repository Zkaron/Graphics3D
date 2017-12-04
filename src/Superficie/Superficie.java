package Superficie;

import Core.Point3D;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import java.awt.*;
import java.util.ArrayList;

public class Superficie {
    public ArrayList<Point3D> point3DArrayList;
//    public ArrayList<ArrayList<Point3D>> cilinderPoints;
    private AbstractLine line3D;

    public Superficie(Graphics g2) {
        point3DArrayList = new ArrayList<>();
//        cilinderPoints = new ArrayList<>();
        line3D = new BresenhamLine(g2);
    }

    public void drawSuperficie(Point3D initialPos, double infLimit, double supLimit) {
        modelSuperficie(initialPos, infLimit, supLimit);

        double color = 0;
        for(int i = 3; i < point3DArrayList.size(); i++) {
            line3D.drawLine3D(point3DArrayList.get(i - 1), point3DArrayList.get(i - 3));
            line3D.drawLine3D(point3DArrayList.get(i - 2), point3DArrayList.get(i - 3));
            line3D.drawLine3D(point3DArrayList.get(i - 2), point3DArrayList.get(i));
            color = Math.random() * 255 + 1;
            line3D.setColor(new Color(0, 0, (int)color));
        }

//        for(int i = 0; i < cilinderPoints.size(); i++) {
//            for (int j = 3; j < cilinderPoints.get(i).size(); j++) {
//                line3D.drawLine3D(cilinderPoints.get(i).get(j - 1), cilinderPoints.get(i).get(j - 3));
//                line3D.drawLine3D(cilinderPoints.get(i).get(j - 2), cilinderPoints.get(i).get(j - 3));
//                line3D.drawLine3D(cilinderPoints.get(i).get(j - 2), cilinderPoints.get(i).get(j));
//            }
//        }
    }

    private void modelSuperficie(Point3D initialPoint, double infLimit, double supLimit) {
        int i = 0;
        for(double t = infLimit; t < supLimit; t+=0.01) {
            double x = t * Math.cos(t * t);
            double y = t * Math.sin(t * t * 2);
            double z = 1 * t;
            point3DArrayList.add(new Point3D(x + initialPoint.x, y + initialPoint.y, z + initialPoint.z));
        }
//        for(double t = infLimit; t < supLimit; t+=1){
//            ArrayList<Point3D> point3DArrayList = new ArrayList<>();
//            for(double w = infLimit; w < supLimit; w+=1) {
//                double x = w;
//                double y = (t / 2) * Math.cos(w);
//                double z = (t / 2) * Math.sin(t);
//                point3DArrayList.add(new Point3D(x + initialPoint.x, y + initialPoint.y, z + initialPoint.z));
//            }
//            cilinderPoints.add(point3DArrayList);
//        }
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

}
