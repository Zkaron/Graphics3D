package Testing;

import Core.MyJFrame;
import Core.Pixel;
import Figures.Rectangle;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class BufferSecrets extends MyJFrame {
    private JPanel panel;
    private Graphics gBackground;
    private Graphics g2;
    private BufferedImage buffImage;
    private BufferedImage buffBackground;

    private Pixel pixel;
    private Rectangle rect;
    private AbstractLine line;

    public BufferSecrets() {
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
        this.setVisible(true);

        buffBackground = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        gBackground = buffBackground.createGraphics();
        gBackground.setColor(panel.getBackground());
        gBackground.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2 = buffImage.createGraphics();

        pixel = new Pixel();
        pixel.setGraphics(gBackground);

        rect = new Rectangle(panel, gBackground);
        line = new BresenhamLine(panel, gBackground);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton() == MouseEvent.BUTTON3) {
                    panel.getGraphics().drawImage(buffBackground, 0, 0, panel);
                }
                pixel.drawPixel(e.getX(), e.getY());
            }
        });
    }

    public void drawTestFigures() {
        rect.drawRectangle(new Point(50, 50), new Point(100, 100));
        line.drawLine(new Point(100, 100), new Point(80, 140));
        line.drawLine(new Point(80, 140), new Point(120, 100));
        line.drawLine(new Point(100, 100), new Point(120, 100));
        rect.drawRectangle(new Point(150, 150), new Point(400, 400));
        rect.drawRectangle(new Point(250, 150), new Point(300, 300));
        panel.getGraphics().drawImage(buffBackground, 0, 0, panel);
    }

    public static void main(String[] args) {
        BufferSecrets secrets = new BufferSecrets();
        secrets.drawTestFigures();
    }
}
