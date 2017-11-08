package Testing.Transformations;

import Core.MyJFrame;
import Lines.AbstractLine;
import Lines.BasicLine;
import Testing.Lines.BasicLineTest;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 9/22/2017.
 */
public class ScaleTest extends MyJFrame {
    private final String FRAME_TITLE = "Scale";
    private JPanel panel;
    private BufferedImage offScreen;
    private AbstractLine line;
    private int imageWidth;
    private int imageHeight;

    public ScaleTest() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added
    }

    public void drawSomethingCool() {
        double scaleX = 0.5, scaleY = 0.5;
        line.setScale(scaleX, scaleY);
        line.drawLine(new Point(100, 50), new Point(50, 100));
        line.drawLine(new Point(150, 50), new Point(200, 100));
        line.drawLine(new Point(100, 200),new Point( 50, 200));
        line.drawLine(new Point(150, 200),new Point( 200, 200));
        line.drawLine(new Point(100, 300),new Point( 100, 350));
        line.drawLine(new Point(200, 350),new Point( 200, 300));
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
            line = new BasicLine(panel);
        }   //check if the screen hasn't been drawn or the window was resided

        Graphics offGraphics = offScreen.createGraphics();
        clear(offGraphics);
        line.setGraphics(offGraphics);
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
        ScaleTest test = new ScaleTest();
    }
}
