package javaproject;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 *The CustomCell program implements an application that
 * sets text and tooltip to the cells of the table.
 * 
 * @author Mridul
 */
public class CustomCell extends TableCell<DispInfo, String> {

    public TextField textField;

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (isEditing()) {
            if (textField != null) {
                textField.setText(getString());
            }
            setText(null);
            setGraphic(textField);
        } else {
            setText(getString());
            setGraphic(null);
            Tooltip tip = new Tooltip(getString());
            Tooltip.install(this, tip);

        }
        
        if(!empty){
            Tooltip tip = new Tooltip(getString());
            Tooltip.install(this, tip);
        }
    }

    /**
     * @return returns the string of the table cell
     */
    public String getString() {
        return getItem() == null ? "" : getItem();
    }
}
