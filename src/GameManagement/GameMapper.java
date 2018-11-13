/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.util.ArrayList;
import javafx.scene.paint.Color;
/**
 *
 * @author burak korkmaz
 * @author yusuf samsum
 */
public class GameMapper {
    
    // properties
    private Square[][] squares;
    private int currentLevel;
    private Block[] blocks;
    private SolutionSet sols;
    
    // constructor
    public GameMapper(int currentLevel ){
        squares = null;
        pieces = null;
        this.currentLevel = currentLevel;
    }
    
    // methods 
    private void setSquares(int width, int height){
        squares = new Square[width][height];
        for(int i = 0; i < height; i++){
            int xCoor = i * 10;
            for(int j = 0; i < width; i++){
                int yCoor = j * 10;
                squares[i][j].setXCoordinate(xCoor);
                squares[i][j].setYCoordinate(yCoor);
                squares[i][j].setHeight(10);
                squares[i][j].setFilled(false);
            }
        }
    }
    
    public void runGame()
    {
        setSquares( 2 + currentLevel, 5 );
        
        setupEntity(currentLevel);
        
    }
    
    private void setupEntity( int currentLevel )
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
    }
    
    
}

