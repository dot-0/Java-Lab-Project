package javaproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *The Main class shows the home page and the application starts from this.
 * 
 * @author Mridul
 */
public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage PrimaryStage) throws IOException {
        stage = PrimaryStage;
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setOnCloseRequest(e -> {
            try {
                e.consume();
                closeProgram();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *Asks for confirmation to close the application.
     * 
     * @throws IOException throws exception if no function is found
     */
    public void closeProgram() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Confirm.fxml"));
        Scene sceneConf = new Scene(parent);
        Stage stage2 = new Stage();
        stage2.initStyle(StageStyle.UTILITY);
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.setScene(sceneConf);
        stage2.show();
    }

    /**
     *This is the main method.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }
}
