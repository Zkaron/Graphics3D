package Lines;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


/**
 * Created by erik on 4/24/17.
 */
public class MidPointLine extends AbstractLine {

  public MidPointLine(Container context) {
      super();
      pixel.setContext(context);
      pixel.setGraphics(context.getGraphics());
  }

    public MidPointLine(JPanel context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    public void drawingMethod() {
        int dx = ((int)p1.getX() - (int)p0.getX()) < 0 ? ((int)p0.getX() - (int)p1.getX()) : ((int)p1.getX() - (int)p0.getX());
        int dy = ((int)p1.getY() - (int)p0.getY()) < 0 ? ((int)p0.getY() - (int)p1.getY()) : ((int)p1.getY() - (int)p0.getY());
        int xinc = (p0.getX() < p1.getX()) ? 1 : -1;
        int yinc = (p0.getY() < p1.getY()) ? 1 : -1;

        //pixel.drawPixel(x0, y0);
        drawWithWidth((int)p0.getX(), (int)p0.getY());

        if(dx > dy) {
            int A = dy / 2;
            int B = (dy / 2) - (dx / 2);
            int pk = A - dx;

            createMaskByType(dx);
            for(int xk = (int)p0.getX(), i = 0, yk = (int)p0.getY(); xk != (int)p1.getX(); xk += xinc, i++) {
                if (pk < 0) {
                    pk += A;
                } else {
                    yk += yinc;
                    pk += B;
                }
                if(mask[i]) {
                    //pixel.drawPixel(xk, yk);
                    drawWithWidth(xk, yk);
                }
            }
        } else {
            int A = dx / 2;
            int B = (dx / 2) - (dy / 2);
            double pk = A - dy;
            createMaskByType(dy);
            for(int yk = (int)p0.getY(), i = 0, xk = (int)p0.getX(); yk != (int)p1.getY(); yk += yinc, i++) {
                if (pk < 0) {
                    pk += A;
                } else {
                    xk += xinc;
                    pk += B;
                }
                //pixel.drawPixel(xk, yk);
                if (mask[i]) {
                    drawWithWidth(xk, yk);
                }
            }
            drawWithWidth((int)p1.getX(), (int)p1.getY());
        }
    }

}
