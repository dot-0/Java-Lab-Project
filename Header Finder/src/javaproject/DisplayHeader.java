package javaproject;

import java.io.FileNotFoundException;
import javafx.scene.control.TextArea;

/**
 *The DisplayHeader program implements an application that
 * searches the information by a function name, extracts
 * header name, parameters and return type of
 * the function from the formateed string, appends 
 * those to the text area and prepares a link for getting details.
 * 
 * @author Mridul
 */
public class DisplayHeader extends GenerateFunctionList {

    static String link;
    static String link2 = "";
    static String link3 = "";

    /**
     *This method finds the informations by calling parse method and 
     * appends those to the text area.
     * 
     * @param name name of the function
     * @param dispHead this is where the informations will be appended
     * @throws FileNotFoundException trows exception if no file is found
     */
    public static void findHead(String name, TextArea dispHead) throws FileNotFoundException {
        link3 = name;
        boolean flag = false;
        for (int i = 0; i < len3; i++) {
            String str = FunList[i];
            if (name.equals(str.substring(0))) {
                String show = parse(myMap.get(str));
                dispHead.appendText(show);
                flag = true;
                break;
            }
        }
        if (!flag) {
            dispHead.appendText("\n\n\n\n\n\n\n              Invalid Function Name !!!");
        }
    }

    /**
     *This method extracts the header file name, parameters and return type 
     * of the function.
     * 
     * @param str the formatted string from which the informations
     * will be extracted
     * 
     * @return returns information containing string
     */
    public static String parse(String str) {

        char str2[] = str.toCharArray();
        String out = "";
        int p = 0, q = 0;
        for (int i = 0; i < str2.length; i++) {
            if (str2[i] == 'P' && str2[i + 1] == 'a' && str2[i + 2] == 'r' && str2[i + 3] == 'a') {
                p = i;
            }
            if (str2[i] == '+') {
                q = i;
            }
        }

        out += "Header : ";
        String head2 = "";

        for (int i = q + 1; i < str2.length; i++) {
            head2 += str2[i];
            out += str2[i];
        }
        link2 = head2;
        char[] head3 = head2.toCharArray();
        String head4 = "c";
        if (head3[head3.length - 2] == '.' && head3[head3.length - 1] == 'h') {

            for (int i = 0; i < head3.length - 2; i++) {
                head4 += head3[i];
            }

            if (SearchSecondaryHeader.found(head4)) {
                out += " / " + head4;
                link2 = head4;
            }
        }
        out += "\n\n";
        for (int i = p; str2[i] != '+'; i++) {
            out += str2[i];
        }
        out += "\n\n";
        return out;
    }

    /**
     * @return returns the prepared link
     */
    public static String getLink() {
        link = link2 + "/" + link3;
        return link;
    }
}
