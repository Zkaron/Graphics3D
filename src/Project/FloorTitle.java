package Project;

import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 10/24/2017.
 */
public class FloorTitle {
    private AbstractLine rect;
    private FloodFill fill;

    public FloorTitle(JPanel panel, BufferedImage image) {
        rect = new Rectangle(panel);
        fill = new FloodFill(panel, image);
    }

    public void drawSquareGreenTitle(int xPos, int yPos, double x, double y) {
        rect.setColor(new Color(7, 143, 31));
        rect.setLineWidth(3);
        rect.setScale(x, y);
        fill.setColor(new Color(43, 191, 26));
        fill.setScale(x, y);

        rect.drawRectangle(new Point(xPos, yPos), new Point(xPos + 50, yPos + 50));
        fill.fill(new Point(xPos + 25, yPos + 25));
    }

    public void drawSquareBrownTitle(int xPos, int yPos, double x, double y) {
        rect.setColor(new Color(94, 66, 69));
        rect.setLineWidth(3);
        rect.setScale(x, y);
        fill.setColor(new Color(119, 83, 86));
        fill.setScale(x, y);

        rect.drawRectangle(new Point(xPos, yPos), new Point(xPos + 50, yPos + 50));
        fill.fill(new Point(xPos + 25, yPos + 25));
    }

    public void drawGrassTitle(int xPos, int yPos, double x, double y) {
        drawSquareBrownTitle(xPos, yPos, x, y);
        drawSquareGreenTitle(xPos, yPos * 3, x, y / 3);
    }

    public void setGraphics(Graphics g) {
        rect.setGraphics(g);
        fill.setGraphics(g);
    }
}
