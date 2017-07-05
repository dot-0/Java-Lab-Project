package javaproject;

import javafx.beans.property.SimpleStringProperty;

/**
 *The DispInfo program implements an application that
 * creates a variable type which contains different 
 * variables.
 * 
 * @author Mridul
 */
public class DispInfo {
    
    private SimpleStringProperty name;
    private SimpleStringProperty para;
    private SimpleStringProperty ret;

    /**
     *This is the constructor of this class
     * which sets values to the variables of 
     * this class.
     * 
     * @param n name of function
     * @param p parameter
     * @param r return type
     */
    public DispInfo(String n, String p, String r) {
        this.name = new SimpleStringProperty(n);
        this.para = new SimpleStringProperty(p);
        this.ret = new SimpleStringProperty(r);
    }

    /**
     * @return returns function name
     */
    public String getName() {
        return name.get();
    }

    /**
     *Sets function name
     * 
     * @param n function name
     */
    public void setName(String n) {
        name.set(n);
    }
    
    /**
     * @return returns parameter
     */
    public String getPara() {
        return para.get();
    }

    /**
     *Sets parameter
     * @param p parameter
     */
    public void setPara(String p) {
        para.set(p);
    }
    
    /**
     * @return returns return_type
     */
    public String getRet() {
        return ret.get();
    }

    /**
     *Sets return_type
     * 
     * @param r return type
     */
    public void setRet(String r) {
        ret.set(r);
    }
}
