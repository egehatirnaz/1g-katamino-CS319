package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

public class NormalModeMapper extends CommonMapper {

    private ArrayList<ImageView> imageList;
    private SolutionDatabase solutionDatabase;
    public  NormalModeMapper(){
        solutionDatabase = new SolutionDatabase("yusuf123");
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

}
