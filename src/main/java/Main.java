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

        System.out.println(PasswordHelper.check("hi","$2a$06$/rs4tjFXx7Zqudt3y/9PtOT/FMkLeVlcqO2esg3aDzLXg1LoQfKR2"));
    }
}
