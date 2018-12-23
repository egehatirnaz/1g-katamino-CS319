package UIManagement;

import GameManagement.BoardTest;
import GameManagement.GamePlay;
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
    @FXML RadioButton signIn;
    @FXML private Button save, cancel;
    MediaPlayer mp;
    Stage stage;
    PlayerDatabase pD;
    @FXML TextField nicknameID;
    @FXML Text entText, secEnd;
    private MediaPlayer media;

    @FXML
    protected void handleSaveClick(ActionEvent event) throws IOException {
        pD = new PlayerDatabase(GamePlay.password);
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Scene scene = (Scene)((Button) event.getSource()).getScene();
        TextField tf = (TextField) scene.lookup("#nicknameID");
        String username = "Unnamed Player";
        if(tf.getText() != null && !tf.getText().isEmpty()){
            username = tf.getText();
        }
        VBox layout1 = new VBox(20);
        if(signIn.isSelected() && !(pD.controlNickName(nicknameID.getText()))){
            System.out.println("updated" + nicknameID.getText());
            int time = pD.getTime(nicknameID.getText());
            pD.updatePlayerTime(nicknameID.getText(), time);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modes.fxml"));
            layout1.getChildren().setAll((Parent)loader.load());
            Scene scene1 = new Scene(layout1, 600, 400);
            stage.setScene(scene1);
        }
        else if(!signIn.isSelected() && pD.controlNickName(nicknameID.getText())){
            System.out.println("added"+ nicknameID.getText());
            pD.addPlayer(nicknameID.getText(),99999);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modes.fxml"));
            layout1.getChildren().setAll((Parent)loader.load());
            Scene scene1 = new Scene(layout1, 600, 400);
            stage.setScene(scene1);

        }
        else{
            System.out.println("hata" + nicknameID.getText());
            entText.setText("");
            secEnd.setText("The nickname has already exist! Use another one to join or sign in.");
        }
        /*if(!(pD.controlNickName(nicknameID.getText()))){
            System.out.println("updated" + nicknameID.getText());
            int time = pD.getTime(nicknameID.getText());
            pD.updatePlayerTime(nicknameID.getText(), time);
        }
        else{
            pD.addPlayer(nicknameID.getText(),0);
            System.out.println("added"+ nicknameID.getText());
        }*/
        //Parent root;
        //root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        pD.closeDatabase();
    }

    @FXML
    protected void handleCancelClick(ActionEvent event) throws IOException{
        mp.play();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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
