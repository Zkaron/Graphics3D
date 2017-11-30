package Curves;

import Core.Point3D;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import java.awt.*;
import java.util.ArrayList;

public class ExplicitCurve {
    public ArrayList<Point3D> point3DArrayList;
    private AbstractLine line3D;

    public ExplicitCurve(Graphics g2) {
        point3DArrayList = new ArrayList<>();
        line3D = new BresenhamLine(g2);
    }

    public void drawCurve(Point3D initialPos, double infLimit, double supLimit) {
        modelCurve(initialPos, infLimit, supLimit);

        for(int i = 3; i < point3DArrayList.size(); i++) {
//            line3D.drawLine3D(point3DArrayList.get(i - 1), point3DArrayList.get(3));
            line3D.drawLine3D(point3DArrayList.get(i - 2), point3DArrayList.get(i - 3));
//            line3D.drawLine3D(point3DArrayList.get(i - 2), point3DArrayList.get(i));
        }
    }

    private void modelCurve(Point3D initialPoint, double infLimit, double supLimit) {
        for(double t = infLimit; t <= supLimit; t+=0.001) {
            double x = Math.cos(t) * 70;
            double y = Math.sin(t) * 80;
            double z = 3 * t;
            point3DArrayList.add(new Point3D(x + initialPoint.x, y + initialPoint.y, z + initialPoint.z));
        }
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
