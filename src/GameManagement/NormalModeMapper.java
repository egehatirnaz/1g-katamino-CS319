package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

public class NormalModeMapper extends CommonMapper {

    private ArrayList<ImageView> imageList;
    public  NormalModeMapper(){
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
        /*
        database'den currentlevella ilgili dosya yeri veya block sayısı gelmeli,

         */
        String str = "src/GameManagement/media/n1.png";
        Image green = new Image( Paths.get(str).toUri().toString());
        Image blue = new Image( Paths.get( "src/GameManagement/media/n2.png" ).toUri().toString() );
        Image yellow = new Image( Paths.get( "src/GameManagement/media/n3.png" ).toUri().toString() );
        ImageView img1 = new ImageView( green );
        ImageView img2 = new ImageView( blue );
        ImageView img3 = new ImageView( yellow );
        imageList.add( img1 );
        imageList.add( img2 );
        imageList.add( img3 );
    }

}
