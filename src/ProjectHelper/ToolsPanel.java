package ProjectHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by erik on 4/26/17.
 */
public class ToolsPanel extends JPanel implements ActionListener {
    private JLabel yellowLabel;
    private ImageIcon lineIcon;
    private JButton lineButton;
    private ImageIcon rectangleIcon;
    private JButton rectangleButton;
    private ImageIcon circleIcon;
    private JButton circleButton;

    public final int linetoll = 0, recttool = 1, circletool = 2;

    public ToolsPanel() {
        setSize(60, 500);
        setBackground(new Color(248, 213, 131));
        yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(60, 500));

        setLayout(new FlowLayout());

        URL imageURL = getClass().getResource("images/lineicon.png");
        lineIcon = new ImageIcon(imageURL);
        lineButton = new JButton();
        lineButton.setIcon(lineIcon);
        lineButton.setPreferredSize(new Dimension(50, 50));
        lineButton.addActionListener(this);

        imageURL = getClass().getResource("images/rectangleicon.png");
        rectangleIcon = new ImageIcon(imageURL);
        rectangleButton = new JButton();
        rectangleButton.setIcon(rectangleIcon);
        rectangleButton.setPreferredSize(new Dimension(50, 50));
        rectangleButton.addActionListener(this);

        circleButton = new JButton();
        circleButton.setPreferredSize(new Dimension(50, 50));
        circleButton.addActionListener(this);

        add(lineButton);
        add(rectangleButton);
        add(circleButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == lineButton) {
            PaintPanel.setDrawingTool(linetoll);
        } else if(actionEvent.getSource() == rectangleButton) {
            PaintPanel.setDrawingTool(recttool);
        } else if(actionEvent.getSource() == circleButton){
            PaintPanel.setDrawingTool(circletool);
        }
    }
}
