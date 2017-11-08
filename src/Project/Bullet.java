package Project;

import Figures.AbstractCircle;
import Figures.MidpointEllipse;
import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 10/24/2017.
 */
public class Bullet implements Runnable {
    private AbstractLine rect;
    private AbstractCircle circle;
    private FloodFill fill;
    private int shotPosX, shotPosY;

    public Bullet(JPanel panel, BufferedImage image) {
        rect = new Rectangle(panel);
        circle = new MidpointEllipse(panel);
        fill = new FloodFill(panel, image);

    }

    public void drawBullet(int xPos, int yPos, double x, double y) {
        rect.setColor(new Color(132, 124, 70));
        rect.setLineWidth(4);
        rect.setScale(x, y);
//        fill.setColor(new Color(159, 139, 43));
        fill.setScale(x, y);

        rect.drawRectangle(new Point(xPos, yPos), new Point(xPos + 8, yPos + 16));
    }

    public void setGraphics(Graphics g) {
        rect.setGraphics(g);
        circle.setGraphics(g);
        fill.setGraphics(g);
    }


    private void shootAnimation() {
        shotPosY -= 5;
        drawBullet(shotPosX, shotPosY, 1, 1);
//        parentFrame.repaint();
    }

    public void setShootLocation(int x, int y) {
        shotPosX = x;
        shotPosY = y;
    }

    public Point getBulletLocation(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public void run() {
        while (shotPosY >= 0) {
            shootAnimation();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
