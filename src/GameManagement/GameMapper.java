/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.util.ArrayList;
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
    final int MAXLEVEL = 13;
    final int MINLEVEL = 1;
   // private Block[] blocks;
   // private SolutionSet sols;
    
    // constructor
    public GameMapper(int currentLevel ){
        squares = null;
     //   pieces = null;
        this.currentLevel = currentLevel + 2;
        setSquares( 13, 5 );
    }
    
    // methods 
    
    private void setSquares(int width, int height){
        squares = new Square[width][height];
        for(int i = 0; i < squares.length; i++){
            double xCoor = i * 100 + 200;
            for(int j = 0; j < squares[i].length; j++){
                double yCoor = j * 100 + 200;
                squares[i][j] = new Square(xCoor, yCoor, 100.0,100.0);
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
        
        runGame();
    }
    
    
    public void runGame()
    {
                  // setupEntity(currentLevel);
    }
    /*
    public Rectangle getLevelStick()
    {
        Rectangle stick = null;
        if( currentLevel > MINLEVEL - 1 && currentLevel < MAXLEVEL + 1 )
        {
            stick = new Rectangle( squares[currentLevel + 3][0].getXCoodinate(),squares[currentLevel + 3][0].getYCoordinate(), 100.0, 500.0  );
            for( int i = currentLevel + 3; i < MAXLEVEL + 1; i++  )
            {
                for( int k = 0; k < squares[i].length; k++ )
                    squares[i][k].getRect().setFill( Color.GRAY );
            }
        }
        return stick;
    }*/

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
    
   /* private void setupEntity( int currentLevel )
    {
        
    }
    
    private boolean updateLevel()
    {
        for( int i = 0; i < squares.length; i++ )
        {
            for( int k = 0; k < squares[i].length; k++ )
            {
                if( !squares[i][k].getStateOfSquare() )
                    return false;
            }
        }
        currentLevel++;
        runGame();
        return true;
    }*/
    
    
}
