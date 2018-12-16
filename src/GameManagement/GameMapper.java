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
    private ArrayList<Double>       initialHeightScale;
    private ArrayList<Double>       initialWidthScale;
    private final double SQUARESIZE = 100.0;
    private final double BOARDCOORDX = 250.0;
    private final double BOARDCOORDY = 100.0;
    // constructor
    public GameMapper(){
        this.currentLevel = 1;
        originalHeightScale = new ArrayList<>();
        originalWidthScale = new ArrayList<>();
    }
    
    // methods

    abstract void setGame( int currentLevel );

    void setSquares(int width, int height, int currentLevel){
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


    abstract  ImageView getStickView( int currentLevel );
    abstract  ArrayList<ImageView> getInitialImageList();
    abstract boolean isLevelFinished( int currentLevel );
    abstract void setInitialImageList();


    public ImageView getAward()
    {
        Image award = new Image(Paths.get("src/GameManagement/media/award.png").toUri().toString());
        ImageView awardView = new ImageView(award);
        awardView.setX(500);
        awardView.setY(200);
        return  awardView;
    }


    void setupEntity(ArrayList<ImageView> imageList)
    {
        System.out.println( "Size: " + imageList.size() );
        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setX(BOARDCOORDX + i * 3 * SQUARESIZE);
            imageList.get(i).setY( 5 * SQUARESIZE + BOARDCOORDY + 100 );
            originalHeightScale.add( imageList.get(i).getFitHeight() );
            originalWidthScale.add( imageList.get(i).getFitWidth() );
            imageList.get(i).setFitHeight(100);
            imageList.get(i).setFitWidth(100);
            imageList.get(i).setPreserveRatio(true);
        }

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
        if (isLevelFinished(currentLevel)) {
            currentLevel++;
            setGame(currentLevel);
        }
    }
    
    
}
