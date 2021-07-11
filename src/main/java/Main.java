import at.favre.lib.crypto.bcrypt.BCrypt;
import com.br.gestionStock.PasswordHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {



    public static void main(String[] args) {
        //URL url = getClass().getResource("/main/webapp/resources/styles/some.css");

        System.out.println();
        Document htmlFile = null;
        try {

            String path = new File("./frontend/t.html").getCanonicalPath();
            htmlFile = Jsoup.parse(new File(path), "ISO-8859-1");
            System.out.println(htmlFile.getElementById("errorModal"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();


        }
    }
}
