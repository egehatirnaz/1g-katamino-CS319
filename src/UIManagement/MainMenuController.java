package UIManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML protected void handleNormalMode(ActionEvent event) throws Exception{
        System.out.println("Normal Mode Pressed!");

    }

    @FXML protected void handleChallengeMode(ActionEvent event) {
        System.out.println("Challenge Mode Pressed!");
    }

    @FXML protected void handleDynamicMode(ActionEvent event) {
        System.out.println("Dynamic Mode Pressed!");
    }

    @FXML protected void handleHowToPlay(ActionEvent event) {
        System.out.println("How To Play Pressed!");
    }

    @FXML protected void handleSolutions(ActionEvent event) {
        System.out.println("Solutions Pressed!");
    }

    @FXML protected void handleLeaderboard(ActionEvent event) {
        System.out.println("Leaderboard Pressed!");
    }

}
