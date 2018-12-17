package GameManagement;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class DynamicModeMapper extends GameMapper {

    @Override
    void setGame( int currentLevel ) {
        super.setSquares( currentLevel, currentLevel ,currentLevel);

    }

    @Override
    ImageView getStickView() {
        return null;
    }


    @Override
    ArrayList<ImageView> getInitialImageList() {
        return null;
    }

    @Override
    boolean isLevelFinished(int currentLevel) {
        return false;
    }

    @Override
    void setInitialImageList() {

    }

    @Override
    void setStickView(int currentLevel) {}

}
