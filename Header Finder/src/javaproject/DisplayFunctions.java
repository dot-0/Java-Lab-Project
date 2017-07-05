package javaproject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

/**
 *The DisplayFunctions program implements an application that
 * searches the information by a header name, extracts
 * function name, parameters and return type
 * from the formateed string and appends 
 * those to the text area.
 * 
 * @author Mridul
 */
public class DisplayFunctions {

    /**
     *Maps the functions to prevent repetition.
     */
    public static Map<String, String> myMap = new HashMap<String, String>();
    /**
     *Contains the information to add in the table view.
     */
    public static ObservableList<DispInfo> data = FXCollections.observableArrayList();

    /**
     *This method finds the functions list and appends those to the table.
     * 
     * @param name name of the header file
     * @param table this is where the functions list will be appended
     */
    public static void findFun(String name, TableView<DispInfo> table) {
        myMap.clear();
        if (name.length() > 2) {
            char ch[] = name.toCharArray();
            int l = ch.length;
            if (ch[0] == 'c' && ch[l - 2] != '.') {
                if (!name.equals("complex")) {
                    String name2 = "";
                    for (int i = 1; i < l; i++) {
                        name2 += ch[i];
                    }
                    name2 += ".h";
                    name = name2;
                }
            }
        }
        boolean flag = false;

        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getSystemResourceAsStream("javaproject/Contents/HeaderFiles/" + name);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader in = new BufferedReader(streamReader);

            flag = true;
            int cou = 1;

            for (String line; (line = in.readLine()) != null;) {
                String s1 = line;
                if (s1.length() > 6) {
                    String s2 = s1.substring(0, 7);
                    if ("_CRTIMP".equals(s2)) {
                        String show = ParseForDispFun.parse(s1, 1);
                        if (show.length() != 0) {
                            String sl = String.valueOf(cou);

                            char show2[] = show.toCharArray();
                            String nm = "", par = "", ret = "";
                            for (int i = 0; show2[i] != '('; i++) {
                                nm += show2[i];
                            }
                            for (int i = 0; i < show2.length; i++) {
                                if (show2[i] == '(') {
                                    for (int j = i + 1; show2[j] != ')'; j++) {
                                        par += show2[j];
                                    }
                                    break;
                                }
                            }
                            for (int i = 0; i < show2.length; i++) {
                                if (show2[i] == '-') {
                                    for (int j = i + 1; j < show2.length; j++) {
                                        ret += show2[j];
                                    }
                                    break;
                                }
                            }

                            if (nm.length() > 1) {
                                if ("__".equals(nm.substring(0, 2))) {
                                    nm = nm.substring(2);
                                } else if ("_".equals(nm.substring(0, 1))) {
                                    nm = nm.substring(1);
                                }
                                if (myMap.containsKey(nm) == false) {
                                    DispInfo DI = new DispInfo(nm, par, ret);
                                    data.add(DI);
                                    cou++;
                                    myMap.put(nm, "a");
                                }
                            }
                        }
                    } else if ("extern ".equals(s2)) {
                        String show = ParseForDispFun.parse(s1, 2);
                        if (show.length() != 0) {

                            String sl = String.valueOf(cou);

                            char show2[] = show.toCharArray();
                            String nm = "", par = "", ret = "";
                            for (int i = 0; show2[i] != '('; i++) {
                                nm += show2[i];
                            }
                            for (int i = 0; i < show2.length; i++) {
                                if (show2[i] == '(') {
                                    for (int j = i + 1; show2[j] != ')'; j++) {
                                        par += show2[j];
                                    }
                                    break;
                                }
                            }
                            for (int i = 0; i < show2.length; i++) {
                                if (show2[i] == '-') {
                                    for (int j = i + 1; j < show2.length; j++) {
                                        ret += show2[j];
                                    }
                                    break;
                                }
                            }

                            if (nm.length() > 1) {
                                if ("__".equals(nm.substring(0, 2))) {
                                    nm = nm.substring(2);
                                } else if ("_".equals(nm.substring(0, 1))) {
                                    nm = nm.substring(1);
                                }
                                if (myMap.containsKey(nm) == false) {
                                    DispInfo DI = new DispInfo(nm, par, ret);
                                    data.add(DI);
                                    cou++;
                                    myMap.put(nm, "a");
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            if (name.length() != 0) {
                Label label = new Label("Invalid Header Name !!");
                label.setFont(Font.font("Arial Rounded MT Bold", 25));
                table.setPlaceholder(label);
            }
        }

        table.setItems(data);

        if (flag && data.isEmpty()) {
            Label label = new Label("No Function Found !!");
            label.setFont(Font.font("Arial Rounded MT Bold", 25));
            table.setPlaceholder(label);
        }
    }
}
