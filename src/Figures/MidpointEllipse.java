package Figures;

import Core.MyJFrame;
import Core.Pixel;

import java.awt.*;
import javax.swing.*;

/**
 * Created by erik on 5/1/17.
 *
 * Algorithm used is in the following text:
 * Univ. Michoacana de San Nicolas de Hgo.
 * Facultad de Ingenieria Electrica
 * Notas de Graficacion
 * Jose Antonio Camarena Ibarrola
 * Marzo de 2010
 */
public class MidpointEllipse extends AbstractCircle {
    public MidpointEllipse(JPanel context) {
        super();
        pixel.setContext(context);
        pixel.setGraphics(context.getGraphics());
    }

    public MidpointEllipse(Container context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    @Override
    public void drawingMethod() {
        int x = 0;
        int y = radius.y;
        int r1sq = radius.x * radius.x;
        int r2sq = radius.y * radius.y;
        double px = 0;
        double py = 2 * r1sq * y;

        drawWithWidth(pc.x + x, pc.y + y);
        drawWithWidth(pc.x - x, pc.y + y);
        drawWithWidth(pc.x + x, pc.y - y);
        drawWithWidth(pc.x - x, pc.y - y);

        double p = r2sq - (r1sq * radius.y) + (0.25 * r1sq);
        while(px < py) {
            x++;
            px += 2 * r2sq;
            if(p < 0) {
                p += r2sq + px;
            } else {
                y--;
                py -= 2 * r1sq;
                p += r2sq + px - py;
            }
            drawWithWidth(pc.x + x, pc.y + y);
            drawWithWidth(pc.x - x, pc.y + y);
            drawWithWidth(pc.x + x, pc.y - y);
            drawWithWidth(pc.x - x, pc.y - y);
        }
        p = r2sq * Math.pow(x + 0.5, 2) + r1sq * Math.pow(y - 1, 2) - r1sq * r2sq;
        while (y > 0) {
            y--;
            py -= 2 * r1sq;
            if(p > 0) {
                p += r1sq - py;
            } else {
                x++;
                px += 2 * r2sq;
                p += r1sq - py + px;
            }
            drawWithWidth(pc.x + x, pc.y + y);
            drawWithWidth(pc.x - x, pc.y + y);
            drawWithWidth(pc.x + x, pc.y - y);
            drawWithWidth(pc.x - x, pc.y - y);
        }
    }

    public void drawUpperHalfCircle(Point pCenter, Point pRadius) {
        this.pc = pCenter;
        this.radius = pRadius;
        scale();
        int x = 0;
        int y = radius.y;
        int r1sq = radius.x * radius.x;
        int r2sq = radius.y * radius.y;
        double px = 0;
        double py = 2 * r1sq * y;

        drawWithWidth(pc.x + x, pc.y - y);
        drawWithWidth(pc.x - x, pc.y - y);

        double p = r2sq - (r1sq * radius.y) + (0.25 * r1sq);
            while(px < py) {
            x++;
            px += 2 * r2sq;
            if(p < 0) {
                p += r2sq + px;
            } else {
                y--;
                py -= 2 * r1sq;
                p += r2sq + px - py;
            }
            drawWithWidth(pc.x + x, pc.y - y);
            drawWithWidth(pc.x - x, pc.y - y);
        }

        p = r2sq * Math.pow(x + 0.5, 2) + r1sq * Math.pow(y - 1, 2) - r1sq * r2sq;
        while (y > 0) {
            y--;
            py -= 2 * r1sq;
            if(p > 0) {
                p += r1sq - py;
            } else {
                x++;
                px += 2 * r2sq;
                p += r1sq - py + px;
            }
            drawWithWidth(pc.x + x, pc.y - y);
            drawWithWidth(pc.x - x, pc.y - y);
        }
    }

    public void drawLowerHalfCircle(Point pCenter, Point pRadius) {
        pc = pCenter;
        radius = pRadius;
        scale();
        int x = 0;
        int y = radius.y;
        int r1sq = radius.x * radius.x;
        int r2sq = radius.y * radius.y;
        double px = 0;
        double py = 2 * r1sq * y;

        drawWithWidth(pc.x + x, pc.y + y);
        drawWithWidth(pc.x - x, pc.y + y);

        double p = r2sq - (r1sq * radius.y) + (0.25 * r1sq);
        while(px < py) {
            x++;
            px += 2 * r2sq;
            if(p < 0) {
                p += r2sq + px;
            } else {
                y--;
                py -= 2 * r1sq;
                p += r2sq + px - py;
            }
            drawWithWidth(pc.x + x, pc.y + y);
            drawWithWidth(pc.x - x, pc.y + y);
        }

        p = r2sq * Math.pow(x + 0.5, 2) + r1sq * Math.pow(y - 1, 2) - r1sq * r2sq;
        while (y > 0) {
            y--;
            py -= 2 * r1sq;
            if(p > 0) {
                p += r1sq - py;
            } else {
                x++;
                px += 2 * r2sq;
                p += r1sq - py + px;
            }
            drawWithWidth(pc.x + x, pc.y + y);
            drawWithWidth(pc.x - x, pc.y + y);
        }
    }

    public void drawLeftHalfCircle(Point pCenter, Point pRadius) {
        pc = pCenter;
        radius = pRadius;
        scale();
        int x = 0;
        int y = radius.y;
        int r1sq = radius.x * radius.x;
        int r2sq = radius.y * radius.y;
        double px = 0;
        double py = 2 * r1sq * y;

        drawWithWidth(pc.x - x, pc.y + y);
        drawWithWidth(pc.x - x, pc.y - y);

        double p = r2sq - (r1sq * radius.y) + (0.25 * r1sq);
        while(px < py) {
            x++;
            px += 2 * r2sq;
            if(p < 0) {
                p += r2sq + px;
            } else {
                y--;
                py -= 2 * r1sq;
                p += r2sq + px - py;
            }
            drawWithWidth(pc.x - x, pc.y + y);
            drawWithWidth(pc.x - x, pc.y - y);
        }
        p = r2sq * Math.pow(x + 0.5, 2) + r1sq * Math.pow(y - 1, 2) - r1sq * r2sq;
        while (y > 0) {
            y--;
            py -= 2 * r1sq;
            if(p > 0) {
                p += r1sq - py;
            } else {
                x++;
                px += 2 * r2sq;
                p += r1sq - py + px;
            }
            drawWithWidth(pc.x - x, pc.y + y);
            drawWithWidth(pc.x - x, pc.y - y);
        }
    }

    public void drawRightHalfCircle(Point pCenter, Point pRadius) {
        pc = pCenter;
        radius = pRadius;
        scale();
        int x = 0;
        int y = radius.y;
        int r1sq = radius.x * radius.x;
        int r2sq = radius.y * radius.y;
        double px = 0;
        double py = 2 * r1sq * y;

        drawWithWidth(pc.x + x, pc.y + y);
        drawWithWidth(pc.x + x, pc.y - y);

        double p = r2sq - (r1sq * radius.y) + (0.25 * r1sq);
        while(px < py) {
            x++;
            px += 2 * r2sq;
            if(p < 0) {
                p += r2sq + px;
            } else {
                y--;
                py -= 2 * r1sq;
                p += r2sq + px - py;
            }
            drawWithWidth(pc.x + x, pc.y + y);
            drawWithWidth(pc.x + x, pc.y - y);
        }
        p = r2sq * Math.pow(x + 0.5, 2) + r1sq * Math.pow(y - 1, 2) - r1sq * r2sq;
        while (y > 0) {
            y--;
            py -= 2 * r1sq;
            if(p > 0) {
                p += r1sq - py;
            } else {
                x++;
                px += 2 * r2sq;
                p += r1sq - py + px;
            }
            drawWithWidth(pc.x + x, pc.y + y);
            drawWithWidth(pc.x + x, pc.y - y);
        }
    }
}

