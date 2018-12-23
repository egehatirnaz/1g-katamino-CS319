package UIManagement;

import GameManagement.BoardTest;
import GameManagement.GamePlay;
import GameManagement.Player;
import GameManagement.PlayerDatabase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainMenuController {
    @FXML
    private Button minimizeButton;
    double x,y;
    MediaPlayer mp;
    PlayerDatabase pD;

    @FXML
    Stage stage;
    UI_Object ui_object;

    @FXML protected void handleCredits(ActionEvent event) throws IOException{
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Credits.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @FXML protected void handleStart(ActionEvent event) throws IOException {
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        /*scene.setOnMousePressed(e -> {
            x = e.getSceneX();
            y = e.getSceneY();
        });
        scene.setOnMouseDragged(e -> {
            scene.setX(e.getScreenX() - x);
            scene.setY(e.getScreenY() - y);
        });*/
        System.out.println("Start Pressed");
    }

    // Leaderboard için sonradan eklenen zımbırtılar. -Ege
    @FXML protected void handleLeaderboard(ActionEvent event) throws IOException {
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        Scene scene = new Scene(root);
        TableView tb = (TableView) scene.lookup("#tableview");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        System.out.println("Leaderboard Pressed");
    }
    @FXML protected void handleReturn(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        System.out.println("ReturnToMenu Pressed");
    }

    @FXML
    protected void handleExit(ActionEvent event) {
        System.out.println("quit pressed");
        System.exit(0);
    }

    @FXML
    public void handleHowToPlay(ActionEvent event) throws IOException{
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @FXML
    protected void changeLanguageEN(){
        System.out.println("EN Selected - God save the queen!");
        //lang.setEnglish();
    }
    @FXML
    protected void changeLanguageTR(MouseEvent event) throws IOException{
        mp = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/stomp.wav").toUri().toString()));
        mp.play();
        mp = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/ding.mp3").toUri().toString()));
        stage = (Stage)((ImageView) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXML/TUR/MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/TUR/MainMenu.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }
    @FXML
    protected void initialize(){
        mp = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/ding.mp3").toUri().toString()));
    }
    @FXML
    protected void toggleMusic(MouseEvent event) throws IOException{

        stage = (Stage)((RadioButton) event.getSource()).getScene().getWindow();
        RadioButton music = (RadioButton) stage.getScene().lookup("#music");
        UI_Object.toggleMusic(music.isSelected());
        /*
        if(music.isSelected()){
            ui_object.returnMediaPlayer().stop();

        }
        else{
            ui_object.returnMediaPlayer().stop();
            ui_object.returnMediaPlayer().play();
        }*/
    }

    public void handleLeaderBoard(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
        //System.out.println(root.getLayoutX());
        pD = new PlayerDatabase(GamePlay.password);
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        VBox layout1 = new VBox(10);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        ArrayList<Player> pArr = pD.getSortedPlayers();
        Text use1 = (Text) scene1.lookup("#use1");
        use1.setText(pArr.get(0).getNickName());
        Text use2 = (Text) scene1.lookup("#use2");
        use2.setText(pArr.get(1).getNickName());
        Text use3 = (Text) scene1.lookup("#use3");
        use3.setText(pArr.get(2).getNickName());
        Text use4 = (Text) scene1.lookup("#use4");
        use4.setText(pArr.get(3).getNickName());
        Text use5 = (Text) scene1.lookup("#use5");
        use5.setText(pArr.get(4).getNickName());
        Text score1 = (Text) scene1.lookup("#score1");
        score1.setText(pArr.get(0).getTime() + "");
        Text score2 = (Text) scene1.lookup("#score2");
        score2.setText(pArr.get(1).getTime() + "");
        Text score3 = (Text) scene1.lookup("#score3");
        score3.setText(pArr.get(2).getTime() + "");
        Text score4 = (Text) scene1.lookup("#score4");
        score4.setText(pArr.get(3).getTime() + "");
        Text score5 = (Text) scene1.lookup("#score5");
        score5.setText(pArr.get(4).getTime() + "");
        stage.setScene(scene1);
        pD.closeDatabase();
    }
}
