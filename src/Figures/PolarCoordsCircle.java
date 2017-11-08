package Figures;

import Core.Pixel;
import Core.MyJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by erik on 4/19/17.
 */
public class PolarCoordsCircle extends AbstractCircle {
    public PolarCoordsCircle(JPanel context) {
        super();
        pixel.setContext(context);
        pixel.setGraphics(context.getGraphics());
    }

    public PolarCoordsCircle(JPanel context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    public void drawingMethod(){
        for(double t = 0; t <= 2 * Math.PI; t = t + 0.01) {
            double x = pc.getX() + radius.getX() * Math.cos(t);
            double y = pc.getY() + radius.getX() * Math.sin(t);
            pixel.drawPixel((int)Math.round(x), (int)Math.round(y));
        }
    }
}
