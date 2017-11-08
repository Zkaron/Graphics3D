package Testing.Figures;

import Core.MyJFrame;
import Figures.AbstractCircle;
import Figures.MidpointEllipse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Erik on 7/6/2017.
 */
public class MidpointEllipseTest extends MyJFrame {
    private final String FRAME_TITLE = "Midpoint Ellipse";
    public LinkedList<Point> circlePointsVector;
    private JPanel panel;
    private BufferedImage offScreen;
    private MidpointEllipse circle;
    private int imageWidth;
    private int imageHeight;


    public MidpointEllipseTest() {
        setTitle(FRAME_TITLE);
        setSize(700, 700);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added
        circlePointsVector = new LinkedList<>();

        setCoolPoints();
    }

    public void drawSomethingCool() {
        for(int p0 = 0, p1 = 1; p1 < circlePointsVector.size(); p0 += 2, p1 += 2){
            circle.drawCircle(circlePointsVector.get(p0), circlePointsVector.get(p1));
        }
//        circle.drawUpperHalfCircle(new Point(150, 150), new Point(100, 100));
//        circle.drawLowerHalfCircle(new Point(350, 350), new Point(100, 100));
//        circle.drawLeftHalfCircle(new Point(250, 100), new Point(100, 100));
//        circle.drawRightHalfCircle(new Point(250, 400), new Point(100, 100));
    }

    public void setCoolPoints() {
        circlePointsVector.add(new Point(300, 300));
        circlePointsVector.add(new Point(100, 100));
        circlePointsVector.add(new Point(300, 300));
        circlePointsVector.add(new Point(50, 30));
        circlePointsVector.add(new Point(300, 300));
        circlePointsVector.add(new Point(170, 230));
//        circlePointsVector.add(new Point(300, 300));
//        circlePointsVector.add(new Point(350, 300));
    }

    public void paint() {
        Dimension d = panel.getSize();
        if((offScreen == null) || (d.width != imageWidth)
                ||(d.height != imageHeight)) {
            if(d.width < 1 || d.height < 1) return;

            offScreen = new BufferedImage(panel.getWidth(),
                    panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            imageWidth = d.width;
            imageHeight = d.height;
            circle = new MidpointEllipse(panel);
        }   //check if the screen hasn't been drawn or the window was resided

        Graphics offGraphics = offScreen.createGraphics();
        clear(offGraphics);
        circle.setGraphics(offGraphics);
        drawSomethingCool();
        Graphics panelGraphics = panel.getGraphics();
        panelGraphics.drawImage(offScreen, 0, 0, panel);
    }

    public void paint(Graphics g) {
        paint();
    }

    private void clear(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    public static void main(String[] args) {
        MidpointEllipseTest test = new MidpointEllipseTest();
        test.drawSomethingCool();
    }
}
