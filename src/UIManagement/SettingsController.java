package UIManagement;

import GameManagement.BoardTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class SettingsController {
    @FXML private RadioButton signIn, music;
    @FXML private Button save, cancel;
    @FXML Stage stage;
    private MediaPlayer media;
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
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Modes.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);

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

    public void handleMusic(ActionEvent event) {
        if(music.isSelected()){
            media = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/title.mp3").toUri().toString()));
            media.play();
            music.setText("Muzik");
        }
        else{
            media.setVolume(0);
        }
    }

    public void handleSignIn(ActionEvent event) {
    }
}
