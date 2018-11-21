package UIManagement;

import GameManagement.BoardTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    public Button butt;
    private double x,y;
    @FXML
    Stage stage;
    //ControlManager neC =new ControlManager();

    @FXML
    protected void handleSaveClick(ActionEvent event) throws IOException {

        /*if(neC.sth){
            oyun ekranina git ve kaydet
        }
        else{
            geri don
        }*/
        /*
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        StackPane layout = new StackPane();
        Scene scene;
        BoardTest bT = new BoardTest();
        bT.start(new Stage());
        scene = bT.returnScene();
        stage.setScene(scene);
        System.out.println("Helo");*/
        //Scene scene = (Scene)((Button) event.getSource()).getScene();
        BoardTest bT = new BoardTest();
        bT.start(new Stage());
        stage.setScene(bT.returnScene());
        stage.setResizable(true);

    }

    @FXML
    protected void handleCancelClick(ActionEvent event) throws IOException{
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }

}
