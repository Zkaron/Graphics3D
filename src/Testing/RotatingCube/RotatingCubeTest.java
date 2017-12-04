package Testing.RotatingCube;

import Core.MyJFrame;
import Core.Point3D;
import Figures.Rectangle;
import Figures3D.Cube;
import Transformations.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class RotatingCubeTest extends MyJFrame implements KeyListener {
    private final String FRAME_TITLE = "Rotating cube";
    private JPanel panel;
    private BufferedImage buffImage;
    private Rectangle fillRect;
    private Cube cube;
    private Graphics g2;
    private Point3D adjusment;
    private Transformation transformation;
    private final String[] transformationKeys= {"translation", "scalation", "rotation"};
    private String transformKeySelected;

    public RotatingCubeTest() {
        setTitle(FRAME_TITLE);
        addKeyListener(this);
//        setSize(1800, 1000);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));

        adjusment = new Point3D(0, 0, 4);
        cube = new Cube(g2);
        cube = new Cube(g2);
        cube.setPerspectiveType("parallel");
        cube.setPerspectiveAdjustment(adjusment);
        transformation = new Transformation();

        transformKeySelected = transformationKeys[0];
    }

    public void drawSomethingCool() {
        cube.modelCube(new Point3D(350, 300, 100), new Point3D(400, 400, 110), Cube.DEFAULT_ASPECT_RATIO);
        cube.drawModeledCube();

        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    private void clear(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    public static void main(String[] args) {
        RotatingCubeTest test = new RotatingCubeTest();
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
                cube.point3DArrayList = transformation.translate(cube.point3DArrayList, new Point3D(5, 0, 0));
            } else if(key == KeyEvent.VK_LEFT) {
                cube.point3DArrayList = transformation.translate(cube.point3DArrayList, new Point3D(-5, 0, 0));
            } else if(key == KeyEvent.VK_UP) {
                cube.point3DArrayList = transformation.translate(cube.point3DArrayList, new Point3D(0, -5, 0));
            }else  if(key == KeyEvent.VK_DOWN) {
                cube.point3DArrayList = transformation.translate(cube.point3DArrayList, new Point3D(0, 5, 0));

            }
        } else if(transformKeySelected.equals(transformationKeys[1])) {   //scale
            if(key == KeyEvent.VK_UP) {
                cube.point3DArrayList = transformation.scale(cube.point3DArrayList, new Point3D(1.1, 1.1, 1.1));
            }
            else if(key == KeyEvent.VK_DOWN) {
                cube.point3DArrayList = transformation.scale(cube.point3DArrayList, new Point3D(0.9, 0.9, 0.9));
            }

        } else if(transformKeySelected.equals(transformationKeys[2])) {  //rotation
            if(key == KeyEvent.VK_X) {
                cube.point3DArrayList = transformation.rotateX(cube.point3DArrayList, 5);
            } else if(key == KeyEvent.VK_Y) {
                cube.point3DArrayList = transformation.rotateY(cube.point3DArrayList, 5);
            } else if(key == KeyEvent.VK_Z) {
                cube.point3DArrayList = transformation.rotateZ(cube.point3DArrayList, 5);
            }
        }

        clear(g2);
        cube.drawModeledCube();
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
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
