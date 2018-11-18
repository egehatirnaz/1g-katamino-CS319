package UIManagement;


import GameManagement.BoardTest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class UI_Object extends Application {
    Stage window;
    Scene scene1, scene2;
    Parent scene3;
    private MediaPlayer media;

    @Override
    public void start(Stage stage) throws Exception{
        window = stage;
        media = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/title.mp3").toUri().toString()));
        media.play();

        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        VBox layout1 = new VBox(20);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        layout1.getChildren().setAll((VBox)loader.load());
        scene1 = new Scene(layout1, 640, 425);

        Button butt = (Button) loader.getNamespace().get("normalModeID");
        butt.setOnAction(e -> play());

        /*
        //L1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,button1);
        scene1 = new Scene(layout1, 200, 200);
        */



        /*
        //B2
        Button button2 = new Button("Go to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));
        */


        VBox layout2 = new VBox(20);
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("NormalMode.fxml"));
        layout2.getChildren().setAll((VBox)loader2.load());
        scene2 = new Scene(layout2, 640, 425);

        /*
        //L2
        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(button2);
        scene2 = new Scene(layout2, 200, 200);*/

        window.setScene(scene1);
        window.setTitle("Katamino");
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void play(){
        //VBox layout2 = new VBox(20);
        //FXMLLoader loader2 = new FXMLLoader(getClass().getResource("NormalMode.fxml"));
        BoardTest bT = new BoardTest();
        bT.start(new Stage());
        window.setScene(bT.returnScene());

        //layout2.getChildren().setAll((VBox)loader2.load());
        //scene2 = new Scene(layout2, 640, 425);
    }
}