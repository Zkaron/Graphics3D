package Figures;

import Core.Pixel;
import Core.MyJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by erik on 4/24/17.
 */
public class MidpointCircle extends AbstractCircle{
    public MidpointCircle(JPanel context) {
        super();
        pixel.setContext(context);
        pixel.setGraphics(context.getGraphics());
    }

    public MidpointCircle(JPanel context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    public void drawingMethod(){
        int x = 0;
        int y = (int)radius.getX();
        pixel.drawPixel((int)pc.getX() + x, (int)pc.getY() + y);
        double pk = 5.0/4.0 - radius.getX();
        while(x <= y) {
            drawWithWidth((int)pc.getX() + x , (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() + x , (int)pc.getY() - y);
            drawWithWidth((int)pc.getX() - x , (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() - x , (int)pc.getY() - y);
            drawWithWidth((int)pc.getX() + y , (int)pc.getY() + x);
            drawWithWidth((int)pc.getX() + y , (int)pc.getY() - x);
            drawWithWidth((int)pc.getX() - y , (int)pc.getY() + x);
            drawWithWidth((int)pc.getX() - y , (int)pc.getY() - x);

            if(pk <= 0) {
                pk += (x << 1) + 3;
                x++;
            } else if(pk > 0) {
                pk += (x << 1)  - (y << 1) + 5;
                x++;
                y--;
            }
        }
    }
}
