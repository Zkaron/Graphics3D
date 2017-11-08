package Core;

import javax.swing.JFrame;

public class MyJFrame extends JFrame{
    private final String DEFAULT_FRAME_TITLE = "My Frame";
    private final int DEFAULT_FRAME_WIDTH = 500;
    private final int DEFAULT_FRAME_HEIGHT = 500;

    public MyJFrame() {
        setTitle(DEFAULT_FRAME_TITLE);
	    setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MyJFrame(String frameName) {
        setTitle(frameName);
	    setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MyJFrame(String frameName, int width, int height) {
	    setTitle(frameName);
	    setSize(width, height);
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
    }    
}
