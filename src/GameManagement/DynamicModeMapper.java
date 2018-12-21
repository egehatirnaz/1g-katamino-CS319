package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.nio.file.Paths;
import java.util.ArrayList;

public class DynamicModeMapper extends GameMapper {

    private String password;

    public DynamicModeMapper( String password )
    {
        this.password = password;
    }

    @Override
    void setGame( int currentLevel ) {
        switch (currentLevel){
            case 1:
                setSquares( 3, 4);
                break;
            case 2:
                setSquares( 5, 4 );
                break;
            case 3:
                setSquares( 7 , 6 );
                break;
        }
    }

    @Override
    void setSquares(int width, int height){
        Square[][] squares = super.getSquares();
        squares = new Square[width][height];
        for(int i = 0; i < squares.length; i++){
            double xCoor = i * super.getSQUARESIZE() + super.getBOARDCOORDX();
            for(int j = 0; j < squares[i].length; j++){
                double yCoor = j * super.getSQUARESIZE() + super.getBOARDCOORDY();
                squares[i][j] = new Square(xCoor, yCoor, super.getSQUARESIZE(), super.getSQUARESIZE());
                    squares[i][j].getRect().setStroke(Color.BURLYWOOD);
                    squares[i][j].getRect().setFill(Color.GRAY);
            }
        }
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

    }

    @Override
    void setStickView(int currentLevel) {}

}
