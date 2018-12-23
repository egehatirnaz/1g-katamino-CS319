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
    private double startCoordX;
    private double startCoordY;
    private Square[][] squares;
    private String password;
    protected int currentLevel;

    public DynamicModeMapper( String password )
    {
        currentLevel = 1;
        this.password = password;
        imageList = new ArrayList<>();
        setGame(currentLevel);
    }

    @Override
    void setGame( int currentLevel ) {
        switch (currentLevel){
            case 1:
                setSquares( 3, 4);
                setInitialImageList();
                super.setupEntity(imageList);
                break;
            case 2:
                setSquares( 5, 4 );
                setInitialImageList();
                super.setupEntity(imageList);
                break;
            case 3:
                setSquares( 6 , 6 );
                setInitialImageList();
                super.setupEntity(imageList);
                break;
                default: setInitialImageList();
                break;
        }
    }

    @Override
    int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void updateLevel() {
        currentLevel++;
        setGame(currentLevel);
    }

    @Override
    void setSquares(int width, int height){
        this.width = width;
        this.height = height;
        startCoordX = ( screenWidth - width * SQUARESIZE ) / 2;
        startCoordY = ( screenHeight - 750 ) / 2;
        squares = new Square[width][height];
        for(int i = 0; i < squares.length; i++){
            double xCoor = i * getSQUARESIZE() + startCoordX;
            //System.out.println( "DYNAMICMAPPER BOARDX: " + xCoor );
            for(int j = 0; j < squares[i].length; j++){
                double yCoor = j * getSQUARESIZE() + startCoordY;
               // System.out.println( "DYNAMICMAPPER BOARDY: " + yCoor );
                squares[i][j] = new Square(xCoor, yCoor, super.getSQUARESIZE(), super.getSQUARESIZE());
                    squares[i][j].getRect().setStroke(Color.BURLYWOOD);
                    squares[i][j].getRect().setFill(Color.AQUA);
            }
        }
    }

    @Override
    public Square[][] getSquares()
    {
        return squares;
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
      //  System.out.println("Width " + squares.length);
      //  System.out.println("Height " + squares[0].length);
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                if(squares[i][j].getStateOfSquare()==false)
                    return false;
            }
        }
        this.updateLevel();
        System.out.println("LEVEL IS FINISHED!!!!!!!!!!");
        return true;
    }

    @Override
    void setInitialImageList() {
        solutionDatabase = new SolutionDatabase(password);
        imageList.clear();
        String str;
        System.out.println( "DYNAMIC MODE CURRENT LEVEL: " + currentLevel );
        ArrayList<String> solutionList = solutionDatabase.getSolution( "DynamicMode", currentLevel, 1);
        if(solutionList.size() == 0)
            super.isGameFinished = true;
        else {
            for (int i = 0; i < solutionList.size(); i++) {
                str = "src/GameManagement/media/" + solutionList.get(i) + ".png";
                Image block = new Image(Paths.get(str).toUri().toString());
                ImageView blockView = new ImageView(block);
                imageList.add(blockView);
            }
        }
        solutionDatabase.closeDatabase();
    }
    @Override
    protected double getBOARDCOORDX() {
        return startCoordX;
    }

     @Override
    protected double getBOARDCOORDY() {
        return startCoordY;
    }

    @Override
    void setStickView(int currentLevel) {}


}
