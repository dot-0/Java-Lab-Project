package javaproject;

/**
 *The ParseForDispFun program implements an application that
 * parses a string in the format - 
 * 'function_name'('parameters')\n  Returns - 'return_type'.
 * 
 * @author Mridul
 */
public class ParseForDispFun {

    /**
     *This method formats the string.
     * 
     * @param s the string which will be parsed
     * @param x the the checking integer which determine the pattern of strings
     * @return returns the formatted string
     */
    public static String parse(String s, int x) {
        char s1[] = s.toCharArray();
        String ret = "", name = "", out = "";
        int spaceCount = 0, flag = 0, p;
        for (int i = 7; i < s1.length; i++) {
            if (s1[i] == ' ') {
                spaceCount++;
            }
            if (s1[i] == '(' && spaceCount > 2) {
                flag = 1;
                p = i;
                String rev_name = "";
                int k = i - 1;
                while (s1[k] == ' ') {
                    k--;
                }

                for (int l = k;; l--) {
                    if (Character.isLetter(s1[l]) == false && Character.isDigit(s1[l]) == false) {
                        if (s1[l] != '_') {
                            break;
                        }
                    }
                    rev_name += s1[l];
                }
                char rev[] = rev_name.toCharArray();
                for (int j = rev.length - 1; j >= 0; j--) {
                    name += rev[j];
                }
                for (int j = p;; j++) {
                    name += s1[j];
                    if (s1[j] == ')') {
                        break;
                    }
                }
                break;
            }
        }

        if (flag == 1) {
            if (x == 1) {
                for (int i = 8;; i++) {
                    if (s1[i] == ' ' || s1[i] == '_') {
                        if ((s1[i + 1] != 'l' || s1[i + 2] != 'o' || s1[i + 3] != 'n') && (s1[i + 1] != 'd' || s1[i + 2] != 'o' || s1[i + 3] != 'u')) {
                            break;
                        }
                    }
                    ret += s1[i];
                }
            } else if (x == 2) {
                for (int i = 7;; i++) {
                    if (s1[i] == ' ' || s1[i] == '_') {
                        if ((s1[i + 1] != 'l' || s1[i + 2] != 'o' || s1[i + 3] != 'n') && (s1[i + 1] != 'd' || s1[i + 2] != 'o' || s1[i + 3] != 'u')) {
                            break;
                        }
                    }
                    ret += s1[i];
                }
            }
        }

        if (flag == 1) {
            out += name + "\n" + "  Returns - " + ret;
        }
        return out;
    }
}
