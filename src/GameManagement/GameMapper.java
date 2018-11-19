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
public class GameMapper {
    
    // properties
    private Square[][] squares;
    private int currentLevel;
    private ArrayList<ImageView> list;
    private ArrayList<ImageView> originalSizelist;
    private  final int MAXLEVEL = 10;
    private final int MINLEVEL = 1;
    private final int BOARDWIDTH = 13;
    private final int BOARDHEIGHT = 5;
    private final double SQUARESIZE = 100.0;
    private final double BOARDCOORDX = 250.0;
    private final double BOARDCOORDY = 100.0;
    private final double STICKCOORDX = 635.0;
    private final double STICKCOORDY = 30.0;

    // constructor
    public GameMapper(int currentLevel ){
        squares = null;
        this.currentLevel = currentLevel + 2;
        originalSizelist = new ArrayList<>();
        list = new ArrayList<>();
        setSquares(BOARDWIDTH,BOARDHEIGHT);
    }
    
    // methods 
    
    private void setSquares(int width, int height){
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
    
    /*
    private void runGame( int currentLevel )
    {
        setSquares( BOARDWIDTH, BOARDHEIGHT );
    }*/

    public ImageView getStickView( int currentLevel )
    {
        Image stick = new Image(Paths.get("src/GameManagement/media/Stick.png").toUri().toString());

        ImageView stickView = new ImageView(stick);

        stickView.setX(STICKCOORDX + ( (currentLevel - 1) * SQUARESIZE ) );
        stickView.setY(STICKCOORDY);
        return  stickView;
    }

    public ImageView getAward()
    {
        Image award = new Image(Paths.get("src/GameManagement/media/award.png").toUri().toString());
        ImageView awardView = new ImageView(award);
        awardView.setX(500);
        awardView.setY(200);
        return  awardView;
    }

    public ArrayList<ImageView> getInitialImageList()
    {
        Image green = new Image(Paths.get("src/GameManagement/media/green.png").toUri().toString());
        Image blue = new Image( Paths.get( "src/GameManagement/media/blue.png" ).toUri().toString() );
        Image yellow = new Image( Paths.get( "src/GameManagement/media/yellow.png" ).toUri().toString() );
        ImageView img1 = new ImageView(green);
        ImageView img2 = new ImageView(blue);
        ImageView img3 = new ImageView(yellow);
        list.add(img1);
        list.add(img2);
        list.add(img3);
        originalSizelist.add(img1);
        originalSizelist.add(img2);
        originalSizelist.add(img3);
        setupEntity();
        return  list;
    }

    public Image getOriginalSizeImage( int index )
    {
        return originalSizelist.get(index).getImage();
    }


    private void setupEntity()
    {
        for( int i = 0; i < list.size(); i++ )
        {
            list.get(i).setX(BOARDCOORDX + i * 3 * SQUARESIZE);
            list.get(i).setY( 5 * SQUARESIZE + BOARDCOORDY + 100 );
            list.get(i).setFitHeight(100);
            list.get(i).setFitWidth(100);
            list.get(i).setPreserveRatio(true);
        }

    }

    public boolean isLevelFinished(){
        for(int i = 0; i < currentLevel; i++){
            for(int j = 0; j < squares[i].length; j++) {
                if(squares[i][j].getStateOfSquare()==false)
                    return false;
            }
        }
        return true;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public Square[][] getSquares()
    {
        return squares;
    }
    

    
    public void updateLevel() {
        if (isLevelFinished()) {
            currentLevel++;
            setSquares(BOARDWIDTH,BOARDHEIGHT);
        }
    }
    
    
}
