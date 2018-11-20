package UIManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML protected void handleSettings(ActionEvent event) throws Exception{
        System.out.println("Settings Pressed");

    }

    @FXML protected void handleStart(ActionEvent event) {
        System.out.println("Start Pressed");
    }

    @FXML protected void handleExit(ActionEvent event) {
        System.exit(0);
    }


}