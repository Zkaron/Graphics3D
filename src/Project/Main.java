package Project;

import Clipping.ExplicitClip;
import Core.MyJFrame;
import Figures.MidpointEllipse;
import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;
import Lines.MidPointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Main extends MyJFrame implements KeyListener, Runnable {

    private final String FRAME_TITLE = "Battle Tanker";
    private JPanel panel;
    private BufferedImage offScreen;
    private BufferedImage overScreen;  //to overlap scenery
    private BufferedImage characterScreen;
    private BufferedImage combatScreen;
    private Graphics offGraphics;
    private Graphics overGraphics;
    private Graphics characterGraphics;
    private Graphics combatGraphics;


    private int imageWidth;
    private int imageHeight;
    private ExplicitClip clipArea;

    private Rectangle rect;
    private Tree tree;
    private Mountain mountain;
    private Mountain overMountain;
    private Tank tank;
    private House house;
    private FloorTitle floor;
    private Enemy alien;
    private Enemy alien1;
    private Enemy alien2;

    private int tankX, tankY, prevTankX;


    public Main() {
        setTitle(FRAME_TITLE);
        setSize(720, 800);
        addKeyListener(this);
        panel = new JPanel();
//        panel.addKeyListener(this);
        add(panel);
        panel.setBackground(new Color(165, 245, 241));

        this.setVisible(true);  //is set visible again because a new element has been added
        tankX = panel.getWidth() / 2 - 70; tankY = panel.getHeight() - 160;
        prevTankX = tankX;

        Thread mainThread = new Thread(this);
        mainThread.start();
    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics g) {
        Dimension d = panel.getSize();
        if((offGraphics == null || overGraphics == null || characterGraphics == null) || (d.width != imageWidth)
                ||(d.height != imageHeight)) {
            if(d.width < 1 || d.height < 1) return;
            imageWidth = d.width;
            imageHeight = d.height;

            offScreen = new BufferedImage(panel.getWidth(),
                    panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            overScreen = new BufferedImage(panel.getWidth(),
                    panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
//            characterScreen = new BufferedImage(panel.getWidth(),
//                    panel.getHeight(), BufferedImage.TYPE_INT_ARGB);  //here goes tank desired dimensions for performance issues
        characterScreen = new BufferedImage(130, 110, BufferedImage.TYPE_INT_ARGB);
        combatScreen = new BufferedImage(panel.getWidth(),
                panel.getHeight(), BufferedImage.TYPE_INT_ARGB);

            rect = new Rectangle(panel, overGraphics);
            tree = new Tree(panel, overScreen);
            mountain = new Mountain(panel, offScreen);
            overMountain = new Mountain(panel, overScreen);
            tank = new Tank(panel, characterScreen);
            house = new House(panel, offScreen);
            floor = new FloorTitle(panel, offScreen);
            alien = new Enemy(panel, offScreen);
            alien1 = new Enemy(panel, offScreen);
            alien2 = new Enemy(panel, offScreen);

            offGraphics = offScreen.createGraphics();
            overGraphics = overScreen.createGraphics();
            characterGraphics = characterScreen.createGraphics();
            combatGraphics = combatScreen.createGraphics();

            alien.setGraphics(offGraphics);
            alien.drawEnemy(30, 50, 1.2, 1.2);
            Thread thA1 = new Thread(alien);
            thA1.start();

            alien1.setGraphics(offGraphics);
            alien1.drawEnemy(500, 100, 1.2, 1.2);
            Thread thA2 = new Thread(alien1);
            thA2.start();

            alien2.setGraphics(offGraphics);
            alien2.drawEnemy(250, 150, 1.2, 1.2);
            Thread thA3 = new Thread(alien2);
            thA3.start();
//            bullet = new Bullet(panel, offScreen);

//            drawBackground();
//            drawFloor();
////            tank.drawFrontIdleTank(0, 0, 0.5, 0.5);
//            tank.setGraphics(offGraphics);
//            tank.drawFrontIdleTank(tankX, tankY, 0.5, 0.5);

//            panelGraphics.drawImage(offScreen, 0, 0, panel);
//            panelGraphics.drawImage(overScreen, 0, 0, panel);
//            panelGraphics.drawImage(characterScreen, tankX, tankY, panel);
        }   //check if the screen hasn't been drawn or the window was resided
//        offGraphics.setColor(getBackground());
//        offGraphics.fillRect(0, 0, d.width, d.height);

        drawBackground();
        drawFloor();

        tank.setGraphics(characterGraphics);
        tank.drawFrontIdleTank(0, 0, 0.5, 0.5);

        offGraphics.drawImage(characterScreen, tankX, tankY, null);
        offGraphics.drawImage(combatScreen, 0, 0, null);
        g.drawImage(offScreen, 0, 0, null);
//        tank.drawFrontIdleTank(tankX, tankY, 0.5, 0.5);

//        g.drawImage(characterScreen, 0, 0, null);
//        panelGraphics.drawImage(offScreen, 0, 0, panel);
//        offGraphics.drawImage(characterScreen, 0, 0, null);  //location of the tank
//        panelGraphics.drawImage(overScreen, 0, 0, panel);
//        if(prevTankX != tankX)
//        panelGraphics.drawImage(characterScreen, tankX, tankY, panel);
//        g.drawImage(offScreen, 0, 0, null);
//        g.drawImage(characterScreen, tankX, tankY, null);
    }

    private void drawBackground() {
        clear(offGraphics);
        tree.setGraphics(overGraphics);
        mountain.setGraphics(offGraphics);
        overMountain.setGraphics(overGraphics);
        house.setGraphics(offGraphics);
        floor.setGraphics(offGraphics);

        rect.setGraphics(offGraphics);

        mountain.drawMountain(200, 185, 1, 1);
        mountain.drawMountain(10, 185, 1.8, 1);
        mountain.drawMountain(300, 135, 1, 1.4);
        overMountain.drawMountain(250, 155, 1.6, 1.2);
        overMountain.drawMountain(400, 195, 1.4, 1);
        overMountain.drawMountain(180, 125, 0.8, 1.5);
        overMountain.drawMountain(230, 195, 1, 1.1);
        mountain.drawMountain(400, 100, 1.2, 1.5);

        tree.drawTree(1400, 1000, 0.3, 0.3);
        tree.drawTree(1900, 1000, 0.3, 0.3);
        tree.drawTree(1550, 1300, 0.3, 0.3);
        tree.drawTree(1250, 1200, 0.3, 0.3);
        tree.drawTree(2000, 1350, 0.3, 0.3);

        tree.drawTree(300, 800, 0.3, 0.3);
        tree.drawTree(100, 1000, 0.3, 0.3);

        tree.drawTree(100, 1010, 0.4, 0.4);
        tree.drawTree(500, 910, 0.4, 0.4);
//        tree.drawTree(600, 910, 0.4, 0.4);

//        tree.drawTreeTop(300, 500, 0.5, 1);
//        tank.drawFrontIdleTank(500, 500, 1, 1);

        house.drawFrontHouse(100, 400, 1, 1);
        house.drawFrontHouse(600, 350, 0.8, 1);

//        floor.drawSquareBrownTitle(150, 150, 1, 1);
//        floor.drawSquareGreenTitle(250, 250, 1, 1);

        offGraphics.drawImage(overScreen, 0, 0, null);
//        offGraphics.drawImage(characterScreen, 0, 0, null);

//        panelGraphics = panel.getGraphics();
    }

    private void drawFloor() {
        for(int i = 0; i < panel.getWidth() - 50; i += 50) {
            floor.drawGrassTitle(i, panel.getHeight() - 50, 1, 1);

        }
    }

    private void clear(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(33);
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT) {
            prevTankX = tankX;
            if(tankX < panel.getWidth() - 130)
            tankX += 10;
//            clear(offGraphics);
//            clear(characterGraphics);
            //characterGraphics.fillRect(0, 0, characterScreen.getWidth(), characterScreen.getHeight());

//            characterGraphics.setBackground(new Color(255, 255, 255, 0));
//            characterGraphics.setColor(new Color(255, 255, 255, 0));
//            characterGraphics.fillRect(0, 0, characterScreen.getWidth(), characterScreen.getHeight());
//            characterGraphics.setComposite(AlphaComposite.Clear);
//            characterGraphics.fillRect(0, 0, characterScreen.getWidth(), characterScreen.getHeight());
//            characterGraphics.setComposite(AlphaComposite.SrcOver);
//            repaint();
        } else if(key == KeyEvent.VK_LEFT) {
            prevTankX = tankX;
            if(tankX > 20) {
                tankX -= 10;
            }
//            repaint();
        } else if(key == KeyEvent.VK_SPACE) {
            int bulletX = tankX + 60;
            int bulletY = panel.getWidth() - 120;

            Bullet bullet = new Bullet(panel, characterScreen);
            bullet.setGraphics(offGraphics);
            bullet.setShootLocation(bulletX, bulletY);

            Thread th = new Thread(bullet);
            th.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
