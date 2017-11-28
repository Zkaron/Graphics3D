package Lines;

import java.awt.Container;
import java.awt.Graphics;

/**
 * Created by erik on 4/2/17.
 * Uses the Bresenham's line algorithm to calculate the position
 * of each pixel to draw
 */
public class BresenhamLine extends AbstractLine {
    /**
     *
     * Constructor used for light use, only needs a frame or panel
     * where to draw
     * @param context the container to use
     */
    public BresenhamLine(Container context) {
        super();
        pixel.setGraphics(context.getGraphics());
    }

    /**
     * Constructor used for full control of the drawing mode
     * (for example double buffering)
     * @param graphics the container's graphics
     */
    public BresenhamLine(Graphics graphics) {
        super();
        pixel.setGraphics(graphics);
    }

    /**
     * Uses the bresenham's line algorithm to calculate the position of
     * each pixel, also uses the mask function to get different types
     * of lines
     */
    @Override
    public void drawingMethod() {
        int x0 = (int)Math.round(p0_2D.getX());
        int y0 = (int)Math.round(p0_2D.getY());
        int x1 = (int)Math.round(p1_2D.getX());
        int y1 = (int)Math.round(p1_2D.getY());

        int dx = (x1 - x0) < 0 ? (x0 - x1) : (x1 - x0);
        int dy = (y1 - y0) < 0 ? (y0 - y1) : (y1 - y0);

        int xinc = (p0_2D.getX() < p1_2D.getX()) ? 1 : -1;
        int yinc = (p0_2D.getY() < p1_2D.getY()) ? 1 : -1;

        drawWithWidth(x0, y0);

        if(dx > dy) {
            int A = 2 * dy;
            int B = (2 * dy) - (2 * dx);
            int pk = A - dx;

//            createMaskByType(dx);
            for(int xk = x0, i = 0, yk = y0; xk != x1; xk += xinc, i++) {
                if (pk < 0) {
                    pk += A;
                } else {
                    yk += yinc;
                    pk += B;
                }
//                if(mask[i]) {
                    drawWithWidth(xk, yk);
//                }
            }
        } else {
            int A = 2 * dx;
            int B = (2 * dx) - (2 * dy);
            double pk = A - dy;
//            createMaskByType(dy);
            for(int yk = y0, i = 0, xk = x0; yk != y1; yk += yinc, i++) {
                if (pk < 0) {
                    pk += A;
                } else {
                    xk += xinc;
                    pk += B;
                }
//                if (mask[i]) {
                    drawWithWidth(xk, yk);
//                }
            }
            drawWithWidth(x1, y1);
        }
    }

    private void createBuffer(Container context, Graphics graphics) {

    }
    /*public void drawLine(int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;

        int error;
        int xinc = 1, yinc = 1;

        if (dy < 0) {
            dy = -dy;
            yinc = -1;
        }
        if (dx < 0) {
            dx = -dx;
            xinc = -1;
        }

        dy <<= 1;
        dx <<= 1;

        pixel.drawPixel(x0, y0);
        if (dx > dy) {
            error = dy - (dx >> 1);

            while (x0 != x1) {
                if (error >= 0) {
                    y0 += yinc;
                    error -= dx;
                }
                x0 += xinc;
                error += dy;
                pixel.drawPixel(x0, y0);
            }
        } else {
            error = dx - (dy >> 1);
            while (y0 != y1) {
                if (error >= 0) {
                    x0 += xinc;
                    error -= dy;
                }
                y0 += yinc;
                error += dx;
                pixel.drawPixel(x0, y0);
            }
        }
    }*/
}
