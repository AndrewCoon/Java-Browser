import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Container;

import static java.lang.System.out;

final class TestHTMLRendering {
    private static final int height = 1000;
    private static final int width = 1000;
    private static final String TITLE_STRING = "HTML Rendering";
    private static final String URL = "http://info.cern.ch/";
    private static final String VERSION_STRING = "1.0";

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                getHTML file = new getHTML(URL);
                out.println("Starting");
                final JFrame jframe = new JFrame(TITLE_STRING + " " + VERSION_STRING);
                Container contentPane = jframe.getContentPane();
                jframe.setSize(width, height);

                contentPane.setBackground(Color.YELLOW);
                contentPane.setForeground(Color.BLUE);
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try {
                    out.println("acquiring URL");
                    JEditorPane jep = new JEditorPane();
                    jep.setContentType("text/html");
                    jep.setText(file.total);
                    out.print(jep.getText());
                    out.println("URL acquired");
                    JScrollPane jsp = new JScrollPane(jep,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    contentPane.add(jsp);
                } finally {

                } /*
                   * catch (IOException e) {
                   * err.println("can't find URL");
                   * contentPane.add(new JLabel("can't find URL"));
                   * }
                   */
                jframe.validate();
                jframe.setVisible(true);

            }
        });
    }

}
