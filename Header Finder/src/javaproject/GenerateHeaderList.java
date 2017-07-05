package javaproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.scene.control.ComboBox;

/**
 *The GenerateHeaderList program implements an application that
 * generates the full header files(which has at leat one function) list.
 * 
 * @author Mridul
 */
public class GenerateHeaderList {

    static CodeSource src = GenerateHeaderList.class.getProtectionDomain().getCodeSource();
    static List<String> HeadList = new ArrayList<String>();

    /**
     *Final header files list
     */
    public static String[] HeadList2;

    /**
     *This method generates the header file list and adds this
     * to the corresponding Combobox.
     * 
     * @param comboBoxFun Function Search Box
     * @throws IOException throws exception if no function is found
     */
    public static void generateHead(ComboBox<String> comboBoxFun) throws IOException {

        if (src != null) {
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            ZipEntry ze;
            while (true) {
                if ((ze = zip.getNextEntry()) == null) {
                    break;
                }
                String entryName = ze.getName();
                if (entryName.contains("HeaderFiles")) {
                    String substr = entryName.substring(33);
                    if (substr.length() != 0) {
                        if (meth2(entryName)) {
                            HeadList.add(substr);
                        }
                    }
                }
            }
        }

        HeadList2 = HeadList.toArray(new String[HeadList.size()]);
        Arrays.sort(HeadList2, String.CASE_INSENSITIVE_ORDER);
        comboBoxFun.getItems().clear();
        comboBoxFun.getItems().addAll(HeadList2);
    }

    /**
     *This method determines whether the header file contains any function or not.
     * 
     * @param path the header files path
     * @return returns true if the header file contains at least one function, otherwise returns false
     */
    public static boolean meth2(String path) {

        boolean flag = false;

        String headName = path.substring(33);
        char ch[] = headName.toCharArray();
        int l = ch.length;
        if (ch[0] == 'c' && ch[l - 2] != '.') {
            if (!headName.equals("complex")) {
                String nm = "";
                for (int i = 1; i < l; i++) {
                    nm += ch[i];
                }
                nm += ".h";
                headName = nm;
                path = path.substring(0, 33) + headName;
            }
        }

        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getSystemResourceAsStream(path);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader in = new BufferedReader(streamReader);

            for (String line; (line = in.readLine()) != null;) {
                String s1 = line;
                if (s1.length() > 6) {
                    String s2 = s1.substring(0, 7);
                    if ("_CRTIMP".equals(s2)) {
                        String show = ParseForDispHead.parse(s1, 1);
                        if (show.length() != 0) {
                            flag = true;
                        }
                    } else if ("extern ".equals(s2)) {
                        String show = ParseForDispHead.parse(s1, 2);
                        if (show.length() != 0) {
                            flag = true;
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }

        return flag;
    }
}
