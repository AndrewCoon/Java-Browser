import javax.swing.*;
import java.awt.Color;

public class Browser {
    int size = 450;
    Frame frame;
    Panel panel;
    JLabel score = new JLabel();

    public Browser() {
        this.size = 450;
        init();
    }

    public Browser(int size) {
        this.size = size;
        init();
    }

    private void init() {
        frame = new Frame(size, size, "Java Browser");
    }


}

