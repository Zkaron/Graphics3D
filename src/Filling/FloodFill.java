package Filling;

import Core.Pixel;
import Transformations.Scale;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Uses recursion to fill a shape, due to the nature of the java VM (virtual machine)
 * a linkedList (Stack) is used to simulate recursion and avoid VM overflowing
 */
public class FloodFill {
    private int clickedBgColor;
    private LinkedList<Point> recursiveStack;
    private BufferedImage buffImage;
    private int containerWidth, containerHeight;
    private Point selectedPoint;
    private double scaleX, scaleY;

    private Pixel pixel;

    public FloodFill(Container context, BufferedImage image) {
        pixel = new Pixel();
        pixel.setContext(context);
        buffImage = image;
        recursiveStack = new LinkedList<>();
        containerWidth = context.getWidth();
        containerHeight = context.getHeight();
        scaleX = 1;
        scaleY = 1;
    }

    public FloodFill(Container context, BufferedImage image, Graphics g2) {
        pixel = new Pixel();
        pixel.setContext(context);
        pixel.setGraphics(g2);
        buffImage = image;
        recursiveStack = new LinkedList<>();
        containerWidth = context.getWidth();
        containerHeight = context.getHeight();
        scaleX = 1;
        scaleY = 1;
    }

    /**
     * It just gets the color in RGB of the clicked point
     */
    private void initFillVariables() {
        clickedBgColor = buffImage.getRGB((int)selectedPoint.getX(), (int)selectedPoint.getY());
    }

    /**
     * Fills recursively the area,  uses a Stack to save each recursive call
     * and compares the color of each pixel to know where to paint
     */
    public void fill(Point clickedPoint) {
        selectedPoint = clickedPoint;
        scale();
        if (!(selectedPoint.getX() >= buffImage.getWidth() - 1 || selectedPoint.getY() >= buffImage.getHeight() - 1  //Check if the coordinate isn't out of bounds
                || selectedPoint.getX() <= 0 || selectedPoint.getY() <= 0)) {
            initFillVariables();
            if (!(clickedBgColor == pixel.getPixelColor().getRGB())) {  //if is the same color, do nothing
                recursiveStack.push(selectedPoint);

                while (recursiveStack.size() != 0) {
                    Point p = recursiveStack.pop();
                    if (!(p.getX() >= buffImage.getWidth() - 1 || p.getY() >= buffImage.getHeight() - 1  //Check if the coordinate isn't out of bounds
                            || p.getX() <= 0 || p.getY() <= 0)) {
                        if (buffImage.getRGB((int) p.getX(), (int) p.getY() + 1) == clickedBgColor) {
                            recursiveStack.add(new Point((int) p.getX(), (int) p.getY() + 1));
                            pixel.drawPixel((int) p.getX(), (int) p.getY() + 1);
                        }
                        if (buffImage.getRGB((int) p.getX() + 1, (int) p.getY()) == clickedBgColor) {
                            recursiveStack.add(new Point((int) p.getX() + 1, (int) p.getY()));
                            pixel.drawPixel((int) p.getX() + 1, (int) p.getY());
                        }
                        if (buffImage.getRGB((int) p.getX(), (int) p.getY() - 1) == clickedBgColor) {
                            recursiveStack.add(new Point((int) p.getX(), (int) p.getY() - 1));
                            pixel.drawPixel((int) p.getX(), (int) p.getY() - 1);
                        }
                        if (buffImage.getRGB((int) p.getX() - 1, (int) p.getY()) == clickedBgColor) {
                            recursiveStack.add(new Point((int) p.getX() - 1, (int) p.getY()));
                            pixel.drawPixel((int) p.getX() - 1, (int) p.getY());
                        }
                    }
                }
            } else {  //print that the same color was selected
//                System.out.println("Selected same color, doing nothing");
            }
        }
    }

    public void setGraphics(Graphics g2) {
        pixel.setGraphics(g2);
    }

    public Pixel getPixel() {
        return pixel;
    }

    public void setPixel(Pixel pixel) {
        this.pixel = pixel;
    }

    public void setColor(Color color) {
        pixel.setPixelColor(color);
    }

    private void scale() {
        Scale scale = new Scale();
        selectedPoint = scale.scale(selectedPoint, scaleX, scaleY);
    }

    public void setScale(double x, double y) {
        scaleX = x;
        scaleY = y;
    }
}
