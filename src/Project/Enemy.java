package Project;

import Figures.*;
import Filling.FloodFill;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Timer;

/**
 * Created by Erik on 10/25/2017.
 */
public class Enemy implements Runnable {
    private AbstractCircle circle;
    private FloodFill fill;
    private int moveWidth;
    private int moveHeigth;
    private int posX, posY;
    private int direction;
    private BufferedImage hitBoxImage;
    private boolean hit, gameEnd;
    private long timeLapse;

    public Enemy(JPanel panel, BufferedImage image) {
        circle = new MidpointEllipse(panel);
        fill = new FloodFill(panel, image);
        moveWidth = image.getWidth() - 160;
        moveHeigth = image.getHeight() - 280;
        posX = 0; posX = 0;
        direction = 11;
        hitBoxImage = image;
        gameEnd = false;
    }

    public void drawEnemy(int xPos, int yPos, double x, double y) {
        setLocation(new Point(xPos, yPos));

        circle.setLineWidth(3);
        circle.setScale(x, y);
        fill.setScale(x, y);

        circle.setColor(new Color(208, 233, 37));
        fill.setColor(new Color(230, 243, 135));
        circle.drawCircle(new Point(xPos + 30, yPos + 5), new Point(15, 15));
//        fill.fill(new Point(xPos + 30, yPos + 5));

        circle.setColor(new Color(116, 44, 35));
        fill.setColor(new Color(198, 78, 64));
        circle.drawCircle(new Point(xPos + 30, yPos + 20), new Point(35, 15));
//        fill.fill(new Point(xPos + 30, yPos + 15));
//        fill.fill(new Point(xPos + 30, yPos + 20));
//        fill.fill(new Point(xPos + 30, yPos + 25));

//        circle.setColor(new Color(65, 98, 197));
        circle.setColor(Color.BLACK);
        fill.setColor(new Color(99, 99, 99));
        circle.drawCircle(new Point(xPos + 31, yPos + 27), new Point(12, 5));
//        fill.fill(new Point(xPos + 36, yPos + 29));

        circle.setLineWidth(4);
        circle.setColor(new Color(179, 36, 210));
        circle.drawCircle(new Point(xPos + 5, yPos + 20), new Point(2, 2));
        circle.drawCircle(new Point(xPos + 55, yPos + 20), new Point(2, 2));
        circle.drawCircle(new Point(xPos + 40, yPos + 15), new Point(2, 2));
        circle.drawCircle(new Point(xPos + 22, yPos + 15), new Point(2, 2));
    }

    public void setGraphics(Graphics g) {
        circle.setGraphics(g);
        fill.setGraphics(g);
    }

    private void moveAnimation() {
        if(System.currentTimeMillis() - timeLapse >= 5000) {
            timeLapse = System.currentTimeMillis();
            posY += 50;
        }
        if(posY >= moveHeigth) {
            gameEnd = true;
        }
        if(posX <= 11) {
            direction = 11;
        } else if(posX >= moveWidth) {
            direction = -11;
        }
        posX += direction;
        drawEnemy(posX, posY, 1.2, 1.2);
//        parentFrame.repaint();
    }

    private boolean isHit() {
        int hitColor = -8094650; // color of the bullet in RGB
        for(int i = posX + 62; i < posX + 124; i++) {
            for(int j = posY; j < posY + 34; j++) {
                if(hitBoxImage.getRGB(i, j) == hitColor) {
                    hit = true;
                    break;
                }
            }
        }
        return hit;
    }

    private void setLocation(Point p) {
        posX = p.x;
        posY = p.y;
    }

    public LinkedList<Point> getBounds() {
        LinkedList bounds = new LinkedList<>();
        bounds.add(new Point(posX, posY));
        bounds.add(new Point(posX + 62, posY + 34));
        return bounds;
    }

    @Override
    public void run() {
        timeLapse = System.currentTimeMillis();
        while (!gameEnd) {
            if (!isHit()) {
                moveAnimation();
                try {
                    Thread.sleep(65);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(isHit()) {
                timeLapse = System.currentTimeMillis();
                try {
                    Thread.sleep(2000);
                    hit = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
