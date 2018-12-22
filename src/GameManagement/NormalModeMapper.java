package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

public class NormalModeMapper extends CommonMapper {

    private ArrayList<ImageView> imageList;
    private SolutionDatabase solutionDatabase;

    public  NormalModeMapper(String password){
        solutionDatabase = new SolutionDatabase(password);
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
        ArrayList<String> solutionList = solutionDatabase.getSolution( "NormalMode", currentLevel, 4);
        for( int i = 0; i < solutionList.size(); i++ )
        {
            str = "src/GameManagement/media/" + solutionList.get(i) + ".png";
            Image block = new Image( Paths.get(str).toUri().toString());
            ImageView blockView = new ImageView(block);
            imageList.add(blockView);
        }

    }

}
