package javaproject;

import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *The AutoSuggestion program implements an application that
 * handles the property of autosuggestion while searching.
 * 
 * @author Mridul
 * @param <T> generic type
 */
public class AutoSuggestion<T> {

    /**
     *The search box
     */
    private final ComboBox<T> CB;
    /**
     *The full list of headers/functions
     */
    private final ObservableList<T> Full_List;
    /**
     *Displays header name, parameters, return type for the searched function.
     */
    private final TextArea display;
    /**
     *Contains and displays the functions list for the searched header.
     */
    private final TableView<DispInfo> table;
    /**
     *The searching string at any moment
     */
    String filter = "";
    /**
     *Checks whether the search box is of header or function.
     */
    int check;

    /**
     *This method handles the autosuggestion property.
     * 
     * @param CB search box
     * @param display text area which shows the details for the searched function
     * @param n checking integer
     * @param table table view which shows the functions list for the searched header
     */
    public AutoSuggestion(ComboBox<T> CB, TextArea display, int n, TableView<DispInfo> table) {
        this.CB = CB;
        this.display = display;
        this.table = table;
        this.check = n;
        Full_List = FXCollections.observableArrayList(CB.getItems());
        CB.getEditor().setOnKeyPressed(this::handleOnKeyPressed);
        CB.setOnHidden(this::handleOnHiding);
    }

    /**
     *This method processes the typed characters and shows autosuggestion.
     * 
     * @param e key event, which determines the pressed key 
     */
    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<T> filteredList = FXCollections.observableArrayList(); //list which is displayed as suggestion
        KeyCode code = e.getCode();

        if (code.isLetterKey() || code.isDigitKey()) {
            filter = CB.getEditor().getText();
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            CB.getItems().setAll(Full_List);
        }

        if (filter.length() != 0) {
            CB.getItems().setAll(Full_List);
            Stream<T> items = CB.getItems().stream();
            String txtUsr = filter.toLowerCase();
            items.filter(el -> el.toString().toLowerCase().startsWith(txtUsr)).forEach(filteredList::add);

            CB.show();
        }

        CB.getItems().setAll(filteredList);
    }

    /**
     *This method selects the string while hiding the suggestion
     * and searches the desired informations by calling corresponding 
     * method.
     * 
     * @param e event for hiding suggestion
     */
    public void handleOnHiding(Event e) {
        filter = CB.getEditor().getText();
        T s = CB.getSelectionModel().getSelectedItem();
        CB.getItems().setAll(Full_List);
        CB.getSelectionModel().select(s);

        String name = CB.getEditor().getText();

        if (check == 1) {
            try {
                if (name.length() != 0) {
                    table.getItems().clear();
                    DisplayFunctions.findFun(name, table);
                }
            } catch (Exception ex) {
            }
        } else {
            try {
                if (name.length() != 0) {
                    display.clear();
                    DisplayHeader.findHead(name, display);
                }
            } catch (Exception ex) {
            }
        }
    }
}
