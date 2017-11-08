package Core;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The core class of this project, draws a single pixel in the
 * desired location
 */
public class Pixel {
    private BufferedImage buffer;
    private Graphics graPixel;
    private Container context;
    private Color color;

    /**
     * Constructs a pixel object
     */
    public Pixel() {
        this.color = Color.BLUE; //Default color
       
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    /**
     * Draws a pixel given the coordinates, uses the actual
     * graphics and color
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void drawPixel(int x, int y) {
        buffer.setRGB(0, 0, color.getRGB());
        graPixel.drawImage(buffer, x, y, null);
    }

    /**
     * Set the color for the pixel
     * @param color the color
     */
    public void setPixelColor(Color color) {
        this.color = color;
    }

    public Color getPixelColor() {
        return color;
    }

    /**
     * Set a Container where to draw
     * @param context the Container
     */
    public void setContext(Container context) {
        this.context = context;
    }

    /**
     * Set the Container's Graphics
     * @param g Container's Graphics
     */
    public void setGraphics(Graphics g) {
        this.graPixel = g;
    }
}
