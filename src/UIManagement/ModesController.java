package UIManagement;

import GameManagement.BoardTest;
import GameManagement.GamePlay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ModesController {
    @FXML
    Stage stage;

    public ModesController() {
    }

    @FXML
    public void handleChallenge(ActionEvent event) {
        GamePlay gamePlay = new GamePlay( "ChallangeMode" );
        gamePlay.start(new Stage());
        this.stage.setScene(gamePlay.returnScene());
        this.stage.setResizable(true);
    }

    @FXML
    public void handleNormal(ActionEvent event) {
        GamePlay gamePlay = new GamePlay( "NormalMode" );
        Stage stage = new Stage();
        stage.setFullScreen(true);
        stage.setMaximized(true);
        gamePlay.start(stage);
        this.stage.setScene(gamePlay.returnScene());
        this.stage.setResizable(true);
    }

    @FXML
    public void handleDynamic(ActionEvent event) {
        GamePlay gamePlay = new GamePlay( "DynamicMode" );
        gamePlay.start(new Stage());
        this.stage.setScene(gamePlay.returnScene());
        this.stage.setResizable(true);
    }

    @FXML
    public void handleReturn(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }
}
