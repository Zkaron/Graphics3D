package Lines;

import javax.swing.*;
import java.awt.*;

public class DDALine extends AbstractLine {
    private int dx, dy;
    private float xinc, yinc;

    public DDALine(Container context) {
        super();
        pixel.setContext(context);
        pixel.setGraphics(context.getGraphics());
    }

    public DDALine(JPanel context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    public void drawingMethod(){
        dx = (int)p1.getX() - (int)p0.getX();
        dy = (int)p1.getY() - (int)p0.getY();
        int steps = (Math.abs(dx) > Math.abs(dy)) ? Math.abs(dx) : Math.abs(dy);
        xinc = dx / (float)steps;
        yinc = dy / (float)steps;

        float x = (float)p0.getX();
        float y = (float)p0.getY();
        pixel.drawPixel(Math.round(x), Math.round(y));
        for(int k = 1; k <= steps; k++) {
            x = x + xinc;
            y = y + yinc;
            pixel.drawPixel(Math.round(x), Math.round(y));
        }
    }
    /*public void eraseLine(int x0, int y0, int x1, int y1, Color color) {
        dx = x1 - x0;
        dy = y1 - y0;
        int steps = (Math.abs(dx) > Math.abs(dy)) ? Math.abs(dx) : Math.abs(dy);
        xinc = dx / (float)steps;
        yinc = dy / (float)steps;

        float x = x0;
        float y = y0;
        pixel.drawPixel(Math.round(x), Math.round(y), color);
        for(int k = 1; k <= steps; k++) {
            x = x + xinc;
            y = y + yinc;
            pixel.drawPixel(Math.round(x), Math.round(y), color);
        }
    }*/
}
