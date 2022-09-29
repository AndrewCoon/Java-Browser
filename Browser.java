import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Browser extends JPanel implements ActionListener, KeyListener {
  int size = 450;
  Frame frame;
  protected JTextField textField;
  protected JTextArea textArea;
  private final static String newline = "\n";
  private static final int CURSOR_WIDTH = 5; // KG
  private int ED_WIDTH = 300; // KG
  private int ED_HEIGHT = 500; // KG
  String title, h1tag;
  protected JLabel actionLabel;

  public static void main(String[] args) {
    new Browser();
  }

  public void Paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    g2.drawString("This is gona be awesome", 70, 20);
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

  public String getValue(String start, String end, String full) {
    return full.substring(full.indexOf(start) + start.length(), full.indexOf(end));
  }

  public void Parse(String url) {
    getHTML file = new getHTML(url);
    String data = file.total;// .toLowerCase();
    // int titleStart = data.indexOf("<title>");
    // titleStart += 7;
    // int titleEnd = data.indexOf("</title>");
    try {
      title = getValue("<title>", "</title>", data);
    } catch (Exception e) {
      title = url;
    }
    try {
      h1tag = getValue("<h1>", "</h1>", data);
    } catch (Exception e) {
      h1tag = "";
    }

  }

  private void init() {
    Parse("http://info.cern.ch/");
    this.ED_WIDTH = size * 2;
    this.ED_HEIGHT = size;

    repaint();

    frame = new Frame(size, size, title);
    // frame = new Frame(size, size, "Java Browser");

    this.setBounds(0, 0, size, size);
    frame.add(this);

    // frame = new Frame(size, size, title);

    this.setBounds(0, 0, size, size);
    frame.add(this);

    textField = new JTextField(20);
    textField.addActionListener(this);

    actionLabel = new JLabel(h1tag);
    // actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    JPanel p = new JPanel();

    // add label to panel
    p.add(actionLabel);

    // add panel to frame
    this.add(p);

    textArea = new JTextArea(5, 20);
    textArea.setEditable(true);

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
