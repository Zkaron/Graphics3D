package Testing.Figures3D;

import Core.MyJFrame;
import Core.Point3D;
import Figures.Rectangle;
import Figures3D.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CubeTestParallel extends MyJFrame {
    private final String FRAME_TITLE = "Cube";
    private JPanel panel;
    private BufferedImage buffImage;
    private Rectangle fillRect;
    private Cube[] cube;
    private Point3D adjusment;

    public CubeTestParallel() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));

        adjusment = new Point3D(0, 0, 2);
        cube = new Cube[2];
        for(int i = 0; i < cube.length; i++) {
            cube[i] = new Cube(g2);
            cube[i].setPerspectiveType("parallel");
            cube[i].setPerspectiveAdjustment(adjusment);
        }

    }

    public void drawSomethingCool() {
        cube[0].drawCube(new Point3D(300, 250, 100), new Point3D(350, 500, 200), Cube.DEFAULT_ASPECT_RATIO);
        cube[1].drawCube(new Point3D(350, 150, 50), new Point3D(500, 100, 100), Cube.DEFAULT_ASPECT_RATIO);
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        CubeTestParallel test = new CubeTestParallel();
        test.drawSomethingCool();
    }
}
