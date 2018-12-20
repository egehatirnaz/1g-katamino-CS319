package GameManagement;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ChallangeModeMapper extends CommonMapper{


    //properties
    private ArrayList<ImageView> imageList;

    // constructor
    public ChallangeModeMapper(){
        imageList = new ArrayList<>();
        setGame(getCurrentLevel());
    }

    @Override
    ArrayList<ImageView> getInitialImageList() {
        return imageList;
    }

    @Override
    void setInitialImageList() {

    }
}
