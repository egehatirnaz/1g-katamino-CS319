package UIManagement;

import GameManagement.BoardTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsController {
    public Button butt;
    //ControlManager neC =new ControlManager();

    @FXML
    protected void handleSaveClick(){

        /*if(neC.sth){
            oyun ekranina git ve kaydet
        }
        else{
            geri don
        }*/

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        StackPane layout = new StackPane();
        Scene scene;
        BoardTest bT = new BoardTest();
        bT.start(new Stage());
        scene = bT.returnScene();
        stage.setScene(scene);
        System.out.println("Helo");

    }
}
