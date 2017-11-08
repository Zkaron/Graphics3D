package Testing.Clipping;

import Clipping.ExplicitClip;
import Core.MyJFrame;
import Figures.*;
import Figures.Rectangle;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Erik on 7/8/2017.
 */
public class ExplicitClipTest extends MyJFrame {
    private final String FRAME_TITLE = "Explicit Clip";
    public LinkedList<Point> originalLinePointsVector;
    public LinkedList<Point> linePointsVector;
    public LinkedList<Point> rectanglePointsVector;
    private JPanel panel;
    private BufferedImage offScreen;
    private AbstractLine line;
    private Rectangle rect;
    private int imageWidth;
    private int imageHeight;

    private Point pressedPoint;
    private Point releasedPoint;

    private ExplicitClip clip;

    public ExplicitClipTest() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added
        originalLinePointsVector = new LinkedList<>();
        linePointsVector = new LinkedList<>();
        rectanglePointsVector = new LinkedList<>();
        clip = new ExplicitClip(linePointsVector);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pressedPoint = new Point(e.getPoint());
                System.out.print("p0[" + e.getX() + ", " + e.getY() + "]  ");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(e.getButton() == MouseEvent.BUTTON3) {
                    linePointsVector.clear();
                    for(int i = 0; i < originalLinePointsVector.size(); i++) {
                        linePointsVector.add(new Point(originalLinePointsVector.get(i)));
                    }
                    clip = new ExplicitClip(linePointsVector);
                    repaint();
                } else {
                    releasedPoint = new Point(e.getPoint());
                    setClipArea(pressedPoint, releasedPoint);
                    System.out.println("p1[" + e.getX() + ", " + e.getY() + "]");
                }
            }
        });
    }

    public void drawSomethingCool() {
        line.setLineWidth(5);
        for(int p0 = 0, p1 = 1; p1 < linePointsVector.size(); p0 += 2, p1 += 2){
            line.drawLine(linePointsVector.get(p0), linePointsVector.get(p1));
        }
        for(int p0 = 0, p1 = 1; p1 < rectanglePointsVector.size(); p0 += 2, p1 += 2){
            rect.drawRectangle(rectanglePointsVector.get(p0), rectanglePointsVector.get(p1));
        }
    }

    public void setCoolPoints() {
        linePointsVector.add(new Point(100, 50));
        linePointsVector.add(new Point(50, 100));
        linePointsVector.add(new Point(150, 50));
        linePointsVector.add(new Point(200, 100));
        linePointsVector.add(new Point(100, 200));
        linePointsVector.add(new Point(50, 200));
        linePointsVector.add(new Point(150, 200));
        linePointsVector.add(new Point(200, 200));
        linePointsVector.add(new Point(100, 300));
        linePointsVector.add(new Point(100, 350));
        linePointsVector.add(new Point(200, 350));
        linePointsVector.add(new Point(200, 300));

        for(int i = 0; i < linePointsVector.size(); i++) {
            originalLinePointsVector.add(new Point(linePointsVector.get(i)));
        }
    }

    public void setClipArea(Point pPressed, Point pReleased) {
        rectanglePointsVector.clear();
        rectanglePointsVector.add(pPressed);
        rectanglePointsVector.add(pReleased);
        clip.setClipArea(rectanglePointsVector.get(0), rectanglePointsVector.get(1));

        clip.clip();
        repaint();
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
            line = new BresenhamLine(panel);
            rect = new Rectangle(panel);
        }   //check if the screen hasn't been drawn or the window was resided

        Graphics offGraphics = offScreen.createGraphics();
        clear(offGraphics);
        line.setGraphics(offGraphics);
        rect.setGraphics(offGraphics);
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
        ExplicitClipTest test = new ExplicitClipTest();
        test.setCoolPoints();
    }
}
