package Testing.Figures;

import Core.MyJFrame;
import Figures.AbstractCircle;
import Figures.PolarCoordsCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Erik on 7/6/2017.
 */
public class PolarCoordsCircleTest extends MyJFrame {
    private final String FRAME_TITLE = "Polar Coords Circle";
    public LinkedList<Point> circlePointsVector;
    private JPanel panel;
    private BufferedImage offScreen;
    private AbstractCircle circle;
    private int imageWidth;
    private int imageHeight;


    public PolarCoordsCircleTest() {
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
    }

    public void setCoolPoints() {
        circlePointsVector.add(new Point(300, 300));
        circlePointsVector.add(new Point(100, 100));
        circlePointsVector.add(new Point(300, 300));
        circlePointsVector.add(new Point(50, 50));
        circlePointsVector.add(new Point(300, 300));
        circlePointsVector.add(new Point(200, 200));
        circlePointsVector.add(new Point(300, 300));
        circlePointsVector.add(new Point(250, 250));
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
            circle = new PolarCoordsCircle(panel);
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
        PolarCoordsCircleTest test = new PolarCoordsCircleTest();
        test.drawSomethingCool();
    }
}
