package ProjectHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import Figures.AbstractCircle;
import Figures.BasicCircle;
import Figures.Rectangle;
import Lines.*;

import javax.swing.*;


/**
 * Created by erik on 4/25/17.
 */
public class PaintPanel extends JPanel implements MouseListener {
    private int xOrigin, yOrigin;
    private int xMousePressed, yMousePressed;
    private int xPrevDragged, yPrevDragged;
    private int xMouseReleased, yMouseReleased;
    //private LinkedList<BresenhamLine> lineLinkedList;
    private AbstractLine line;
    private Rectangle rect;
    private AbstractCircle circle;
    private BufferedImage image;
    private static int drawingTool = 0;

    public PaintPanel() {
        setSize(1000, 500);
        xOrigin = -1;
        yOrigin = -1;
        xPrevDragged = -1;
        yPrevDragged = -1;
        xMousePressed = 0;
        yMousePressed = 0;
        line = new BresenhamLine(this);
        rect = new Rectangle(this);
        circle = new BasicCircle(this);
        //lineLinkedList = new LinkedList<>();
        addMouseListener(this);
        //addMouseMotionListener(this);
    }

    public static void main (String[] args) {
        PaintPanel frame = new PaintPanel();
    }

    public static void setDrawingTool(int tool) {
        drawingTool = tool;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(xOrigin == -1) {
            line.setColor(Color.GREEN);
            //line.setLineColor(Color.GREEN);
            xOrigin = mouseEvent.getX();
            yOrigin = mouseEvent.getY();
        }
        xMousePressed = mouseEvent.getX();
        yMousePressed = mouseEvent.getY();

        //int actX = mouseEvent.getX();
        //int actY = mouseEvent.getY();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        xMouseReleased = mouseEvent.getX();
        yMouseReleased = mouseEvent.getY();

        switch (drawingTool) {
            case 0:
                line.setColor(Color.BLUE);
                line.drawLine(new Point(xOrigin, yOrigin), new Point(xMouseReleased, yMouseReleased));
                break;
            case 1:
                line.setColor(Color.BLUE);
                rect.drawRectangle(new Point(xOrigin, yOrigin), new Point(xMouseReleased, yMouseReleased));
                break;
            case 2:
                circle.setColor(Color.BLUE);
                /*int xc = Math.round(xOrigin - xMouseReleased) + xOrigin;
                int yc = Math.round(yOrigin - yMouseReleased) + yOrigin;
                int radius = Math.abs(xOrigin - xMouseReleased);
                circle.drawCircle(xc, yc, radius);*/
                default:
                    System.out.println("No tool selected, strange...");
                    break;
        }

        /*if(SwingUtilities.isRightMouseButton(mouseEvent)) {
            line.setColor(Color.BLUE);
            rect.drawRectangle(xOrigin, yOrigin, xMouseReleased, yMouseReleased);
        } else {
            try {
                File outFile = new File("CoordenadasDibujo.coords");
                FileWriter outWrite = new FileWriter(outFile, true);
            //outWrite.append("Coordinates: P1[" + xOrigin + ", " + yOrigin + "], " +
            //        "P2[" + xMouseReleased + "," + yMouseReleased + "]\n");
                outWrite.append("" + xOrigin + ", " + yOrigin + ", " +
                        +xMouseReleased + ", " + yMouseReleased + "\n");
                outWrite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            line.setColor(Color.BLUE);
            line.drawLine(xOrigin, yOrigin, xMouseReleased, yMouseReleased);
        }*/
        xOrigin = -1; yOrigin = -1;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /*@Override
    public void mouseDragged(MouseEvent mouseEvent) {
            if(xOrigin != -1 || yOrigin != -1) {
                lineLinkedList.add(new BresenhamLine(this));
                lineLinkedList.getLast().setColor(Color.GREEN);
                if (xMousePressed != mouseEvent.getX() ||
                        yMousePressed != mouseEvent.getY()) {
                    //if(xPrevDragged != -1 && (xPrevDragged != mouseEvent.getX() || yPrevDragged != mouseEvent.getY())) {
                        //line.eraseLine(xMousePressed, yMousePressed, xPrevDragged, yPrevDragged, pixel.getBackground());
                        //pixel.setPixelColor(Color.GREEN);
                    //}

                    //line.drawLine(xMousePressed, yMousePressed, mouseEvent.getX(), mouseEvent.getY());
                    if(lineLinkedList.size() == 1) {
                        lineLinkedList.getFirst().drawLineTmp(xMousePressed, yMousePressed, mouseEvent.getX(), mouseEvent.getY());
                        lineLinkedList.add(new BresenhamLine(this));
                        lineLinkedList.getLast().setColor(Color.GREEN);
                    }
                    lineLinkedList.removeFirst();
                    lineLinkedList.getFirst().drawLineTmp(xMousePressed, yMousePressed, mouseEvent.getX(), mouseEvent.getY());
                    if(xPrevDragged != mouseEvent.getX() || yPrevDragged != mouseEvent.getY()) {
                        revalidate();
                        repaint();
                    }
                    xPrevDragged = mouseEvent.getX();
                    yPrevDragged = mouseEvent.getY();
                }
            }
        }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }*/
}
