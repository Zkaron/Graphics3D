package Testing.Filling;

import Core.MyJFrame;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class FloodFillTest extends MyJFrame {
    private JPanel panel;
    private BufferedImage offScreen;
    private int imageWidth;
    private int imageHeight;
    private Point clickedPoint;

    private Filling.FloodFill boundaryFill;
    private Figures.Rectangle rect;
    private AbstractLine line;

    /**
     * Creates the Frame, panel and initializes variables
     */
    public FloodFillTest() {
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element is added


//        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics g2 = buffImage.createGraphics();
//        g2.setColor(panel.getBackground());
//        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());
//
    }

    /**
     * Draws shapes (a triangle and two rectangles) to test the filling algorithm
     */
    public void drawTestFigures() {
        rect.drawRectangle(new Point(50, 50), new Point(100, 100));
        line.drawLine(new Point(100, 100), new Point(80, 140));
        line.drawLine(new Point(80, 140), new Point(120, 100));
        line.drawLine(new Point(100, 100), new Point(120, 100));
        rect.drawRectangle(new Point(150, 150), new Point(400, 400));
        rect.drawRectangle(new Point(250, 150), new Point(300, 300));
//        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
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
            boundaryFill = new Filling.FloodFill(panel, offScreen);

            rect = new Figures.Rectangle(panel);
            line = new BresenhamLine(panel);

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    clickedPoint = new Point(e.getX(), e.getY());
                    boundaryFill.fill(clickedPoint);
                    panel.getGraphics().drawImage(offScreen, 0, 0, panel);
                }
            });
        }   //check if the screen hasn't been drawn or the window was resided

        Graphics offGraphics = offScreen.createGraphics();
        clear(offGraphics);
        line.setGraphics(offGraphics);
        rect.setGraphics(offGraphics);
        boundaryFill.setGraphics(offGraphics);
        drawTestFigures();
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
        FloodFillTest test = new FloodFillTest();
    }
}
