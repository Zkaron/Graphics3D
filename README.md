# Graphics3D
This is the code for third partial of Graphics assignature (August-December 2017). 
I'll just explain general functionality of the main classes and how to write a test class.

DEFINITIONS:
Test Class refers to a class that uses the method main, it's more like deliverables for the client.

All the core functionality resides in Lines.AbstractLine class, this class manages from the 3D coordinates math to project them as 2D point and draw them. Every big change like setting transformations to the points must be called from this class (in the drawLine3D function and before calling the method drawLine).
As this class is abstract, every change made on it should be automatically updated from it's subclasses.

To write a test class you must first create it in the Testing folder, then follow with creating a class that extends MyFrame (a specialized class of JFrame) and keep the following structure;

public Class MyTestClass extends MyFrame {
    private final String FRAME_TITLE = "NameOfTestApplication";
    private JPanel panel;
    private BufferedImage buffImage;
    
  //here goes extra global variable declarations
  
  public MyTestClass() {
        setTitle(FRAME_TITLE);
        //You can adjust frame size like in a normal JFrame
        panel = new JPanel();
        add(panel);
        
        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        fillRect = new Rectangle(panel, g2);
        fillRect.setColor(panel.getBackground());
        fillRect.fillRectangle(new Point(0, 0), new Point(panel.getWidth(), panel.getHeight()));
        
        // Here initialize any needed variable, if you use
        // from my figure classes you need to construct them
        // in the following form
        cube = new Cube(g2);
  }
  
  public void someFunctionThatDraws() {
    //Here you add all the points that are going to be drawn and call the figure callMethod
    
     // This displays the results on the frame
     panel.getGraphics().drawImage(buffImage, 0, 0, panel);
  }
  
  public static void main(String[] args) {
    //Here just call your class
    MyTestClass test = new MyTestClass();
    test.someFunctionThatDraws();
  }
}

