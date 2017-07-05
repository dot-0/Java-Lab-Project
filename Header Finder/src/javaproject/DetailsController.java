package javaproject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 *The DetailsController program implements an application that
 * parses a html file and appends the details of a searched function
 * to a webview.
 * 
 * @author Mridul
 */
public class DetailsController implements Initializable {

    /**
     *Shows the details of a searched function.
     */
    @FXML
    public WebView detailsArea;

    /**
     *Holds the webview.
     */
    @FXML
    public AnchorPane IDanchorPane;

    static String str, disp, name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IDanchorPane.setStyle("-fx-background-color: rgb(50, 60, 70);"); //sets the colour of the anchorpane

        String link = HomePageController.link;
        name = "";
        
        //finds the name of the function from the link 
        char arr[] = link.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '/') {
                for (int j = i + 1; j < arr.length; j++) {
                    name += arr[j];
                }
                break;
            }
        }

        //parses the html file and appends the selected texts to the webview
        boolean flag = false;
        boolean flag2 = false;
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getSystemResourceAsStream("javaproject/Contents/Details/" + link + "/index.html");
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader in = new BufferedReader(streamReader);
            flag = true;
            str = "";
            disp = "";
            for (String line; (line = in.readLine()) != null;) {
                if (line.contains("<h1>" + name) || line.contains("<h1><span class=\"namespace\" title=\"namespace std\">std::</span>" + name)) {
                    flag2 = true;
                }
                if (line.contains("See also")) {
                    flag2 = false;
                }
                if (link.contains("assert") || link.contains("errno")) {
                    if (line.contains("</section></div><div id=\"CH_bb\"></div></div>")) {
                        flag2 = false;
                    }
                }
                if (flag2) {
                    if (line.contains("<a href=\"http://www.cplusplus.com/")) {
                        line = line.replace("<a href=\"http://www.cplusplus.com/", "<a href=\"http://");
                    }
                    disp += line;
                    disp += "\n";
                }
            }
            StringBuilder sb = new StringBuilder(str);
            sb.append(disp);
            str = sb.toString();
            detailsArea.getEngine().loadContent(str);

        } catch (Exception e) {
            str = "";
            StringBuilder sb = new StringBuilder(str);
            sb.append("<center><font face=\"Arial Rounded MT Bold\" size=\"5px\">No More Details Found !!</font></center>");
            str = sb.toString();
            detailsArea.getEngine().loadContent(str);
        }
    }
}
