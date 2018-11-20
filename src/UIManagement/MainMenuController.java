package UIManagement;

import GameManagement.BoardTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.control.ScrollPaneBuilder;
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

public class MainMenuController {

    @FXML
    private Button minimizeButton;
    double x,y;

    @FXML
    Stage stage;

    @FXML protected void handleCredits(ActionEvent event) throws IOException{
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Credits.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        /*String message =
                "Earthrise at Christmas: " +
                        "[Forty] years ago this Christmas, a turbulent world /n" +
                        "[Forty] years ago this Christmas, a turbulent world /n "  +
                        "[Forty] years ago this Christmas, a turbulent world /n " +
                        "was taken by the Apollo 8 crew in December 1968, showing " +
                        "Earth for the first time as it appears from deep space. " +
                        "Astronauts Frank Borman, Jim Lovell and William Anders " +
                        "had become the first humans to leave Earth orbit, " +
                        "entering lunar orbit on Christmas Eve. In a historic live " +
                        "broadcast that night, the crew took turns reading from " +
                        "the Book of Genesis, closing with a holiday wish from " +
                        "Commander Borman: \"We close with good night, good luck, " +
                        "a Merry Christmas, and God bless all of you -- all of " +
                        "you on the good Earth.\"";

        // Reference to the Text
        Text textRef = TextBuilder.create()
                .layoutY(100)
                .textOrigin(VPos.TOP)
                .textAlignment(TextAlignment.JUSTIFY)
                .wrappingWidth(600)
                .text(message)
                .fill(Color.rgb(38, 46, 41))
                .font(Font.font("SansSerif", FontWeight.BOLD, 24))
                .build();

        // Provides the animated scrolling behavior for the text
        TranslateTransition transTransition = TranslateTransitionBuilder.create()
                .duration(new Duration(75000))
                .node(textRef)
                .toY(-820)
                .interpolator(Interpolator.LINEAR)
                .cycleCount(Timeline.INDEFINITE)
                .build();

        scene.add*/
    }

    @FXML protected void handleStart(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
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


    @FXML
    protected void handleExit(ActionEvent event) {
        System.out.println("quit pressed");
        System.exit(0);
    }
}
