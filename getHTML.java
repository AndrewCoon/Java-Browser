import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
 
public class getHTML {
    String total;
    public getHTML(String _url){
        try {
            // get URL content
            
            String a = _url;
            URL url = new URL(a);
            URLConnection conn = url.openConnection();
 
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
 
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
                total = total + inputLine;
            }
            br.close();
 
            System.out.println("Done");

        } catch (MalformedURLException e) {
            total = "<title>URL does not exist!</title>";
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
