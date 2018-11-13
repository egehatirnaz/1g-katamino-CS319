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
    private Square[][] squares;
    private void setSquares(int width, int height){
        for(int i = 0; i < height; i++){
            int xCoor = i * 10;
            for(int j = 0; i < width; i++){
                int yCoor = j * 10;
                squares[i][j] = new Square(xCoor, yCoor, xCoor + 10, yCoor + 10);
                squares[i][j].setFilled(false);
            }
        }
    }

    GameMapper(){
        setSquares(13, 5);
    }
}
