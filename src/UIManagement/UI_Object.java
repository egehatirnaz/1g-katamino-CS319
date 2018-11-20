package UIManagement;

import GameManagement.BoardTest;
import javafx.application.Application;
import javafx.event.EventHandler;
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

import java.nio.file.Paths;

public class UI_Object extends Application {
    Stage window;
    Scene scene1, scene2;
    Scene scene;
    Parent scene3;
    private MediaPlayer media;
    private double x,y;

    @Override
    public void start(Stage stage) throws Exception{
        window = stage;
        media = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/title.mp3").toUri().toString()));
        media.play();

        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        scene1 = new Scene(layout1, 600, 400);
        Button butt = (Button) loader.getNamespace().get("startID");
        Button buttQ = (Button) loader.getNamespace().get("quitID");;

        butt.setOnAction(event->{
            play();
            /*FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Settings.fxml"));
            Button butt2 = (Button) loader1.getNamespace().get("saveID");
            window.setScene(new Scene(root1));
            window.initStyle(StageStyle.UNDECORATED);
            butt2.setOnAction(e->play());
            root1.setOnMousePressed(event1 -> {
                x = event1.getSceneX();
                y = event1.getSceneY();
            });
            root1.setOnMouseDragged(event1 -> {
                window.setX(event1.getScreenX() - x);
                window.setY(event1.getScreenY() - y);
            });*/
        });
        Button butt1 = (Button) loader.getNamespace().get("settingsID");
        //for dragging
        butt1.setOnAction(e->{
            window.setScene(new Scene(root1));
            window.initStyle(StageStyle.UNDECORATED);
            root1.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root1.setOnMouseDragged(event -> {
                window.setX(event.getScreenX() - x);
                window.setY(event.getScreenY() - y);
            });
        });

        /*buttQ.setOnAction(e->{
            window.close();
        });*/
        //L1


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
        window.initStyle(StageStyle.UNDECORATED);
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
