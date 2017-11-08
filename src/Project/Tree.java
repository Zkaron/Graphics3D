package Project;

import Core.MyJFrame;
import Figures.*;
import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;
import Lines.MidPointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class Tree {
//    private final String FRAME_TITLE = "Tree";
//    private JPanel panel;
//    private BufferedImage offScreen;
    private AbstractLine line;
    private AbstractLine rect;
    private MidpointEllipse circle;
    private FloodFill fill;
    private int xPos, yPos;
//    private int imageWidth;
//    private int imageHeight;

//    public Tree() {
//        setTitle(FRAME_TITLE);
//        panel = new JPanel();
//        add(panel);
//        this.setVisible(true);  //is set visible again because a new element has been added
//    }

    public Tree(JPanel panel, BufferedImage offScreen) {
        rect = new Rectangle(panel);
        circle = new MidpointEllipse(panel);
        fill = new FloodFill(panel, offScreen);
        xPos = 0;
        yPos = 0;
    }

    public void drawTree(int xPos, int yPos, double x, double y) {
        drawTreeTop(xPos, yPos, x, y);
        drawTreeBottom(xPos, yPos, x, y);
    }

    public void drawTreeTop(int xPos, int yPos, double x, double y) {
        initTreeVariables(xPos, yPos, x, y);
        //draw and fill tree top
        circle.setLineWidth(5);
        circle.setColor(new Color(24, 90, 15));
        circle.drawCircle(new Point(xPos + 110, yPos +110), new Point(100, 100));
        fill.setColor(new Color(81, 159, 63));
        fill.fill(new Point(xPos + 110, yPos + 40));

        //Draw tree details
        circle.drawLowerHalfCircle(new Point(xPos + 80, yPos + 40), new Point(20, 15));
        circle.drawLowerHalfCircle(new Point(xPos + 140, yPos + 40), new Point(20, 15));

        circle.drawLowerHalfCircle(new Point(xPos + 60, yPos + 80), new Point(20, 15));
        circle.drawLowerHalfCircle(new Point(xPos + 110, yPos + 80), new Point(20, 15));
        circle.drawLowerHalfCircle(new Point(xPos + 160, yPos + 80), new Point(20, 15));

        circle.drawLowerHalfCircle(new Point(xPos + 40, yPos + 120), new Point(20, 15));
        circle.drawLowerHalfCircle(new Point(xPos + 110, yPos + 120), new Point(20, 15));
        circle.drawLowerHalfCircle(new Point(xPos + 170, yPos + 120), new Point(20, 15));

        circle.drawLowerHalfCircle(new Point(xPos + 40, yPos + 150), new Point(20, 15));
        circle.drawLowerHalfCircle(new Point(xPos + 90, yPos + 170), new Point(20, 15));
        circle.drawLowerHalfCircle(new Point(xPos + 150, yPos + 170), new Point(20, 15));
    }

    public void drawTreeBottom(int xPos, int yPos, double x, double y) {
        initTreeVariables(xPos, yPos, x, y);
        //draw and fill tree bottom
        rect.setColor(new Color(83, 49, 17));
        rect.setLineWidth(5);
        rect.drawRectangle(new Point(xPos + 80, yPos + 210), new Point(xPos + 140, yPos + 380));

        fill.setColor(new Color(112, 82, 25));
        fill.fill(new Point(xPos + 110, yPos + 295));
    }

    private void initTreeVariables(int xPos, int yPos, double x, double y) {

        this.xPos = xPos;
        this.yPos = yPos;
        //Init scales
        rect.setScale(x, y);
        circle.setScale(x, y);
        fill.setScale(x, y);
    }

    public void setGraphics(Graphics g) {
        rect.setGraphics(g);
        circle.setGraphics(g);
        fill.setGraphics(g);
    }

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
//        drawTree(1, 1);
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
//
//    public static void main(String[] args) {
//        Tree tk = new Tree();
//    }
}
