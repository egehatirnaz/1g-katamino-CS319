package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.nio.file.Paths;
import java.util.ArrayList;

public class DynamicModeMapper extends GameMapper {

    private ArrayList<ImageView> imageList;
    private SolutionDatabase solutionDatabase;
    private int width;
    private int height;


    public DynamicModeMapper( String password )
    {
        solutionDatabase = new SolutionDatabase(password);
        imageList = new ArrayList<>();
        setGame(getCurrentLevel());
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
        this.width = width;
        this.height = height;
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
        return imageList;
    }

    @Override
    boolean isLevelFinished(int currentLevel) {
        Square[][] squares = super.getSquares();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                if(squares[i][j].getStateOfSquare()==false)
                    return false;
            }
        }
        super.updateLevel();
        return true;
    }

    @Override
    void setInitialImageList() {

        int currentLevel = getCurrentLevel();
        imageList.clear();
        String str;
        ArrayList<String> solutionList = solutionDatabase.getSolution( "DynamicMode", currentLevel, 1);
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
