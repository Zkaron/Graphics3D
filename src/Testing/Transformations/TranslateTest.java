package Testing.Transformations;

import Core.MyJFrame;
import Core.Point3D;
import Figures.Rectangle;
import Figures3D.Cube;
import Transformations.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TranslateTest extends MyJFrame {
    private final String FRAME_TITLE = "Cube translated";
    private JPanel panel;
    private BufferedImage buffImage;
    private Rectangle fillRect;
    private Cube[] cube;
    private Point3D adjusment;
    private Transformation translation;

    public TranslateTest() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));

        adjusment = new Point3D(-1000, -1000, -2000);
        cube = new Cube[2];
        for(int i = 0; i < cube.length; i++) {
            cube[i] = new Cube(g2);
            cube[i].setPerspectiveType("perspective");
            cube[i].setPerspectiveAdjustment(adjusment);
        }
        translation = new Transformation();

    }

    public void drawSomethingCool() {
        //cube[0].drawCube(new Point3D(300, 250, 100), new Point3D(350, 500, 200), Cube.DEFAULT_ASPECT_RATIO);

        cube[1].modelCube(new Point3D(300, 250, 100), new Point3D(350, 500, 200), Cube.DEFAULT_ASPECT_RATIO);
        Point3D scaleRatio = new Point3D(-30, -50, 0);
        ArrayList<Point3D> scaledPoints;
        scaledPoints = translation.translate(cube[1].point3DArrayList, scaleRatio);
        cube[1].setPoint3DArrayList(scaledPoints);
        cube[1].drawModeledCube();

        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        TranslateTest test = new TranslateTest();
        test.drawSomethingCool();
    }
}
