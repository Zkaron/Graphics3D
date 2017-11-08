package Lines;

import javax.swing.*;
import java.awt.*;

public class BasicLine extends AbstractLine {
  public BasicLine(Container context) {
      super();
      pixel.setContext(context);
      pixel.setGraphics(context.getGraphics());
  }

    public BasicLine(JPanel context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

   public void drawingMethod() {
       // y = mx + b
       //Primero detecta el largo de cada linea
       int xLength = getDifference((int)p0.getX(), (int)p1.getX());
       int yLength = getDifference((int)p0.getY(), (int)p1.getY());

       //Decide si dibujar desde x o desde y
       if (xLength >= yLength) {
           if((int)p0.getX() > (int)p1.getX()) {
               int temp = (int)p0.getX();
               p0.setLocation(p1.getX(), p0.getY());
               //p0.getX() = (int)p1.getX();
               p1.setLocation(temp, p1.getY());
               //p1.getX() = temp;
               temp = (int)p0.getY();
               p0.setLocation(p0.getX(), p1.getY());
               //(int)p0.getY() = (int)p1.getY();
               p1.setLocation(p1.getX(), temp);
               //(int)p1.getY() = temp;
           }
           for(int x = (int)p0.getX(); x != (int)p1.getX(); x++){
           int m = Math.round(((int)p1.getY() - (int)p0.getY()) / ((int)p1.getX() - (int)p0.getX()));
           int b = (int)p0.getY() - (m * (int)p0.getX());
           int y = m * x + b;
           pixel.drawPixel(x, y);
           }
       } else {
            if((int)p0.getY() > (int)p1.getY()) {
               int temp = (int)p0.getY();
               p0.setLocation(p0.getX(), p1.getY());
               //(int)p0.getY() = (int)p1.getY();
                p1.setLocation(p1.getX(), temp);
               //(int)p1.getY() = temp;
               temp = (int)p0.getX();
               p0.setLocation(p1.getX(), p0.getY());
               //(int)p0.getX() = (int)p1.getX();
                p1.setLocation(temp, p1.getY());
               //(int)p1.getX() = temp;
           }
            for(int y = (int)p0.getY(); y != (int)p1.getY(); y++){
            int m = (int)p1.getX() != (int)p0.getX() ? Math.round(((int)p1.getY() - (int)p0.getY()) / ((int)p1.getX() - (int)p0.getX())) : Math.round((int)p1.getY() - (int)p0.getY());
            int b = (int)p0.getY() - (m * (int)p0.getX());
            int x = (y - b) / m;
            pixel.drawPixel(x, y);
        }
       }
   }
   private int getDifference(int axis1, int axis2) {
       return Math.abs(axis1 - axis2);
   }

}
