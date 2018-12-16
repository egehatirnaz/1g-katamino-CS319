package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

abstract class CommonMapper extends GameMapper {

    private final double STICKCOORDX = 635.0;
    private final double STICKCOORDY = 30.0;
    private final double SQUARESIZE = 100.0;
    private final int BOARDWIDTH = 13;
    private final int BOARDHEIGHT = 5;

    public CommonMapper(){
    }

    @Override
    protected void setGame( int currentLevel){
        super.setSquares( BOARDWIDTH, BOARDHEIGHT, currentLevel );
        setInitialImageList();
        super.setupEntity(getInitialImageList());
    }

    abstract ArrayList<ImageView> getInitialImageList();
    abstract void setInitialImageList();

    @Override
    ImageView getStickView( int currentLevel ) {

        Image stick = new Image(Paths.get("src/GameManagement/media/Stick.png").toUri().toString());

        ImageView stickView = new ImageView(stick);

        stickView.setX(STICKCOORDX + ( (currentLevel - 2) * SQUARESIZE ) );
        stickView.setY(STICKCOORDY);
        return  stickView;
    }
    @Override
    protected boolean isLevelFinished( int currentLevel ){
        Square[][] squares = super.getSquares();
        for(int i = 0; i < currentLevel; i++){
            for(int j = 0; j < squares[i].length; j++) {
                if(squares[i][j].getStateOfSquare()==false)
                    return false;
            }
        }
        return true;
    }
}
