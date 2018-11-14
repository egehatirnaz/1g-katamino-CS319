/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import java.lang.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.lang.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.embed.swing.JFXPanel;
import java.nio.file.Paths;
import static javafx.application.Application.launch;
import javafx.scene.paint.Color;
import javax.swing.JFrame;
/**
 *
 * @author firatyildiz
 */
public class SolutionSet {
    
    private List<Block> original = new ArrayList<>();
    
    public SolutionSet(){
        original.add(new Block(Color.GREEN,
                new Piece(0, Direction.DOWN),
                new Piece(1, Direction.RIGHT),
                new Piece(2, Direction.RIGHT),
                new Piece(1, Direction.RIGHT, Direction.DOWN),
                new Piece(1, Direction.RIGHT, Direction.DOWN, Direction.DOWN)));

        original.add(new Block(Color.GRAY,
                new Piece(0, Direction.DOWN),
                new Piece(1, Direction.RIGHT),
                new Piece(2, Direction.RIGHT),
                new Piece(3, Direction.RIGHT),
                new Piece(1, Direction.RIGHT,Direction.RIGHT,Direction.DOWN )));
        
        original.add(new Block(Color.RED,
                new Piece(0, Direction.DOWN),
                new Piece(1, Direction.RIGHT),
                new Piece(2, Direction.RIGHT),
                new Piece(3, Direction.RIGHT),
                new Piece(1, Direction.DOWN)));
    } 
    
    public Parent draw(){
        Pane root = new Pane();
        root.setPrefSize(60 * 20, 80 * 20);
        GraphicsContext g;
        int a = 0;
        Canvas canvas = new Canvas(60 * 20, 80 * 20);
        g = canvas.getGraphicsContext2D();
        for(int i = 0; i < 3; i++){
           original.get(i).draw(g);
           //original.get(i).
           for(int j = 0; j < 5*(i+1); j++){
               if(i != 2){
                   a++;
                   original.get(i + 1).move(Direction.DOWN);               }
           }
        }
        System.out.println(a);
        root.getChildren().addAll(canvas);
        return root;
        }
}
