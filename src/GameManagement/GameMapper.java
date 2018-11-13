/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.util.*;
/**
 *
 * @author firatyildiz
 */
public class GameMapper {
    
    // properties
    private Square[][] squares;
    
    // constructor
    public GameMapper(int width, int height){
        squares = new Square[width][height];
        setSquares(width,height);
    }
    
    // methods 
    private void setSquares(int width, int height){
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
    
    

}
