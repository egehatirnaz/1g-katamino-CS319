package UIManagement;

import GameManagement.BoardTest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.Window;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.nio.file.Paths;

public class UI_Object extends Application {
    Stage window;
    Scene scene1, scene2;
    Scene scene;
    private double x,y;

    // God dislikes lazy static variables.
    private static MediaPlayer media = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/title.mp3").toUri().toString()));
    @Override
    public void start(Stage stage) throws Exception{
        window = stage;
        //Parent root;
        //root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        //Parent root1 = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        scene1 = new Scene(layout1, 600, 400);
        VBox layout2 = new VBox(20);
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("NormalMode.fxml"));
        layout2.getChildren().setAll((VBox)loader2.load());
        scene2 = new Scene(layout2, 640, 425);
        scene1.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        scene1.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - x);
            window.setY(event.getScreenY() - y);
        });

        media.play();
        media.setCycleCount(MediaPlayer.INDEFINITE);
        window.setScene(scene1);
        window.initStyle(StageStyle.UNDECORATED);
        window.setTitle("Katamino");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static MediaPlayer returnMediaPlayer(){
        return media;
    }

    public static void toggleMusic(boolean on){
        if(on){
            media.stop();
        }
        else{
            media.play();
        }
    }
}
