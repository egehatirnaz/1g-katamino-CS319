package GameManagement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GamePlay extends Application {

    // properties
    GameMapper gameMapper;
    final String password = "yusuf123";
    private Scene window;
    double originX, originY;
    Scene scene;

    public GamePlay( String gameMode  )
    {
        if( gameMode == "NormalMode" )
        {
            gameMapper = new NormalModeMapper(password);
        }
        else if( gameMode == "ChallangeMode" )
        {
            gameMapper = new ChallangeModeMapper( password );
        }
        else
        {
            gameMapper = new DynamicModeMapper( password );
        }
    }

    @Override
    public void start(Stage primaryStage) {



        primaryStage.setTitle("Katamino");
        primaryStage.setScene(scene);
        primaryStage.show();
        window = scene;
    }
}
