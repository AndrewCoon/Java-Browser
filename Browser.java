import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.net.URL;

public class Browser extends JPanel implements ActionListener, KeyListener {
  int size = 450;
  Frame frame;
  protected JTextField textField;
  protected JTextArea textArea;
  private final static String newline = "\n";
  private static final int CURSOR_WIDTH = 5; // KG
  private int ED_WIDTH = 300; // KG
  private int ED_HEIGHT = 500; // KG
  String title, h1tag, span1, ptag;
  protected JLabel actionLabel;
  protected JLabel spanLabel;
  protected JLabel paragraph;
  List<JLabel> h1s = new ArrayList<JLabel>();
  List<JLabel> ps = new ArrayList<JLabel>();
  BufferedImage image;

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
    String data = full.substring(full.indexOf(start));
    return full.substring(start.length(), data.indexOf(end));
  }


  public void Parse(String url) {
    getHTML file = new getHTML(url);
    String data;
    try {

      data = file.total.toLowerCase();
    } catch (Exception e) {
      data = "<h1> IEBBFI could not load website </h1>";

    }
    // int titleStart = data.indexOf("<title>");
    // titleStart += 7;
    // int titleEnd = data.indexOf("</title>");
    try {
      title = "Internet explorer but better v 0.1- " + getValue("<title>", "</title>", data);
    } catch (Exception e) {
      title = url;
    }
    for (int i = 0; i < 5; i++) {
      try {
        h1tag = getValue("<h1", "/h1>", data);
        h1tag = getValue(">", "<", h1tag);

      } catch (Exception e) {
        h1tag = "";
      }
      data = data.replace("<h1>" + h1tag + "</h1>", " ");
      actionLabel = new JLabel(h1tag);
      actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      h1s.add(actionLabel);
    }
    for (int i = 0; i < 5; i++) {
      try {
        ptag = getValue("<p", "/p>", data);
        ptag = getValue(">", "<", ptag);
        data = data.replace("<p>" + ptag + "</p>", " ");
        actionLabel = new JLabel(ptag);
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        ps.add(actionLabel);
      } catch (Exception e) {
        ptag = "";
      }
    }
    try {
      span1 = getValue("<span", "/span>", data);
      span1 = getValue(">", "<", span1);
    } catch (Exception e) {
      span1 = "";
    }
    String imageUrl = "";
    try {
      if(occurs(data,"<img")>0){
        //srcset
        if(occurs(data,"src=")>0){
        imageUrl = getValue("src=", "/img>", data);
        }else{
          imageUrl=getValue("srcset=", "/img>", data);
        }
        try{
        imageUrl = imageUrl.substring(0,imageUrl.indexOf(".jpg")-4);
        }catch(Exception e){}
        try{
          imageUrl = imageUrl.substring(0,imageUrl.indexOf(".png")-4);
          }catch(Exception e){}
        imageUrl = imageUrl.replaceAll("\"", "");
        imageUrl = imageUrl.replace("<", " ");
        imageUrl = imageUrl.replace(">", " ");
        if(occurs(data,"/")==0){
          imageUrl = url + imageUrl;
        }
        try{
          imageUrl = imageUrl.substring(0,imageUrl.indexOf("?"));
        }catch(Exception e){

        }
       // imageUrl = imageUrl.substring(0,imageUrl.indexOf("?"));
        System.out.println("url: " + imageUrl);
        URL imageurl = new URL(imageUrl);
        image = ImageIO.read(imageurl);
      }
      //imageUrl = getValue("<img src=", "></img>", data); 
      
    } catch (Exception e) {
      System.out.println("GOTEM: ");
      e.printStackTrace();
      System.out.println("url: " + imageUrl);
    }
  }

  private void init() {
    Object[] possibilities = null;
    String s = (String) JOptionPane.showInputDialog(
        frame, "Load website:\n");

    Parse(s);
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
      // p//
      this.add(h1s.get(i));
    }
    for (int i = 0; i < ps.size(); i++) {
      System.out.println("p tag number: " + i);
      // p//
      this.add(ps.get(i));
    }
    try{
    p.add(new JLabel(new ImageIcon(image)));}catch(Exception e){

    }
    // p.add(actionLabel);
    p.add(spanLabel);
    p.add(paragraph);
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
    int first = 0;
    int last = word.length();
    int count = 0;
    for (int i = 0; i < str.length()-word.length(); i++) {
       //System.out.println(str.substring(first, last).toLowerCase());  
        //System.out.println(word.toLowerCase());
        if(str.substring(first, last).toLowerCase().equals(word.toLowerCase()))
          count++;
        first++;
        last++;
    }

    return count;
}
}
