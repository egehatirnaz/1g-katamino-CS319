/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class VertexTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        
        Pane root = new Pane();
        
        Square main = new Square( 300, 300, 100, 100 );
        
        int directions[][] = {{1,0,0,0}, {2,0,0,0} , {3,0,0,0}, {1,1,0,0} };
        
        Vertex v = new Vertex( main, directions );
        
        v.rotateRight();
        
        ArrayList<Square> adj = v.getAdjacent();
        
        root.getChildren().add( v.getMain().getRect() );
     
           System.out.println( "Main X: " + v.getMain().getXCoodinate() );
           System.out.println( "Main Y: " + v.getMain().getXCoodinate() );
        
        for( int i = 0; i < adj.size(); i++)
        {
            
            root.getChildren().add( adj.get(i).getRect() );
            System.out.println( "X: " + adj.get(i).getXCoodinate() );
            System.out.println( "Y: " +  adj.get(i).getYCoordinate() );
        }
        
        int ex[] = { 1 ,2 , 3, 4, 5 };
        
        ex = v.shiftRight(ex);
        
        for( int i = 0; i < 5; i++)
            System.out.println( ex[i] );
        
         
        Scene scene = new Scene(root, 1000, 1000);
        
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
