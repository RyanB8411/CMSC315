import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public static void main(String[] args) throws Exception {
        MyFrame f = new MyFrame();
        f.setTitle("My Empty Frame");
        f.setSize(300, 200); // default size is 0,0
        f.setLocation(10, 200); // default is 0,0 (top left corner)
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
