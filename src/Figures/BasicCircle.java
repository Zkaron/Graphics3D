package Figures;

import Core.Pixel;
import Core.MyJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by erik on 4/19/17.
 */
public class BasicCircle extends AbstractCircle {
    public BasicCircle(JPanel context) {
      super();
      pixel.setContext(context);
      pixel.setGraphics(context.getGraphics());
    }

    public BasicCircle(JPanel context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    public void drawingMethod() {
        for(int x = (int)pc.getX() - (int)radius.getX(); x <= (int)pc.getX() + (int)radius.getX(); x++) {
            double y = pc.getY() + Math.sqrt((radius.getX() * radius.getX()) - Math.pow((x - pc.getX()), 2));
            drawWithWidth(x, (int)Math.round(y));
            y = pc.getY() - Math.sqrt((radius.getX() * radius.getX()) - Math.pow((x - pc.getX()), 2));
            drawWithWidth(x, (int)Math.round(y));
        }
    }
}
