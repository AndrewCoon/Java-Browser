import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Parse {
    protected JTextField textField;
    private final static String testURL = "http://jsgames.rf.gd/basic";
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static final int CURSOR_WIDTH = 5; // KG
    private int ED_WIDTH = 300; // KG
    private int ED_HEIGHT = 500; // KG
    String title, h1tag, span1, ptag, url;
    protected boolean testing;
    protected JLabel actionLabel;
    protected JLabel spanLabel;
    protected JLabel paragraph;
    List<JLabel> h1s = new ArrayList<JLabel>();
    List<JLabel> ps = new ArrayList<JLabel>();

    public Parse(boolean file, String dir) {
        if (file)
            parseFile(dir);
        else
            parseUrl(dir);
    }

    private void parseUrl(String url) {
        getHTML file = new getHTML(url);
        String data;
        try {
            data = file.total.toLowerCase();
        } catch (Exception e) {
            data = "<h1> IEBBFI could not load website </h1>";
        }
        parse(data);
    }

    private void parseFile(String filename) {
        String data = "";
        try {
            File myObj = new File(filename);
            Scanner read = new Scanner(myObj);
            while (read.hasNextLine()) {
                data += newline + read.nextLine();
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        parse(data);
    }

    public void parse(String data) {
        try {
            title = "Internet explorer but better v 0.1 - " + getValue("<title>", "</title>", data);
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
            } catch (Exception e) {
                ptag = "";
            }
            data = data.replace("<p>" + ptag + "<p>", " ");
            actionLabel = new JLabel(ptag);
            actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            ps.add(actionLabel);
        }

        try {
            span1 = getValue("<span", "/span>", data);
            span1 = getValue(">", "<", span1);
        } catch (Exception e) {
            span1 = "";
        }

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

    public String getValue(String start, String end, String full) {
        return full.substring(full.indexOf(start) + start.length(), full.indexOf(end));
    }
}
