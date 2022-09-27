import javax.swing.JFrame;

public class Frame extends JFrame {
        public Frame(int frameVert, int frameHor, String frameName) {
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setTitle(frameName);
          this.setSize(frameHor, frameVert);
          this.setLocationRelativeTo(null);
          this.setVisible(true);
          this.setLayout(null);
     }
}