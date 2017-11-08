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
 * Created by Erik on 9/21/2017.
 *
 * Necesario agregar correccion en cuandro a relleno de figuras en caso de escalación asi como
 * escalacion de circulos
 */
public class Tank {
//    private final String FRAME_TITLE = "Midpoint Line";
//    private JPanel panel;
//    private BufferedImage buffImage;
//    private AbstractLine line;
    private AbstractLine rect;
    private MidpointEllipse circle;
    private FloodFill fill;

//    public Tank() {
//        setTitle(FRAME_TITLE);
//        panel = new JPanel();
//        add(panel);
//        this.setVisible(true);  //is set visible again because a new element has been added
//
//        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics g2 = buffImage.createGraphics();
//        g2.setColor(panel.getBackground());
//        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());
//
////        line = new MidPointLine(panel, g2);
//        rect = new Rectangle(panel, g2);
//        circle = new MidpointEllipse(panel, g2);
//        fill = new FloodFill(panel, buffImage, g2);
//    }

    public Tank(JPanel panel, BufferedImage offScreen) {
        rect = new Rectangle(panel);
        circle = new MidpointEllipse(panel);
        fill = new FloodFill(panel, offScreen);
    }

    public void drawFrontIdleTank(int xPos, int yPos, double x, double y) {
        rect.setColor(Color.BLACK);
        rect.setScale(x, y);
        circle.setScale(x, y);
        fill.setScale(x, y);

        rect.setLineWidth(3);
        circle.setLineWidth(3);
        fill.getPixel().setPixelColor(new Color(12, 114, 60));
        rect.drawRectangle(new Point(xPos + 50, yPos + 0), new Point(xPos + 200,yPos + 70 ));   //upper cabin
        fill.fill(new Point(xPos + 125,yPos + 35));      //upper cabin

        rect.drawRectangle(new Point(xPos + 0,yPos + 70), new Point(xPos + 250,yPos + 170));   //main cabin
        fill.fill(new Point(xPos + 125,yPos + 120));     //main cabin

        fill.getPixel().setPixelColor(Color.BLACK);

        rect.drawRectangle(new Point(xPos + 0,yPos + 170), new Point(xPos + 50,yPos + 220));   //left wheel
        fill.fill(new Point(xPos + 25, yPos + 195));     //left wheel

        rect.drawRectangle(new Point(xPos + 200,yPos + 170), new Point(xPos + 250,yPos + 220));   //right wheel
        fill.fill(new Point(xPos + 225, yPos + 195));     //right wheel

        circle.setColor(Color.BLACK);
//        circle.setLineWidth(5);
        circle.drawCircle(new Point(xPos + 125,yPos + 35), new Point(25 , 25 ));      //outer gun
        circle.drawCircle(new Point(xPos + 125,yPos + 35), new Point(18 , 18 ));      //inner gun
//        circle.drawLeftHalfCircle(new Point(xPos + 100, yPos + 100), new Point(50, 50));
        fill.fill(new Point(xPos + 147,yPos + 35));     //gun
    }

    public void setGraphics(Graphics g) {
        rect.setGraphics(g);
        circle.setGraphics(g);
        fill.setGraphics(g);
    }

//    public static void main(String[] args) {
//        Tank tk = new Tank();
//        tk.drawFrontIdleTank(0.5, 0.5);
//    }
}
