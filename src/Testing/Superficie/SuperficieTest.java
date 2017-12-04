package Testing.Superficie;

import Core.MyJFrame;
import Core.Point3D;
import Figures.Rectangle;
import Superficie.Superficie;
import Transformations.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class SuperficieTest extends MyJFrame implements KeyListener {
    private final String FRAME_TITLE = "Curve";
    private JPanel panel;
    private BufferedImage buffImage;
    private Rectangle fillRect;
    private Superficie superficie;
    private Graphics g2;
    private Point3D adjusment;
    private Transformation transformation;
    private final String[] transformationKeys= {"translation", "scalation", "rotation"};
    private String transformKeySelected;

    Point3D drawFrom;

    public SuperficieTest() {
        setTitle(FRAME_TITLE);
        addKeyListener(this);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));

        adjusment = new Point3D(-60, -40,-80);
        superficie = new Superficie(g2);
        superficie.setPerspectiveType("perspective");
        superficie.setPerspectiveAdjustment(adjusment);
        transformation = new Transformation();

        transformKeySelected = transformationKeys[0];

    }

    public void drawSomethingCool() {
        drawFrom = new Point3D(500, 300, 0);
        superficie.drawSuperficie(drawFrom, 50, 80);
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        SuperficieTest test = new SuperficieTest();
        test.drawSomethingCool();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(transformKeySelected.equals(transformationKeys[0])) {   //traslate

            if(key == KeyEvent.VK_RIGHT) {
                superficie.point3DArrayList = transformation.translate(superficie.point3DArrayList, new Point3D(5, 0, 0));
            } else if(key == KeyEvent.VK_LEFT) {
                superficie.point3DArrayList = transformation.translate(superficie.point3DArrayList, new Point3D(-5, 0, 0));
            } else if(key == KeyEvent.VK_UP) {
                superficie.point3DArrayList = transformation.translate(superficie.point3DArrayList, new Point3D(0, -5, 0));
            }else  if(key == KeyEvent.VK_DOWN) {
                superficie.point3DArrayList = transformation.translate(superficie.point3DArrayList, new Point3D(0, 5, 0));

            }
        } else if(transformKeySelected.equals(transformationKeys[1])) {   //scale
            if(key == KeyEvent.VK_UP) {
                superficie.point3DArrayList = transformation.scale(superficie.point3DArrayList, new Point3D(1.1, 1.1, 1.1));
            }
            else if(key == KeyEvent.VK_DOWN) {
                superficie.point3DArrayList = transformation.scale(superficie.point3DArrayList, new Point3D(0.9, 0.9, 0.9));
            }

        } else if(transformKeySelected.equals(transformationKeys[2])) {  //rotation
            if(key == KeyEvent.VK_X) {
                superficie.point3DArrayList = transformation.rotateX(superficie.point3DArrayList, 5);
            } else if(key == KeyEvent.VK_Y) {
                superficie.point3DArrayList = transformation.rotateY(superficie.point3DArrayList, 5);
            } else if(key == KeyEvent.VK_Z) {
                superficie.point3DArrayList = transformation.rotateZ(superficie.point3DArrayList, 5);
            }
        }

        clear(g2);
        superficie.drawSuperficie(drawFrom, 50, 80);
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    private void clear(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_T) {
            transformKeySelected = transformationKeys[0]; //translation
        }else if(key == KeyEvent.VK_S) {
            transformKeySelected = transformationKeys[1]; //scalation
        } else if(key == KeyEvent.VK_R) {
            transformKeySelected = transformationKeys[2]; // rotation
        }
    }
}
