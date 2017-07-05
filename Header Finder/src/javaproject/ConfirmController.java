package javaproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *This class controls the confirmation page.
 * 
 * @author Mridul
 */
public class ConfirmController implements Initializable {

    @FXML
    private Button IDyesButton;
    @FXML
    private Button IDnoButton;
    @FXML
    private AnchorPane IDanchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDanchorPane.setStyle("-fx-background-color: rgb(50, 60, 70);"); //sets the colour of the anchorpane
    }

    /**
     * This method sets yes button on action on mouse click
     * @param event action event
     * @throws IOException 
     */
    @FXML
    private void yesButton(ActionEvent event) throws IOException {
        Parent closeParent2 = FXMLLoader.load(getClass().getResource("Confirm.fxml"));
        Scene closeScene2 = new Scene(closeParent2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(closeScene2);
        stage2.close();
        System.exit(0);
    }
    
    /**
     * This method sets yes button on action on ENTER press
     * @param event action event
     * @throws IOException 
     */
    @FXML
    private void handleEnterPressedYes(KeyEvent event) throws IOException {
        if (KeyCode.ENTER == event.getCode()) {
            Parent closeParent2 = FXMLLoader.load(getClass().getResource("Confirm.fxml"));
            Scene closeScene2 = new Scene(closeParent2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(closeScene2);
            stage2.close();
            System.exit(0);
        }
    }

    /**
     * This method sets no button on action on mouse click
     * @param event action event
     * @throws IOException 
     */
    @FXML
    private void noButton(ActionEvent event) throws IOException {
        Parent closeParent = FXMLLoader.load(getClass().getResource("Confirm.fxml"));
        Scene closeScene = new Scene(closeParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(closeScene);
        stage.close();
    }
    
    /**
     * This method sets no button on action on ENTER press
     * @param event action event
     * @throws IOException 
     */
    @FXML
    private void handleEnterPressedNo(KeyEvent event) throws IOException {
        if (KeyCode.ENTER == event.getCode()) {
            Parent closeParent = FXMLLoader.load(getClass().getResource("Confirm.fxml"));
            Scene closeScene = new Scene(closeParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(closeScene);
            stage.close();
        }
    }
}
