/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author burak korkmaz
 * @author yusuf samsum
 */
public abstract class GameMapper {
    
    // properties
    private Square[][] squares;
    private int currentLevel;
    private ArrayList<Double>       originalHeightScale;
    private ArrayList<Double>       originalWidthScale;
    private ArrayList<Double>       initialPositionX;
    private ArrayList<Double>       initialPositionY;
    private final double SQUARESIZE = 100.0;
    private final double BOARDCOORDX = 250.0;
    private final double BOARDCOORDY = 100.0;
    private ImageView awardView;
    // constructor
    public GameMapper(){
        this.currentLevel = 3;
        originalHeightScale = new ArrayList<>();
        originalWidthScale = new ArrayList<>();
        initialPositionX = new ArrayList<>();
        initialPositionY = new ArrayList<>();
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


    void setSquares(int width, int height){
        squares = new Square[width][height];
        for(int i = 0; i < squares.length; i++){
            double xCoor = i * SQUARESIZE + BOARDCOORDX;
            for(int j = 0; j < squares[i].length; j++){
                double yCoor = j * SQUARESIZE + BOARDCOORDY;
                squares[i][j] = new Square(xCoor, yCoor, SQUARESIZE,SQUARESIZE);
                if(i>=currentLevel  ){
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

    protected double getSQUARESIZE(){
        return SQUARESIZE;
    }

    protected double getBOARDCOORDX() {
        return BOARDCOORDX;
    }

    protected double getBOARDCOORDY() {
        return BOARDCOORDY;
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
        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setX(BOARDCOORDX + i * 2 * SQUARESIZE);
            initialPositionX.add( imageList.get(i).getX() );
            imageList.get(i).setY( 5 * SQUARESIZE + BOARDCOORDY + 100 );
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


    public int getCurrentLevel(){
        return currentLevel;
    }

    public Square[][] getSquares()
    {
        return squares;
    }
    

    public void updateLevel() {
        currentLevel++;
        setGame(currentLevel);
    }
    
    
}
