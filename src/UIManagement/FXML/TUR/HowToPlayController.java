package UIManagement.FXML.TUR;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HowToPlayController {
    Stage stage;

    public HowToPlayController() {
    }

    public void handleBack(ActionEvent event) throws IOException {
        this.stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainMenu.fxml"));
        VBox layout1 = new VBox(20.0D);
        layout1.getChildren().setAll(new Node[]{(Parent)loader.load()});
        Scene scene1 = new Scene(layout1, 600.0D, 400.0D);
        this.stage.setScene(scene1);
    }
}
