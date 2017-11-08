package ProjectHelper;

import Core.MyJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by erik on 4/26/17.
 */
public class PaintGUI extends MyJFrame {
    private final String FRAME_TITLE = "Mini Paint";
    private final int DEFAULT_FRAME_WIDTH = 1200;
    private final int DEFAULT_FRAME_HEIGHT = 600;
    private JMenuBar greenMenuBar;
    private JLabel yellowLabel;
    private JButton lineIcon;
    private  JButton rectangleIcon;
    private  ToolsPanel toolsPanel;

    Container contentPane;
    PaintPanel paintPanel;

    public PaintGUI() {
        super();
        setTitle(FRAME_TITLE);
        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

        greenMenuBar = new JMenuBar();
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension( DEFAULT_FRAME_WIDTH, 40));

        yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(60, DEFAULT_FRAME_HEIGHT));

        lineIcon = new JButton();

        toolsPanel = new ToolsPanel();
        toolsPanel.setSize(new Dimension(60, DEFAULT_FRAME_HEIGHT));

        paintPanel = new PaintPanel();
        paintPanel.setPreferredSize(new Dimension(DEFAULT_FRAME_WIDTH, 500));

        setJMenuBar(greenMenuBar);
        contentPane = getContentPane();
        contentPane.add(yellowLabel, BorderLayout.WEST);
        contentPane.add(toolsPanel, BorderLayout.EAST);
        contentPane.add(paintPanel);

        //add(contentPane);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PaintGUI gui = new PaintGUI();
            }
        });
    }

}
