package UIManagement.FXML.TUR;

import UIManagement.Lang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    // Leaderboard için sonradan eklenen zımbırtılar. -Ege
    @FXML protected void handleLeaderboard(ActionEvent event) throws IOException {
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

    /*
    @FXML
    public void initialize(){
        lang.setTurkish();
        minimizeButton = new Button();

        Scene scene = minimizeButton.getScene();


        Button quitButt = (Button) scene.lookup("#quitID");
        Button leaderboardButt = (Button) scene.lookup("#leaderboardID");
        Button creditsButt = (Button) scene.lookup("#creditsID");
        Button startButt = (Button) scene.lookup("#startID");

        System.out.println(lang.strings[4]);

        quitButt.setText(lang.strings[4]);
        leaderboardButt.setText(lang.strings[2]);
        creditsButt.setText(lang.strings[3]);
        startButt.setText(lang.strings[1]);
    }
    */
}
