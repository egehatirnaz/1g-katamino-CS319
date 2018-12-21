package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import java.util.concurrent.TimeUnit;

public class NormalModeMapper extends CommonMapper {

    private ArrayList<ImageView> imageList;
    private SolutionDatabase solutionDatabase;
    public  NormalModeMapper(){
        solutionDatabase = new SolutionDatabase("8222");
        imageList = new ArrayList<>();
        setGame(getCurrentLevel());
    }



    @Override
    ArrayList<ImageView> getInitialImageList() {
        return  imageList;
    }

    @Override
    void setInitialImageList() {
        int currentLevel = getCurrentLevel();
        imageList.clear();
        System.out.println( "setInitialImage: "+ currentLevel );
        String str;
        System.out.println( solutionDatabase );
        ArrayList<String> solutionList = solutionDatabase.getSolution( "NormalMode", currentLevel, 1);
        for( int i = 0; i < solutionList.size(); i++ )
        {
            str = "src/GameManagement/media/" + solutionList.get(i) + ".png";
            Image block = new Image( Paths.get(str).toUri().toString());
            ImageView blockView = new ImageView(block);
            imageList.add(blockView);
        }

        /*
        Image green = new Image( Paths.get(str).toUri().toString());
        Image blue = new Image( Paths.get( "src/GameManagement/media/n2.png" ).toUri().toString() );
        Image yellow = new Image( Paths.get( "src/GameManagement/media/n3.png" ).toUri().toString() );
        ImageView img1 = new ImageView( green );
        ImageView img2 = new ImageView( blue );
        ImageView img3 = new ImageView( yellow );
        imageList.add( img1 );
        imageList.add( img2 );
        imageList.add( img3 );*/
    }

    void startTime(TimeKeeper t, Text updateT){
        //this.isLevelFinished(getCurrentLevel());
        t.startTimer(00);
        t.run(updateT);
    }

}
