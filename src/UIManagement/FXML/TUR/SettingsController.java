package UIManagement.FXML.TUR;

import GameManagement.BoardTest;
import UIManagement.Lang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class SettingsController {
    public Button butt;
    private double x,y;
    @FXML
    Stage stage;
    MediaPlayer mp;
    //ControlManager neC =new ControlManager();

    @FXML
    protected void handleSaveClick(ActionEvent event) throws IOException {
        mp.play();

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
        /*
        Scene scene = (Scene)((Button) event.getSource()).getScene();
        TextField tf = (TextField) scene.lookup("#nicknameID");
        String username = "Unnamed Player";
        if(tf.getText() != null && !tf.getText().isEmpty()){
            username = tf.getText();
        }
        BoardTest bT = new BoardTest();
        //bT.setUsername(username); TODO:add this method to BoardTest
        bT.start(new Stage());
        stage.setScene(bT.returnScene());
        stage.setResizable(true);*/
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Scene scene = (Scene)((Button) event.getSource()).getScene();
        TextField tf = (TextField) scene.lookup("#nicknameID");
        String username = "İsimsiz Oyuncu";
        if(tf.getText() != null && !tf.getText().isEmpty()){
            username = tf.getText();
        }
        //TODO: Do what you want with the username input.

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
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }

    @FXML
    protected void changeLanguageEN(MouseEvent event) throws IOException{
        stage = (Stage)((ImageView) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../../Settings.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Settings.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }
    @FXML
    protected void changeLanguageTR(){
        System.out.println("TR Selected - As bayrakları as!");
    }

    @FXML
    public void handleSignIn(MouseEvent event) {
    }

    @FXML
    protected void initialize(){
        mp = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/ding.mp3").toUri().toString()));
    }

}
