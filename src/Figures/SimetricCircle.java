package Figures;

import Core.Pixel;
import Core.MyJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by erik on 4/19/17.
 */
public class SimetricCircle extends AbstractCircle {
    public SimetricCircle(JPanel context) {
        super();
        pixel.setContext(context);
        pixel.setGraphics(context.getGraphics());
    }

    public SimetricCircle(JPanel context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    public void drawingMethod(){
        int x, y;
        int r2 = (int)radius.getX() * (int)radius.getX();

        drawWithWidth((int)pc.getX(), (int)pc.getY() + (int)radius.getX());
        drawWithWidth((int)pc.getX(), (int)pc.getY() - (int)radius.getX());
        drawWithWidth((int)pc.getX() + (int)radius.getX(), (int)pc.getY());
        drawWithWidth((int)pc.getX() - (int)radius.getX(), (int)pc.getY());

        x = 1;
        y = (int) (Math.sqrt(r2 - 1) + 0.5);
        while(x < y) {
            //y = yc + Math.sqrt((radius * radius) - Math.pow((x - xc), 2));
            drawWithWidth((int)pc.getX() + x, (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() + x, (int)pc.getY() - y);
            drawWithWidth((int)pc.getX() - x, (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() - x, (int)pc.getY() - y);

            drawWithWidth((int)pc.getY() + y, (int)pc.getX() + x);
            drawWithWidth((int)pc.getY() + y, (int)pc.getX() - x);
            drawWithWidth((int)pc.getY() - y, (int)pc.getX() + x);
            drawWithWidth((int)pc.getY() - y, (int)pc.getX() - x);
            x++;
            y = (int) (Math.sqrt(r2 - x*x) + 0.5);
        }
    }
}
