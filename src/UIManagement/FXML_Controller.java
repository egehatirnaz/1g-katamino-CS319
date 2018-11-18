package UIManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FXML_Controller implements Initializable {
    @FXML
    private VBox rootPane;

    @Override
    public void initialize(URL url, ResourceBundle bundle){

    }

    @FXML
    public void loadPlay(ActionEvent event) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("NormalMode.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void loadMainMenu(ActionEvent event) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
