import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;

public class Browser implements ActionListener{
    int size = 450;
    Frame frame;
    Panel panel;
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";

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
    }
    public void actionPerformed(ActionEvent evt) {
        //Object obj = packageChoice.getSelectedItem();
        //JOptionPane.showMessageDialog(this, "You selected: " + obj);
      }
      


}

