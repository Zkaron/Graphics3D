package Figures;

import Core.MyJFrame;
import Core.Pixel;

import java.awt.*;
import javax.swing.*;

/**
 * Created by erik on 5/1/17.
 */
public class PolarCoordsEllipse extends AbstractCircle{
    public PolarCoordsEllipse(JPanel context) {
        super();
        pixel.setContext(context);
        pixel.setGraphics(context.getGraphics());
    }

    public PolarCoordsEllipse(Container context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    @Override
    public void drawingMethod(){
        double inc = (radius.getX() > radius.getY()) ? 1.0 / radius.getX() : 1.0 / radius.getY();
        for(double t = 0; t <= 2 * Math.PI + 0.1; t += inc) {
            double x = pc.getX() + radius.getX() * Math.cos(t);
            double y = pc.getY() + radius.getY() * Math.sin(t);
            //pixel.drawPixel((int)Math.round(x), (int)Math.round(y));
            drawWithWidth((int)Math.round(x), (int)Math.round(y));
        }
        //drawWithWidth((int)Math.round(x), (int)Math.round(y), radius.getX(), radius.getY());
    }
}