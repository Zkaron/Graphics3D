package Testing.Figures3D;

import Core.MyJFrame;
import Core.Point3D;
import Figures.Rectangle;
import Figures3D.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class CubeTestPerspective extends MyJFrame {
    private final String FRAME_TITLE = "Cube";
    private JPanel panel;
    private BufferedImage buffImage;
    private Rectangle fillRect;
    private Cube[] cube;
    private Point3D adjusment;

    public CubeTestPerspective() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));

        adjusment = new Point3D(-60, -40,-80);
        cube = new Cube[2];
        for(int i = 0; i < cube.length; i++) {
            cube[i] = new Cube(g2);
            cube[i].setPerspectiveType("perspective");
            cube[i].setPerspectiveAdjustment(adjusment);
        }

    }

    public void drawSomethingCool() {
        cube[0].drawCube(new Point3D(500, 500, 100), new Point3D(150, 200, 100), 500);
//        cube[1].drawCube(new Point3D(250, 250, 50), new Point3D(300, 200, 100), 150);
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        CubeTestPerspective test = new CubeTestPerspective();
        test.drawSomethingCool();
    }
}
