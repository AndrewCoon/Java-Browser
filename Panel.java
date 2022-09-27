import javax.swing.JPanel;
import java.awt.Color;

public class Panel extends JPanel {
    int x, y, type, tSize, mSize, size;
    int[][] map;
    Color color;

    public Panel(int size, int tSize, int mSize) {
        this.tSize = tSize;
        this.mSize = mSize;
        this.setBounds(0, 0, size, size);
        this.setBackground(Color.BLACK);
    }
}