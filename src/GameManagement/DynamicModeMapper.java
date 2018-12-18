package GameManagement;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DynamicModeMapper extends GameMapper {

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

    }

    @Override
    void setStickView(int currentLevel) {}

}
