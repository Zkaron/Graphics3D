package Project;

import Figures.MidpointEllipse;
import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;
import Lines.BresenhamLine;
import Lines.MidPointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 10/16/2017.
 */
public class House {
    private AbstractLine line;
    private AbstractLine rect;
    private MidpointEllipse circle;
    private FloodFill fill;

    public House(JPanel panel, BufferedImage offScreen) {
        line = new BresenhamLine(panel);
        rect = new Rectangle(panel);
        circle = new MidpointEllipse(panel);
        fill = new FloodFill(panel, offScreen);
    }

    public void drawFrontHouse(int xPos, int yPos, double x, double y) {
        line.setScale(x, y);
        rect.setScale(x, y);
        circle.setScale(x, y);
        fill.setScale(x, y);

        rect.setColor(Color.BLACK);
        rect.setLineWidth(3);
        circle.setColor(Color.BLACK);
        circle.setLineWidth(3);
        line.setColor(Color.BLACK);
        line.setLineWidth(3);

        //roof
        line.drawLine(new Point(xPos + 1, yPos + 30), new Point(xPos + 50, yPos + 0));
        line.drawLine(new Point(xPos + 50, yPos + 0), new Point(xPos + 100, yPos + 30));
        line.drawLine(new Point(xPos + 50, yPos + 0), new Point(xPos + 50, yPos + 10));
        line.drawLine(new Point(xPos + 1, yPos + 40), new Point(xPos + 50, yPos + 10));
        line.drawLine(new Point(xPos + 50, yPos + 10), new Point(xPos + 100, yPos + 40));

        //walls
        fill.setColor(new Color(218, 200, 139));
        line.drawLine(new Point(xPos + 1, yPos + 30), new Point(xPos + 1, yPos + 120));
        line.drawLine(new Point(xPos + 100, yPos + 30), new Point(xPos + 100, yPos + 120));
        line.drawLine(new Point(xPos + 1, yPos + 120), new Point(xPos + 100, yPos + 120));
        fill.fill(new Point(xPos + 50, yPos + 60));


        //chimney
        line.drawLine(new Point(xPos + 80, yPos + 20), new Point(xPos + 80, yPos + 0));
        line.drawLine(new Point(xPos + 90, yPos + 25), new Point(xPos + 90, yPos + 0));
        line.drawLine(new Point(xPos + 80, yPos + 0), new Point(xPos + 90, yPos + 0));
        fill.setColor(new Color(150, 46, 48));
        fill.fill(new Point(xPos + 85, yPos + 10));

        //door
        rect.drawRectangle(new Point(xPos + 40, yPos + 120), new Point(xPos + 60, yPos + 80));
        fill.setColor(new Color(113, 99, 32));
        fill.fill(new Point(xPos + 50, yPos + 100));

        //window
        line.setLineWidth(1);
        fill.setColor(new Color(67, 166, 173));
        circle.drawCircle(new Point(xPos + 50, yPos + 40), new Point(15, 15));
        fill.fill(new Point(xPos + 50, yPos + 40));
        line.drawLine(new Point(xPos + 50, yPos + 55), new Point(xPos + 50, yPos + 25));
        line.drawLine(new Point(xPos + 35, yPos + 40), new Point(xPos + 65, yPos + 40));

        fill.setColor(new Color(206, 67, 55));
        fill.fill(new Point(xPos + 25, yPos + 20));
        fill.fill(new Point(xPos + 75, yPos + 20));




//        fill.setColor(new Color(12, 114, 60));
//        rect.drawRectangle(new Point(xPos + 50, yPos + 0), new Point(xPos + 200,yPos + 70 ));   //upper cabin
//        fill.fill(new Point(xPos + 125,yPos + 35));      //upper cabin
//
//        rect.drawRectangle(new Point(xPos + 0,yPos + 70), new Point(xPos + 250,yPos + 170));   //main cabin
//        fill.fill(new Point(xPos + 125,yPos + 120));     //main cabin
//
//        fill.setColor(Color.BLACK);
//
//        rect.drawRectangle(new Point(xPos + 0,yPos + 170), new Point(xPos + 50,yPos + 220));   //left wheel
//        fill.fill(new Point(xPos + 25, yPos + 195));     //left wheel
//
//        rect.drawRectangle(new Point(xPos + 200,yPos + 170), new Point(xPos + 250,yPos + 220));   //right wheel
//        fill.fill(new Point(xPos + 225, yPos + 195));     //right wheel
//
//        circle.setColor(Color.BLACK);
////        circle.setLineWidth(5);
//        circle.drawCircle(new Point(xPos + 125,yPos + 35), new Point(25 , 25 ));      //outer gun
//        circle.drawCircle(new Point(xPos + 125,yPos + 35), new Point(18 , 18 ));      //inner gun
////        circle.drawLeftHalfCircle(new Point(xPos + 100, yPos + 100), new Point(50, 50));
//        fill.fill(new Point(xPos + 147,yPos + 35));     //gun
    }

    public void setGraphics(Graphics g) {
        line.setGraphics(g);
        rect.setGraphics(g);
        circle.setGraphics(g);
        fill.setGraphics(g);
    }

}
