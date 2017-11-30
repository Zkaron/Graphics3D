package Testing.Curves;

import Core.MyJFrame;
import Core.Point3D;
import Curves.ExplicitCurve;
import Figures.Rectangle;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ExplicitCurveTest extends MyJFrame {
    private final String FRAME_TITLE = "Curve";
    private JPanel panel;
    private BufferedImage buffImage;
    private Rectangle fillRect;
    private ExplicitCurve curve;
    private Point3D adjusment;

    public ExplicitCurveTest() {
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
        curve = new ExplicitCurve(g2);
        curve.setPerspectiveType("perspective");
        curve.setPerspectiveAdjustment(adjusment);

    }

    public void drawSomethingCool() {
        Point3D drawFrom = new Point3D(300, 300, 0);
        curve.drawCurve(drawFrom, 0, 50);
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        ExplicitCurveTest test = new ExplicitCurveTest();
        test.drawSomethingCool();
    }
}
