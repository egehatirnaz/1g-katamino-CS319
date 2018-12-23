/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 *
 * @author burak korkmaz
 * @author yusuf samsum
 */
public abstract class GameMapper {

    // properties
    private Square[][] squares;
   // protected int currentLevel;
    private ArrayList<Double>       originalHeightScale;
    private ArrayList<Double>       originalWidthScale;
    protected ArrayList<Double>       initialPositionX;
    protected ArrayList<Double>       initialPositionY;
    protected final double SQUARESIZE = 100.0;
    private final int SQUARENUMBER = 13;
   // private final double BOARDCOORDX = 250.0;
    //private final double BOARDCOORDY = 100.0;
    protected double screenWidth;
    protected double screenHeight;
    private ImageView awardView;
    private Rectangle2D screenBounds;
    private double startCoordX;
    private double startCoordY;
    private double imgStartCoordX;
    private double imgStartCoordY;
    protected boolean isGameFinished;
    // constructor
    public GameMapper(){
        originalHeightScale = new ArrayList<>();
        originalWidthScale = new ArrayList<>();
        initialPositionX = new ArrayList<>();
        initialPositionY = new ArrayList<>();
        screenBounds = Screen.getPrimary().getBounds();
        screenWidth = screenBounds.getWidth();
        screenHeight = screenBounds.getHeight() - 80;
        startCoordX = ( screenWidth - SQUARENUMBER * SQUARESIZE ) / 2;
        startCoordY = ( screenHeight - 750 ) / 2;
        setAward();
    }

    // methods
    // abstract methods
    abstract  ImageView getStickView();
    abstract  ArrayList<ImageView> getInitialImageList();
    abstract boolean isLevelFinished( int currentLevel );
    abstract void setInitialImageList();
    abstract void setStickView( int currentLevel );
    abstract void setGame( int currentLevel );
    abstract int getCurrentLevel();
    abstract void updateLevel();


    void setSquares(int width, int height){
        squares = new Square[width][height];
        for(int i = 0; i < squares.length; i++){
            double xCoor = i * SQUARESIZE + startCoordX;
            for(int j = 0; j < squares[i].length; j++){
                double yCoor = j * SQUARESIZE + startCoordY;
                squares[i][j] = new Square(xCoor, yCoor, SQUARESIZE,SQUARESIZE);
                if(i>=getCurrentLevel()  ){
                    squares[i][j].getRect().setStroke(Color.BURLYWOOD);
                    squares[i][j].getRect().setFill(Color.GRAY);
                    squares[i][j].getRect().setOpacity( 0.7 );
                }
                else {
                    squares[i][j].getRect().setStroke(Color.BURLYWOOD);
                    squares[i][j].getRect().setFill(Color.AQUA);
                    squares[i][j].getRect().setOpacity( 0.7 );
                }
            }
        }
    }

    public boolean isGameFinished(){
        return isGameFinished;
    }
    protected double getSQUARESIZE(){
        return SQUARESIZE;
    }

    protected double getBOARDCOORDX() {
        return startCoordX;
    }

    protected double getBOARDCOORDY() {
        return startCoordY;
    }

    public ImageView getAward()
    {
        return  awardView;
    }

    protected void setAward()
    {
        Image award = new Image(Paths.get("src/GameManagement/media/award.png").toUri().toString());
        awardView = new ImageView(award);
        awardView.setX(500);
        awardView.setY(200);
    }


    protected void setupEntity(ArrayList<ImageView> imageList)
    {
        initialPositionX.clear();
        initialPositionY.clear();
        imgStartCoordX = ( screenWidth - imageList.size() * 200) / 2 ;
        imgStartCoordY = (screenHeight - 100);

        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setX(imgStartCoordX + (i * 1.8 * SQUARESIZE));
            initialPositionX.add( imageList.get(i).getX() );
            imageList.get(i).setY( imgStartCoordY );
            initialPositionY.add( imageList.get(i).getY() );
            originalHeightScale.add( imageList.get(i).getFitHeight() );
            originalWidthScale.add( imageList.get(i).getFitWidth() );
            imageList.get(i).setFitHeight(100);
            imageList.get(i).setFitWidth(100);
            imageList.get(i).setPreserveRatio(true);
        }

    }

    public ArrayList<Double> getInitialPositionX() {
        return initialPositionX;
    }

    public ArrayList<Double> getInitialPositionY() {
        return initialPositionY;
    }

    public ArrayList<Double> getOriginalHeightScale()
    {
        return originalHeightScale;
    }

    public  ArrayList<Double> getOriginalWidthScale()
    {
        return  originalWidthScale;
    }


    public Square[][] getSquares()
    {
        return squares;
    }



    public Background getBackgroundImage()
    {
        String url =  "src/GameManagement/media/gameBoard.png";
        BackgroundImage backgroundImage= new BackgroundImage(new Image(Paths.get(url).toUri().toString(), screenWidth, screenHeight, true,true),
                BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background( backgroundImage );
        return background;
    }
}
