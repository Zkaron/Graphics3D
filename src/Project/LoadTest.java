package Project;

import Core.MyJFrame;
import Core.Point3D;
import Figures.Rectangle;
import Lines.AbstractLine;
import Lines.BresenhamLine;
import Transformations.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Erik on 12/3/2017.
 */
public class LoadTest extends MyJFrame implements KeyListener {
    private final String FRAME_TITLE = "Cube scaled";
    private JPanel panel;
    private BufferedImage buffImage;
    private Graphics g2;
    private Rectangle fillRect;
    private AbstractLine line;
    private Point3D adjusment;
    private Transformation transformation;
    private final String[] transformationKeys= {"translation", "scalation", "rotation"};
    private String transformKeySelected;
    public Model kodama, gwen;

    public LoadTest()  {
        setTitle(FRAME_TITLE);
        setSize(1000, 1000);
        addKeyListener(this);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));

        adjusment = new Point3D(-1000, -1000, -2000);
        line = new BresenhamLine(g2);
        transformation = new Transformation();
        transformKeySelected = transformationKeys[0];
        kodama = null;
        gwen = null;
    }

    public ArrayList<Point3D> firstScalationOfModel(ArrayList<Point3D> m) {
        ArrayList<Point3D> translatedModel = new ArrayList<>(m);
        translatedModel = transformation.scale(translatedModel, new Point3D(20, 20, 20));
//        translatedModel = transformation.scale(translatedModel, new Point3D(0.5, 0.5, 0.5));
        return translatedModel;
    }

    public ArrayList<Point3D> firstTranslationOfModel(ArrayList<Point3D> m) {
        ArrayList<Point3D> translatedModel = new ArrayList<>(m);
        translatedModel = transformation.translate(translatedModel, new Point3D(500, 400, 0));
//        translatedModel = transformation.translate(translatedModel, new Point3D(460, 650, 3000));
        return translatedModel;
    }

    public ArrayList<Point3D> firstRotationOfModel(ArrayList<Point3D> m) {
        ArrayList<Point3D> rotatedModel = new ArrayList<>(m);
        rotatedModel = transformation.rotateX(rotatedModel, 90);
        rotatedModel = transformation.rotateY(rotatedModel, 90);
        rotatedModel = transformation.rotateZ(rotatedModel, 180);

        return rotatedModel;
    }

    public void draw3DModel(Model m) {
        if(m.faces.get(0).vertex.index3 == Integer.MAX_VALUE) {
            for (int i = 0; i < m.vertices.size() - 1; i++) {   //Vertices where parsed as duals
                line.drawLine3D(m.vertices.get(m.faces.get(i).vertex.index1 - 1), m.vertices.get(m.faces.get(i).vertex.index2 - 1));
                line.drawLine3D(m.vertices.get(m.faces.get(i).vertex.index2 - 1), m.vertices.get(m.faces.get(i + 1).vertex.index1 - 1));
                line.drawLine3D(m.vertices.get(m.faces.get(i + 1).vertex.index1), m.vertices.get(m.faces.get(i + 1).vertex.index2 - 1));
//                panel.getGraphics().drawImage(buffImage, 0, 0, panel);
            }
        }else {   //Vertices where parsed as thrice enrolled
            for(int i = 0; i < m.faces.size(); i++) {
                line.drawLine3D(m.vertices.get(m.faces.get(i).vertex.index1 - 1), m.vertices.get(m.faces.get(i).vertex.index2 - 1));
                line.drawLine3D(m.vertices.get(m.faces.get(i).vertex.index2 - 1), m.vertices.get(m.faces.get(i).vertex.index3 - 1));
            }
        }

        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public void loadAndDrawKodama() {
        ObjLoader loader = new ObjLoader();
        String resourcesLocation = System.getProperty("user.dir")
                + File.separator + "src" + File.separator +
                "Project" + File.separator + "resources" + File.separator;
        File wavefrontFile = new File(resourcesLocation + "kodama.obj");
        try {
            kodama = loader.load(wavefrontFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        kodama.vertices = firstScalationOfModel(kodama.vertices);
        kodama.vertices = firstTranslationOfModel(kodama.vertices);
        kodama.vertices = firstRotationOfModel(kodama.vertices);
        draw3DModel(kodama);
    }

    public void loadAndDrawCar() {
        ObjLoader loader = new ObjLoader();
        String resourcesLocation = System.getProperty("user.dir")
                + File.separator + "src" + File.separator +
                "Project" + File.separator + "resources" + File.separator;
        File wavefrontFile = new File(resourcesLocation + "car.obj");
        try {
            kodama = loader.load(wavefrontFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        kodama.vertices = firstScalationOfModel(kodama.vertices);
        kodama.vertices = firstTranslationOfModel(kodama.vertices);
        kodama.vertices = firstRotationOfModel(kodama.vertices);
        draw3DModel(kodama);
    }

    public void loadAndDrawFalcon() {
        ObjLoader loader = new ObjLoader();
        String resourcesLocation = System.getProperty("user.dir")
                + File.separator + "src" + File.separator +
                "Project" + File.separator + "resources" + File.separator;
        File wavefrontFile = new File(resourcesLocation + "millenium-falcon.obj");
        try {
            kodama = loader.load(wavefrontFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        kodama.vertices = firstTranslationOfModel(kodama.vertices);
        kodama.vertices = firstScalationOfModel(kodama.vertices);
//        kodama.vertices = firstTranslationOfModel(kodama.vertices);
        kodama.vertices = firstRotationOfModel(kodama.vertices);
        draw3DModel(kodama);
    }

    public void loadAndDrawGwen() {
        ObjLoader loader = new ObjLoader();
        String resourcesLocation = System.getProperty("user.dir")
                + File.separator + "src" + File.separator +
                "Project" + File.separator + "resources" + File.separator;
        File wavefrontFile = new File(resourcesLocation + "gwen_body.obj");
        try {
            kodama = loader.load(wavefrontFile);
            kodama.vertices = firstScalationOfModel(kodama.vertices);
            kodama.vertices = firstTranslationOfModel(kodama.vertices);
            kodama.vertices = firstRotationOfModel(kodama.vertices);
            draw3DModel(kodama);

//            wavefrontFile = new File(resourcesLocation + "gwen_hair.obj");
//            gwen = loader.load(wavefrontFile);
//            gwen.vertices = firstScalationOfModel(gwen.vertices);
//            gwen.vertices = transformation.translate(gwen.vertices, new Point3D(320, 380, 30));
//            gwen.vertices = firstRotationOfModel(gwen.vertices);
//            draw3DModel(gwen);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void loadAndDrawInvader() {
        ObjLoader loader = new ObjLoader();
        String resourcesLocation = System.getProperty("user.dir")
                + File.separator + "src" + File.separator +
                "Project" + File.separator + "resources" + File.separator;
        File wavefrontFile = new File(resourcesLocation + "medium_invader.obj");
        try {
            kodama = loader.load(wavefrontFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        kodama.vertices = firstScalationOfModel(kodama.vertices);
        kodama.vertices = firstTranslationOfModel(kodama.vertices);
        kodama.vertices = firstRotationOfModel(kodama.vertices);
        draw3DModel(kodama);
    }

    private void clear(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    public static void main(String[] args) {
        LoadTest test = new LoadTest();
//        test.loadAndDrawKodama();
//        test.loadAndDrawCar();
//        test.loadAndDrawFalcon();
//        test.loadAndDrawGwen();
        test.loadAndDrawInvader();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(transformKeySelected.equals(transformationKeys[0])) {   //traslate

            if(key == KeyEvent.VK_RIGHT) {
                kodama.vertices = transformation.translate(kodama.vertices, new Point3D(5, 0, 0));
//                gwen.vertices = transformation.translate(gwen.vertices, new Point3D(5, 0, 0));

            } else if(key == KeyEvent.VK_LEFT) {
                kodama.vertices = transformation.translate(kodama.vertices, new Point3D(-5, 0, 0));
//                gwen.vertices = transformation.translate(gwen.vertices, new Point3D(-5, 0, 0));

            } else if(key == KeyEvent.VK_UP) {
                kodama.vertices = transformation.translate(kodama.vertices, new Point3D(0, -5, 0));
//                gwen.vertices = transformation.translate(gwen.vertices, new Point3D(0, -5, 0));

            }else  if(key == KeyEvent.VK_DOWN) {
                kodama.vertices = transformation.translate(kodama.vertices, new Point3D(0, 5, 0));
//                gwen.vertices = transformation.translate(gwen.vertices, new Point3D(0, 5, 0));


            }
        } else if(transformKeySelected.equals(transformationKeys[1])) {   //scale
            if(key == KeyEvent.VK_UP) {
                kodama.vertices = transformation.scale(kodama.vertices, new Point3D(1.1, 1.1, 1.1));
            }
            else if(key == KeyEvent.VK_DOWN) {
                kodama.vertices = transformation.scale(kodama.vertices, new Point3D(0.9, 0.9, 0.9));
            }

        } else if(transformKeySelected.equals(transformationKeys[2])) {  //rotation
            if(key == KeyEvent.VK_X) {
                kodama.vertices = transformation.rotateX(kodama.vertices, 5);
//                gwen.vertices = transformation.rotateX(gwen.vertices, 5);

            } else if(key == KeyEvent.VK_Y) {
                kodama.vertices = transformation.rotateY(kodama.vertices, 5);
//                gwen.vertices = transformation.rotateY(gwen.vertices, 5);

            } else if(key == KeyEvent.VK_Z) {
                kodama.vertices = transformation.rotateZ(kodama.vertices, 5);
//                gwen.vertices = transformation.rotateZ(gwen.vertices, 5);

            }
        }

        clear(g2);
        draw3DModel(kodama);
//        draw3DModel(gwen);
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
