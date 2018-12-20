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
        this.currentLevel = 1;
        originalHeightScale = new ArrayList<>();
        originalWidthScale = new ArrayList<>();
        setAward();
    }
    
    // methods

    abstract void setGame( int currentLevel );

    void setSquares(int width, int height){
        System.out.println( "setSquares cur level : "  + currentLevel );
        currentLevel += 2;
        squares = new Square[width][height];
        for(int i = 0; i < squares.length; i++){
            double xCoor = i * SQUARESIZE + BOARDCOORDX;
            for(int j = 0; j < squares[i].length; j++){
                double yCoor = j * SQUARESIZE + BOARDCOORDY;
                squares[i][j] = new Square(xCoor, yCoor, SQUARESIZE,SQUARESIZE);
                if(i>=currentLevel  ){
                    squares[i][j].getRect().setStroke(Color.BURLYWOOD);
                    squares[i][j].getRect().setFill(Color.GRAY);
                }
                else {
                    squares[i][j].getRect().setStroke(Color.BURLYWOOD);
                    squares[i][j].getRect().setFill(Color.AQUA);
                }
            }
        }
    }

    public double getSQUARESIZE(){
        return SQUARESIZE;
    }

    public double getBOARDCOORDX() {
        return BOARDCOORDX;
    }

    public double getBOARDCOORDY() {
        return BOARDCOORDY;
    }

    abstract  ImageView getStickView();
    abstract  ArrayList<ImageView> getInitialImageList();
    abstract boolean isLevelFinished( int currentLevel );
    abstract void setInitialImageList();
    abstract void setStickView( int currentLevel );

    public ImageView getAward()
    {
        return  awardView;
    }

    public void setAward()
    {
        Image award = new Image(Paths.get("src/GameManagement/media/award.png").toUri().toString());
        awardView = new ImageView(award);
        awardView.setX(500);
        awardView.setY(200);
    }


    void setupEntity(ArrayList<ImageView> imageList)
    {
        System.out.println( "Size: " + imageList.size() );
        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setX(BOARDCOORDX + i * 3 * SQUARESIZE);
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
