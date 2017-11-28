package Figures;

import Lines.AbstractLine;
import Lines.BresenhamLine;
import javax.swing.*;
import java.awt.*;

/**
 * Created by erik on 4/19/17.
 */
public class Rectangle extends AbstractLine {
    BresenhamLine line;

    public Rectangle(JPanel context) {
        line = new BresenhamLine(context);
    }

    public Rectangle(JPanel context, Graphics g2) {
        line = new BresenhamLine(g2);
    }

    public void fillRectangle(Point p0, Point p1) {
        int diffY = p0.getY() < p1.getY() ? (int)p1.getY() - (int)p0.getY() : (int)p0.getY() - (int)p1.getY();
        int py = p0.getY() < p1.getY() ? (int)p0.getY() : (int)p1.getY();
        for(int y = 0; y < diffY; y++) {
            line.drawLine(new Point((int)p0.getX(), py), new Point((int)p1.getX(), py));
            py++;
        }
    }
    public void setColor(Color color) {
        line.setColor(color);
    }

    public void setGraphics(Graphics g) {
        line.setGraphics(g);
    }

    @Override
    public void drawingMethod() {
        line.setLineWidth(this.getLineWidth());
//        line.setColor(this.getColor());
        line.drawLine(new Point((int) p0_2D.getX(), (int) p0_2D.getY()),
                new Point((int) p1_2D.getX(), (int) p0_2D.getY()));
        line.drawLine(new Point((int) p0_2D.getX(), (int) p0_2D.getY()), new Point((int) p0_2D.getX(), (int) p1_2D.getY()));
        line.drawLine(new Point((int) p1_2D.getX(), (int) p0_2D.getY()), new Point((int) p1_2D.getX(), (int) p1_2D.getY()));
        line.drawLine(new Point((int) p0_2D.getX(), (int) p1_2D.getY()), new Point((int) p1_2D.getX(), (int) p1_2D.getY()));
    }

}
