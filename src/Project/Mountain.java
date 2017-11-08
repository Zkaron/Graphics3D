package Project;

import Core.MyJFrame;
import Figures.MidpointEllipse;
import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;
import Lines.BresenhamLine;
import Lines.MidPointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mountain {

//    private final String FRAME_TITLE = "Mountain";
//    private JPanel panel;
//    private BufferedImage offScreen;
    private AbstractLine line;
//    private AbstractLine rect;
//    private MidpointEllipse circle;
    private FloodFill fill;
//    private int imageWidth;
//    private int imageHeight;
//
    private int xPos, yPos;

//    public Mountain() {
//        setTitle(FRAME_TITLE);
//        panel = new JPanel();
//        add(panel);
//        this.setVisible(true);  //is set visible again because a new element has been added
//        xPos = 0;
//        yPos = 0;
//    }

    public Mountain(JPanel panel, BufferedImage offGraphics) {
        line = new BresenhamLine(panel);
        fill = new FloodFill(panel, offGraphics);
    }

    public void drawMountain(int xPos, int yPos, double x, double y) {
        this.xPos = xPos;
        this.yPos = yPos;
        //Init scales
        line.setScale(x, y);
        fill.setScale(x, y);

        line.setLineWidth(5);
        line.setColor(new Color(45, 123, 14));
        line.drawLine(new Point(xPos + 50, yPos + 0), new Point(xPos + 100, yPos + 100));
        line.drawLine(new Point(xPos + 50, yPos + 0), new Point(xPos + 0, yPos + 100));
        line.drawLine(new Point(xPos + 0, yPos + 100), new Point(xPos + 100, yPos + 100));
        fill.setColor(new Color(72, 191, 25));
        fill.fill(new Point(xPos + 50, yPos + 50));

        line.drawLine(new Point(xPos + 50, yPos + 30), new Point(xPos + 40, yPos + 20));
        line.drawLine(new Point(xPos + 50, yPos + 30), new Point(xPos + 60, yPos + 20));
        fill.setColor(new Color(255, 255, 255));
        fill.fill(new Point(xPos + 50, yPos + 25));
    }

    public void setGraphics(Graphics g) {
        line.setGraphics(g);
        fill.setGraphics(g);
    }
//
//    public void paint() {
//        Dimension d = panel.getSize();
//        if((offScreen == null) || (d.width != imageWidth)
//                ||(d.height != imageHeight)) {
//            if(d.width < 1 || d.height < 1) return;
//
//            offScreen = new BufferedImage(panel.getWidth(),
//                    panel.getHeight(), BufferedImage.TYPE_INT_RGB);
//            imageWidth = d.width;
//            imageHeight = d.height;
//
//            line = new MidPointLine(panel);
//            rect = new Rectangle(panel);
//            circle = new MidpointEllipse(panel);
//            fill = new FloodFill(panel, offScreen);
//        }   //check if the screen hasn't been drawn or the window was resided
//
//        Graphics offGraphics = offScreen.createGraphics();
//        clear(offGraphics);
//        line.setGraphics(offGraphics);
//        rect.setGraphics(offGraphics);
//        circle.setGraphics(offGraphics);
//        fill.setGraphics(offGraphics);
//        drawMountain(1, 1);
//        Graphics panelGraphics = panel.getGraphics();
//        panelGraphics.drawImage(offScreen, 0, 0, panel);
//    }
//
//    public void paint(Graphics g) {
//        paint();
//    }
//
//    private void clear(Graphics g) {
//        g.setColor(panel.getBackground());
//        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
//    }

//    public static void main(String[] args) {
//        Mountain tk = new Mountain();
//    }
}
