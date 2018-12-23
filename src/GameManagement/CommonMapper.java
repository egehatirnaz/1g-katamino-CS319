package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

abstract class CommonMapper extends GameMapper {

    private final double STICKCOORDX = 235.0;
    private final double STICKCOORDY = 30.0;
    private final double SQUARESIZE = 100.0;
    private final int BOARDWIDTH = 13;
    private final int BOARDHEIGHT = 5;
    private ImageView stickView;

    // constructor
    public CommonMapper(){}

    // abstract methods
    abstract ArrayList<ImageView> getInitialImageList();
    abstract void setInitialImageList();

    // override methods
    @Override
    protected void setGame( int currentLevel){
        System.out.println( "SETGAME currentLevel: " + currentLevel );
        super.setSquares( BOARDWIDTH, BOARDHEIGHT);
        setInitialImageList();
        if(!isGameFinished()) {
            setStickView(currentLevel);
            super.setupEntity(getInitialImageList());
        }
    }

    @Override
    public ImageView getStickView()
    {
        return  stickView;
    }


    @Override
    protected void setStickView( int currentLevel ) {

        Image stick = new Image(Paths.get("src/GameManagement/media/Stick.png").toUri().toString());
        stickView = new ImageView( stick );
        stickView.setX(super.getBOARDCOORDX() + ( currentLevel * SQUARESIZE ) - 15);
        stickView.setY(super.getBOARDCOORDY() - 70 );
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
        updateLevel();
        return true;
    }

}
