package javaproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.scene.control.ComboBox;

/**
 *The GenerateFunctionList program implements an application that
 * generates the list of every functions and by this time also
 * maps informations got from calling parse method from ParseForDispHead
 * calss.
 * 
 * 
 * @author Mridul
 */
public class GenerateFunctionList {

    /**
     *Counts the number of functions
     */
    public static int coun = 0;
    /**
     *length functions list
     */
    public static int len3;

    /**
     *Maps a string containing parameters, return type
     * and corresponding header file name for each function.
     */
    public static Map<String, String> myMap = new HashMap<String, String>();

    /**
     *Final functions list
     */
    public static String[] FunList;

    static CodeSource src = GenerateHeaderList.class.getProtectionDomain().getCodeSource();
    static List<String> HeadList = new ArrayList<String>();
    static List<String> FunListTemp = new ArrayList<String>();

    /**
     *This method generates the functions list and adds this 
     * to the corresponding Combobox.
     * 
     * @param comboBoxHead Header Search Box
     * @throws FileNotFoundException throws exception if no function is found
     * @throws IOException throws exception if no function is found
     */
    public static void generateFun(ComboBox<String> comboBoxHead) throws FileNotFoundException, IOException {

        if (src != null) {
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            ZipEntry ze;

            while ((ze = zip.getNextEntry()) != null) {
                String entryName = ze.getName();
                if (entryName.contains("HeaderFiles")) {
                    HeadList.add(entryName.substring(33));
                    meth1(entryName);
                }
            }
        }

        len3 = coun;
        FunList = FunListTemp.toArray(new String[len3]);
        Arrays.sort(FunList, String.CASE_INSENSITIVE_ORDER);
        comboBoxHead.getItems().clear();
        comboBoxHead.getItems().addAll(FunList);
    }

    /**
     *This method searches the functions and does the mapping.
     * 
     * @param name name of header file
     * @throws IOException throws exception if no function is found
     */
    public static void meth1(String name) throws IOException{

        InputStream inputStream = ClassLoader.getSystemClassLoader().getSystemResourceAsStream(name);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(streamReader);
        name = name.substring(33);

        for (String line; (line = in.readLine()) != null;) {
            String s1 = line;
            if (s1.length() > 6) {
                String s2 = s1.substring(0, 7);
                if ("_CRTIMP".equals(s2)) {
                    String show = ParseForDispHead.parse(s1, 1);
                    if (show.length() != 0) {
                        String funName = "";
                        for (int i = 0;; i++) {
                            if ("Parameters".equals(show.substring(i, i + 10))) {
                                funName = show.substring(0, i);
                                break;
                            }
                        }
                        if (funName.length() > 1) {
                            if ("__".equals(funName.substring(0, 2))) {
                                funName = funName.substring(2);
                            } else if ("_".equals(funName.substring(0, 1))) {
                                funName = funName.substring(1);
                            }
                            show += "+" + name;
                            if (myMap.containsKey(funName) == false) {
                                FunListTemp.add(funName);
                                coun++;
                                myMap.put(funName, show);
                            }
                        }
                    }
                } else if ("extern ".equals(s2)) {
                    String show = ParseForDispHead.parse(s1, 2);
                    if (show.length() != 0) {
                        String funName = "";
                        for (int i = 0;; i++) {
                            if ("Parameters".equals(show.substring(i, i + 10))) {
                                funName = show.substring(0, i);
                                break;
                            }
                        }
                        if (funName.length() > 1) {
                            if ("__".equals(funName.substring(0, 2))) {
                                funName = funName.substring(2);
                            } else if ("_".equals(funName.substring(0, 1))) {
                                funName = funName.substring(1);
                            }
                            show += "+" + name;
                            if (myMap.containsKey(funName) == false) {
                                FunListTemp.add(funName);
                                coun++;
                                myMap.put(funName, show);
                            }
                        }
                    }
                }
            }
        }
    }
}
