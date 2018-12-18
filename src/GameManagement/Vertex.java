/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author yusuf samsum
 */
public class Vertex {
    
    //properties
    private Square vertex;
    private ArrayList<Square> adjacencyList;
    private Direction[] directions; // R , D , L , U
    String savingLocation;
    Color color;

    //constructor
    public Vertex( Square mainSquare, Direction[] directions, Color color, String savingLocation )
    {
        vertex = mainSquare;
        this.directions = directions;
        this.color = color;
        mainSquare.getRect().setFill(color);
        createAdjacents();
        this.savingLocation = savingLocation;
        System.out.println( "Const: " + color );
    }
    
    
    private void createAdjacents()
    {
        System.out.println( "color: " + color);
        adjacencyList =  new ArrayList<Square>();
        for( int onePiece = 0; onePiece < directions.length; onePiece++ )
        {     
            
            double newX = vertex.getXCoodinate();
            double newY = vertex.getYCoordinate();
            double height = vertex.getHeight();
            Square piece = new Square( newX, newY, height, height);
            piece.getRect().setStroke( Color.AQUA);
            piece.getRect().setFill( color );
            int[] findDirection = directions[onePiece].getDistance();
            for( int pieceDirection = 0; pieceDirection < findDirection.length; pieceDirection++ )
            {
                int distance = findDirection[pieceDirection];
                if(distance > 0){
                    if( pieceDirection == 0 )
                        piece.setXCoordinate(newX + distance*height );
                    else if( pieceDirection == 1 )
                        piece.setYCoordinate(newY + distance*height );            
                    else if( pieceDirection == 2 )
                        piece.setXCoordinate(newX - distance*height );
                    else if( pieceDirection == 3 )
                        piece.setYCoordinate(newY - distance*height );
                }
            }
            adjacencyList.add(piece);
        }
    }
    
    
    public ArrayList<Square> getAdjacent()
    {
        return adjacencyList;
    }
    
    public Square getMain()
    {
        return vertex;
    }
    /*
    public void rotateLeft()
    {
        for( int i = 0; i < DIRECTIONLENGTH; i++ )
        {
            directions[i] = shiftLeft( directions[i] );
        }
        
        adjacencyList.clear();
        createAdjacents();
    }
    
    public void rotateRight()
    {
        for( int i = 0; i < DIRECTIONLENGTH; i++ )
        {
            directions[i] = shiftRight( directions[i] );
        }
        
        adjacencyList.clear();
        createAdjacents();
    }

    private int[] shiftLeft( int[] array )
    {
        int[] result = new int[ array.length ];
        for( int i = 0; i < array.length - 1; i++)
        {
            result[ i ] = array[ i + 1];
        }
        result[ array.length - 1 ] = array[ 0 ];
        
        return result;
    }

    public int[] shiftRight( int[] array )
    {
        int[] result = new int[ array.length ];
        result[0] = array[ array.length - 1 ];
        for( int i = 1; i < array.length; i++)
        {
            result[i] = array[ i - 1 ];
        }
        return result;
    }

    public  void updateLocation()
    {
        adjacencyList.clear();
        createAdjacents();
    }*/
}
