package UIManagement.FXML.TUR;

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

public class LeaderBoardController {
    Stage stage;
    MediaPlayer mp;
    public void handleBack(ActionEvent event) throws IOException {
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
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
