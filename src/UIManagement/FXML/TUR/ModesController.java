package UIManagement.FXML.TUR;

import GameManagement.BoardTest;
import GameManagement.GamePlay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class ModesController {

    Stage stage;
    String nick;
    MediaPlayer mp;

    public ModesController() {}

    @FXML
    public void handleChallenge(ActionEvent event) throws IOException{
        // Play sound
        mp.play();

        // Go back to main menu
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);



        // Start the game
        GamePlay gamePlay = new GamePlay( "ChallangeMode" );
        gamePlay.start(new Stage());
        //this.stage.setScene(gamePlay.returnScene());
        //this.stage.setResizable(true);
    }

    @FXML
    public void handleNormal(ActionEvent event) throws IOException{
        // Play sound
        mp.play();

        // Go back to main menu
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        VBox layout2 = new VBox(20);
        layout2.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout2, 600, 400);
        stage.setScene(scene1);



        // Start the game
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        GamePlay gamePlay = new GamePlay( "NormalMode" );
        Stage stage = new Stage();
        // stage.setFullScreen(true);
        // stage.setMaximized(true);
        gamePlay.start(stage);
        VBox layout1 = new VBox(20);
        //Scene sceneNormal = gamePlay.returnScene();
        stage.setScene(gamePlay.returnScene());
        //this.stage.setScene(gamePlay.returnScene());
        //this.stage.setResizable(true);
    }

    @FXML
    public void handleDynamic(ActionEvent event) throws IOException{
        // Play sound
        mp.play();

        // Go back to main menu
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);



        // Start the game
        GamePlay gamePlay = new GamePlay( "DynamicMode" );
        gamePlay.start(new Stage());
        //this.stage.setScene(gamePlay.returnScene());
        //this.stage.setResizable(true);
    }

    @FXML
    public void handleReturn(ActionEvent event) throws IOException {
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }

    @FXML
    protected void initialize(){
        mp = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/ding.mp3").toUri().toString()));
    }
}
