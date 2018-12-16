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
        String str = "src/GameManagement/media/green.png";
        Image green = new Image( Paths.get(str).toUri().toString());
        Image blue = new Image( Paths.get( "src/GameManagement/media/aBlock.png" ).toUri().toString() );
        Image yellow = new Image( Paths.get( "src/GameManagement/media/yellow.png" ).toUri().toString() );
        ImageView img1 = new ImageView( green );
        ImageView img2 = new ImageView( blue );
        ImageView img3 = new ImageView( yellow );
        imageList.add( img1 );
        imageList.add( img2 );
        imageList.add( img3 );
    }

}
