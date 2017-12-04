package Testing.RotatingCube;

import Core.MyJFrame;
import Core.Point3D;
import Figures.Rectangle;
import Figures3D.Cube;
import Transformations.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AutomaticCubeTest extends MyJFrame {
    private final String FRAME_TITLE = "Rotating cube";
    public JPanel panel;
    public BufferedImage buffImage;
    private Rectangle fillRect;
    public Cube cube;
    public Graphics g2;
    private Point3D adjusment;
    public Transformation transformation;
    private final String[] transformationKeys= {"translation", "scalation", "rotation"};
    private String transformKeySelected;

    public AutomaticCubeTest() {
        setTitle(FRAME_TITLE);
//        setSize(1800, 1000);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));

        adjusment = new Point3D(0, 0, 4);
        cube = new Cube(g2);
        cube = new Cube(g2);
        cube.setPerspectiveType("parallel");
        cube.setPerspectiveAdjustment(adjusment);
        transformation = new Transformation();

        transformKeySelected = transformationKeys[0];
    }

    public void drawSomethingCool() {
        cube.modelCube(new Point3D(90, 200, 100), new Point3D(400, 400, 110), Cube.DEFAULT_ASPECT_RATIO);
        cube.point3DArrayList = transformation.scale(cube.point3DArrayList, new Point3D(4, 4, 4));
        cube.drawModeledCube();

        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    private void clear(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    public static void main(String[] args) {
        AutomaticCubeTest test = new AutomaticCubeTest();
        test.cube.modelCube(new Point3D(90, 100, 100), new Point3D(400, 400, 110), Cube.DEFAULT_ASPECT_RATIO);
        test.cube.point3DArrayList = test.transformation.scale(test.cube.point3DArrayList, new Point3D(4, 4, 4));
        test.cube.drawModeledCube();

        test.panel.getGraphics().drawImage(test.buffImage, 0, 0, test.panel);
        while(true) {
            test.cube.point3DArrayList = test.transformation.rotateX(test.cube.point3DArrayList, 10);
            test.cube.point3DArrayList = test.transformation.rotateY(test.cube.point3DArrayList, 10);
            test.panel.getGraphics().drawImage(test.buffImage, 0, 0, test.panel);

            test.clear(test.g2);
            test.cube.drawModeledCube();

            try {
                Thread.sleep(100);
//                test.cube.point3DArrayList = test.transformation.rotateZ(test.cube.point3DArrayList, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
