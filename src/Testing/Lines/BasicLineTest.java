package Testing.Lines;

import Core.MyJFrame;
import Lines.AbstractLine;
import Lines.BasicLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
/**
 * Created by Erik on 7/6/2017.
 */
public class BasicLineTest extends MyJFrame{
  private final String FRAME_TITLE = "Bresenham Line";
  public LinkedList<Point> linePointsVector;
  private JPanel panel;
  private BufferedImage offScreen;
  private AbstractLine line;
  private int imageWidth;
  private int imageHeight;

  public BasicLineTest() {
      setTitle(FRAME_TITLE);
      panel = new JPanel();
      add(panel);
      this.setVisible(true);  //is set visible again because a new element has been added
      linePointsVector = new LinkedList<>();
  }

  public void drawSomethingCool() {
      for(int p0 = 0, p1 = 1; p1 < linePointsVector.size(); p0 += 2, p1 += 2){
          line.drawLine(linePointsVector.get(p0), linePointsVector.get(p1));
      }
  }

  public void setCoolPoints() {
      linePointsVector.add(new Point(100, 50));
      linePointsVector.add(new Point(50, 100));
      linePointsVector.add(new Point(150, 50));
      linePointsVector.add(new Point(200, 100));
      linePointsVector.add(new Point(100, 200));
      linePointsVector.add(new Point(50, 200));
      linePointsVector.add(new Point(150, 200));
      linePointsVector.add(new Point(200, 200));
      linePointsVector.add(new Point(100, 300));
      linePointsVector.add(new Point(100, 350));
      linePointsVector.add(new Point(200, 350));
      linePointsVector.add(new Point(200, 300));
  }


  public void paint() {
      Dimension d = panel.getSize();
      if((offScreen == null) || (d.width != imageWidth)
              ||(d.height != imageHeight)) {
          if(d.width < 1 || d.height < 1) return;

          offScreen = new BufferedImage(panel.getWidth(),
                  panel.getHeight(), BufferedImage.TYPE_INT_RGB);
          imageWidth = d.width;
          imageHeight = d.height;
          line = new BasicLine(panel);
      }   //check if the screen hasn't been drawn or the window was resided

      Graphics offGraphics = offScreen.createGraphics();
      clear(offGraphics);
      line.setGraphics(offGraphics);
      drawSomethingCool();
      Graphics panelGraphics = panel.getGraphics();
      panelGraphics.drawImage(offScreen, 0, 0, panel);
  }

  public void paint(Graphics g) {
      paint();
  }

  private void clear(Graphics g) {
      g.setColor(panel.getBackground());
      g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
  }


    public static void main(String[] args) {
        BasicLineTest test = new BasicLineTest();
        test.setCoolPoints();
    }
}
