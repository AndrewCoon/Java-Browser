public class Main {
    public static void main(String[] args) {
        getHTML file = new getHTML("http://google.com");
        String data = file.total;
        int titleStart = data.indexOf("<title>");
        titleStart += 7;
        int titleEnd = data.indexOf("</title>");
        new Browser(data.substring(titleStart, titleEnd), 600);

    }
}