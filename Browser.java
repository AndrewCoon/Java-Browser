import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Browser extends JPanel implements ActionListener, KeyListener{
    int size = 450;
    Frame frame;
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static final int CURSOR_WIDTH = 5;  // KG
    private int ED_WIDTH = 1000;  // KG
    private int ED_HEIGHT = 100;  // KG

    public Browser() {
        this.size = 450;
        init();
    }

    public Browser(int size) {
        this.size = size;
        init();
    }

    private void init() {
        this.ED_WIDTH = size*2;
        this.ED_HEIGHT = size;
        
        frame = new Frame(size, size, "Java Browser");
        
        this.setBounds(0, 0, size, size);
        frame.add(this);
        textField = new JTextField(20);
        textField.addActionListener(this);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        frame.add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        frame.add(scrollPane, c);

        frame.getContentPane().add(this,BorderLayout.CENTER);
        frame.pack();
        frame.setSize(ED_WIDTH, ED_HEIGHT);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent evt) {
        
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
        
    }
    
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ENTER) {
            System.out.println("Enter Pressed");            
        }
        this.repaint();
    }


}

