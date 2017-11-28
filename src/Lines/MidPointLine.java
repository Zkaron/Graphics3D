package Lines;

import javax.swing.*;
import java.awt.*;


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
        int dx = ((int) p1_2D.getX() - (int) p0_2D.getX()) < 0 ? ((int) p0_2D.getX() - (int) p1_2D.getX()) : ((int) p1_2D.getX() - (int) p0_2D.getX());
        int dy = ((int) p1_2D.getY() - (int) p0_2D.getY()) < 0 ? ((int) p0_2D.getY() - (int) p1_2D.getY()) : ((int) p1_2D.getY() - (int) p0_2D.getY());
        int xinc = (p0_2D.getX() < p1_2D.getX()) ? 1 : -1;
        int yinc = (p0_2D.getY() < p1_2D.getY()) ? 1 : -1;

        //pixel.drawPixel(x0, y0);
        drawWithWidth((int) p0_2D.getX(), (int) p0_2D.getY());

        if(dx > dy) {
            int A = dy / 2;
            int B = (dy / 2) - (dx / 2);
            int pk = A - dx;

            createMaskByType(dx);
            for(int xk = (int) p0_2D.getX(), i = 0, yk = (int) p0_2D.getY(); xk != (int) p1_2D.getX(); xk += xinc, i++) {
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
            for(int yk = (int) p0_2D.getY(), i = 0, xk = (int) p0_2D.getX(); yk != (int) p1_2D.getY(); yk += yinc, i++) {
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
            drawWithWidth((int) p1_2D.getX(), (int) p1_2D.getY());
        }
    }

}
