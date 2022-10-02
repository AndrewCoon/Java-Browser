import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Browser extends JPanel implements ActionListener, KeyListener {
  int size = 450;
  Frame frame;
  protected JTextField textField;
  private final static String testURL = "http://jsgames.rf.gd/basic";
  protected JTextArea textArea;
  private final static String newline = "\n";
  private static final int CURSOR_WIDTH = 5; // KG
  private int ED_WIDTH = 300; // KG
  private int ED_HEIGHT = 500; // KG
  String title, h1tag, span1, ptag;
  protected boolean test;
  protected JLabel actionLabel;
  protected JLabel spanLabel;
  protected JLabel paragraph;
  List<JLabel> h1s = new ArrayList<JLabel>();
  List<JLabel> ps = new ArrayList<JLabel>();

  public static void main(String[] args) {
    new Browser(true);
  }

  // public void Paint(Graphics g) {
  // Graphics2D g2 = (Graphics2D) g;
  // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
  // RenderingHints.VALUE_ANTIALIAS_ON);
  //
  // g2.drawString("This is gona be awesome", 70, 20);
  // }
  public String toString() {
    return "h1s: " + h1s.toString() + newline + newline + newline + "ps: " + ps.toString();
  }

  public Browser() {
    this.size = 450;
    this.title = "default";
    init();
  }

  public Browser(String title, int size) {
    this.size = size;
    this.title = title;
    init();
  }

  public Browser(int size) {
    this.size = size;
    init();
  }

  public Browser(boolean test) {
    this.size = 450;
    this.test = test;
    init();
  }

  public String getValue(String start, String end, String full) {
    return full.substring(full.indexOf(start) + start.length(), full.indexOf(end));
  }

  public void parse(String input) {
    Parse parser;
    if (test) {
      parser = new Parse(true, input);
    } else {
      parser = new Parse(false, input);
    }

    this.title = parser.title;
    this.h1tag = parser.h1tag;
    this.span1 = parser.span1;
    this.ptag = parser.ptag;
    this.h1s = parser.h1s;
    this.ps = parser.ps;
  }

  private void init() {
    Object[] possibilities = null;
    String s;
    if (!test) {
      s = (String) JOptionPane.showInputDialog(frame, "Load website:\n");
      parse(s);
    } else {
      parse("index.html");
    }
    this.ED_WIDTH = size * 2;
    this.ED_HEIGHT = size;

    repaint();

    frame = new Frame(size, size, title);

    this.setBounds(0, 0, size, size);
    frame.add(this);

    this.setBounds(0, 0, size, size);
    frame.add(this);

    textField = new JTextField(20);
    textField.addActionListener(this);

    ptag = String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 200, ptag);

    // actionLabel = new JLabel(h1tag);
    spanLabel = new JLabel(span1);
    // actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    paragraph = new JLabel(ptag);
    // actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    paragraph.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    JPanel p = new JPanel();

    // add label to panel
    for (int i = 0; i < h1s.size(); i++) {
      System.out.println("h1 tag number: " + i);
      p.add(h1s.get(i));
    }

    for (int i = 0; i < ps.size(); i++) {
      System.out.println("ps tag number: " + i);
      p.add(ps.get(i));
    }

    textArea = new JTextArea(5, 20);
    textArea.setEditable(true);
    p.add(textArea);

    this.add(p);

    JScrollPane scrollPane = new JScrollPane(textArea);

    GridBagConstraints c = new GridBagConstraints();
    c.gridwidth = GridBagConstraints.REMAINDER;

    c.fill = GridBagConstraints.HORIZONTAL;
    frame.add(textField, c);

    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1.0;
    c.weighty = 1.0;
    frame.add(scrollPane, c);

    frame.getContentPane().add(this, BorderLayout.CENTER);
    frame.pack();
    frame.setSize(ED_WIDTH, ED_HEIGHT);
    frame.addKeyListener(this);
    frame.setVisible(true);

    if (test)
      parse("index.html");
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

  private int occurs(String str, String word) {
    String a[] = str.split(" ");
    int count = 0;
    for (int i = 0; i < a.length; i++) {
      if (word.equals(a[i]))
        count++;
    }

    return count;
  }
}
