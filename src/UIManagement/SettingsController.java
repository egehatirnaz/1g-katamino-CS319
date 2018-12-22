package UIManagement;

import GameManagement.BoardTest;
import GameManagement.PlayerDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class SettingsController {
    @FXML private RadioButton signIn, music;
    @FXML private Button save, cancel;
    Stage stage;
    PlayerDatabase pD = new PlayerDatabase("yusuf123");
    @FXML TextField nicknameID;
    private MediaPlayer media;
    //ControlManager neC =new ControlManager();
    @FXML
    protected void handleSaveClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Scene scene = (Scene)((Button) event.getSource()).getScene();
        TextField tf = (TextField) scene.lookup("#nicknameID");
        String username = "Unnamed Player";
        if(tf.getText() != null && !tf.getText().isEmpty()){
            username = tf.getText();
        }
        //TODO: Do what you want with the username input.
        //pD.addPlayer(nicknameID.getText(), 0);
        //ModesController mm = new ModesController(nicknameID.getText());
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Modes.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }

    @FXML
    protected void handleCancelClick(ActionEvent event) throws IOException{
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }

    public void handleMusic(ActionEvent event) {
        if(music.isSelected()){
            media = new MediaPlayer(new Media(Paths.get("src/GameManagement/media/title.mp3").toUri().toString()));
            media.play();
            music.setText("Muzik");
        }
        else{
            media.setVolume(0);
        }
    }
    @FXML
    public void handleSignIn(MouseEvent event) {

    }

    @FXML
    protected void changeLanguageEN(){
        System.out.println("EN Selected - God save the queen!");
        //lang.setEnglish();
    }
    @FXML
    protected void changeLanguageTR(MouseEvent event) throws IOException{
        stage = (Stage)((ImageView) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXML/TUR/Settings.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/TUR/Settings.fxml"));
        VBox layout1 = new VBox(20);
        layout1.getChildren().setAll((Parent)loader.load());
        Scene scene1 = new Scene(layout1, 600, 400);
        stage.setScene(scene1);
    }

    public String getUsername(){
        return nicknameID.getText();
    }
}
