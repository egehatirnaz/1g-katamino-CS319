package UIManagement.FXML.TUR;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class HowToPlayController {
    Stage stage;
    MediaPlayer mp;

    public HowToPlayController() {
    }

    public void handleBack(ActionEvent event) throws IOException {
        mp.play();
        this.stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainMenu.fxml"));
        VBox layout1 = new VBox(20.0D);
        layout1.getChildren().setAll(new Node[]{(Parent)loader.load()});
        Scene scene1 = new Scene(layout1, 600.0D, 400.0D);
        this.stage.setScene(scene1);
    }
    @FXML
    protected void initialize(){
        mp = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/ding.mp3").toUri().toString()));
    }
}
