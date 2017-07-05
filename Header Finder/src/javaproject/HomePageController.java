package javaproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *This class controls everything of home page.
 * 
 * @author Mridul
 */
public class HomePageController implements Initializable {

    static Stage AIstage;

    /**
     * The container of home page
     */
    @FXML
    private AnchorPane IDanchorPane;

    /**
     *Function Search Box
     */
    @FXML
    public ComboBox<String> comboBoxFun;

    /**
     *This is where the details for a function is are appended.
     */
    @FXML
    public TextArea dispHead;

    /**
     *Header Search Box
     */
    @FXML
    public ComboBox<String> comboBoxHead;
    /**
     *'Details' named hyperlink
     */
    @FXML
    private Hyperlink hyperLink;
    /**
     *Displays functions list.
     */
    @FXML
    private TableView<DispInfo> table;
    @FXML
    private TableColumn<DispInfo, String> name;
    @FXML
    private TableColumn<DispInfo, String> parameter;
    @FXML
    private TableColumn<DispInfo, String> returnType;

    /**
     *Link for getting details of a function.
     */
    public static String link;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<DispInfo, String>("name"));
        parameter.setCellValueFactory(new PropertyValueFactory<DispInfo, String>("para"));
        returnType.setCellValueFactory(new PropertyValueFactory<DispInfo, String>("ret"));

        Callback<TableColumn<DispInfo, String>, TableCell<DispInfo, String>> cellFactory
                = new Callback<TableColumn<DispInfo, String>, TableCell<DispInfo, String>>() {
            @Override
            public TableCell call(TableColumn p) {
                return new CustomCell();
            }
        };

        //sets table cells of first column on action on mouse click
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.setOnMouseClicked((MouseEvent click) -> {
            if (click.getClickCount() == 1) {
                try {
                    TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
                    int row = pos.getRow();
                    int col = pos.getColumn();
                    TableColumn column = pos.getTableColumn();
                    String val = column.getCellData(row).toString();
                    if (col == 0) {
                        try {
                            comboBoxHead.getEditor().setText(val);
                            dispHead.clear();
                            DisplayHeader.findHead(val, dispHead);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (Exception e) {
                }
            }
        });

        name.setCellFactory(cellFactory);
        parameter.setCellFactory(cellFactory);
        returnType.setCellFactory(cellFactory);

        IDanchorPane.setStyle("-fx-background-color: rgb(50, 60, 70);"); //sets colour of anchorpane

        //sets CSS to the ComboBoxes which sets their fonts
        String path = HomePageController.class.getResource("Contents/CSS/comboBox.css").toExternalForm();
        comboBoxFun.getStylesheets().add(path);
        comboBoxHead.getStylesheets().add(path);
        
        //sets CSS to the table
        String path2 = HomePageController.class.getResource("Contents/CSS/table.css").toExternalForm();
        table.getStylesheets().add(path2);
        Label label = new Label("");
        table.setPlaceholder(label);

        try {
            GenerateHeaderList.generateHead(comboBoxFun);
            new AutoSuggestion<String>(comboBoxFun, dispHead, 1, table);
        } catch (Exception ex) {
        }

        try {
            GenerateFunctionList.generateFun(comboBoxHead);
            new AutoSuggestion<String>(comboBoxHead, dispHead, 2, table);
        } catch (Exception ex) {
        }
    }

    /**
     * Searches Functions on ENTER press
     * @param event action event
     * @throws IOException 
     */
    @FXML
    private void handleEnterPressedFun(KeyEvent event) throws IOException {
        if (KeyCode.ENTER == event.getCode()) {
            table.getItems().clear();
            String name = comboBoxFun.getEditor().getText();
            if (name.length() != 0) {
                DisplayFunctions.findFun(name, table);
            }
        }
    }
    
    /**
     * Searches Headers on ENTER press
     * @param event action event
     * @throws IOException 
     */
    @FXML
    private void handleEnterPressedHead(KeyEvent event) throws IOException {
        if (KeyCode.ENTER == event.getCode()) {
            dispHead.clear();
            String name = comboBoxHead.getEditor().getText();
            if (name.length() != 0) {
                DisplayHeader.findHead(name, dispHead);
            }
        }
    }

    /**
     * Exits on ESC press
     * @param event action event
     * @throws IOException 
     */
    @FXML
    private void handleEscPressed(KeyEvent event) throws IOException {
        if (KeyCode.ESCAPE == event.getCode()) {
            Parent parent = FXMLLoader.load(getClass().getResource("Confirm.fxml"));
            Scene sceneConf = new Scene(parent);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getModality();
            stage.setScene(sceneConf);
            stage.show();
        }
    }

    /**
     *Sets 'Details' on action on mouse click.
     * 
     * @param event action event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void HyperLinkAction(ActionEvent event) throws IOException, URISyntaxException {
        link = DisplayHeader.getLink();
        String Blank = "/";
        if (!link.equals(Blank)) {
            Parent parent = FXMLLoader.load(getClass().getResource("Details.fxml"));
            Scene sceneDet = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Details");
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getModality();
            stage.setScene(sceneDet);
            stage.show();
        }
    }
    
    /**
     *Sets 'Details' on action on ENTER press.
     * 
     * @param event action event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void handleEnterPressedHyperLink(KeyEvent event) throws IOException, URISyntaxException {
        if (KeyCode.ENTER == event.getCode()) {
            link = DisplayHeader.getLink();
            String Blank = "/";
            if (!link.equals(Blank)) {
                Parent parent = FXMLLoader.load(getClass().getResource("Details.fxml"));
                Scene sceneDet = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Details");
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.getModality();
                stage.setScene(sceneDet);
                stage.show();
            }
        }
    }
}
