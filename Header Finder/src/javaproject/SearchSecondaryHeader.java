package javaproject;

/**
 *The SearchSecondaryHeader program implements an application that
 * checks whether a secondary header for a function exists or not.
 * 
 * @author Mridul
 */
public class SearchSecondaryHeader extends GenerateFunctionList {

    /**
     *This method checks whether a secondary header for a function exists or not.
     * 
     * @param str secondary header name which will be checked 
     * @return returns true if seondary header is found, otherwise returns false
     */
    public static boolean found(String str) {
        for (String HeadList1 : HeadList) {
            if (HeadList1.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
