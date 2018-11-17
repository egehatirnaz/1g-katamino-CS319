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
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
/*import jfxtras.labs.scene.control.window.Window;
import jfxtras.labs.scene.layout.ScalableContentPane;
import jfxtras.labs.util.event.MouseControlUtil;*/

/**
 *
 * @author User
 */
public class VertexTest extends Application {
    
    double orgSceneX, orgSceneY;
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
        double mainX, mainY = 300.0;
        double height = 100.0;
        Square main = new Square( 300, 300, 100, 100 );
        
        int directions[][] = {{1,0,0,0}, {1,1,0,0} , {1,0,0,1}, {0,0,1,0} };
        
        Vertex v = new Vertex( main, directions );
        
        v.rotateRight();
        
        ArrayList<Square> adj = v.getAdjacent();
        
        root.getChildren().add( v.getMain().getRect() );
     
           //System.out.println( "Main X: " + v.getMain().getXCoodinate() );
           //System.out.println( "Main Y: " + v.getMain().getXCoodinate() );
        
        for( int i = 0; i < adj.size(); i++)
        {
            
            root.getChildren().add( adj.get(i).getRect() );
            //System.out.println( "X: " + adj.get(i).getXCoodinate() );
            //System.out.println( "Y: " +  adj.get(i).getYCoordinate() );
        }
        
        v.getMain().getRect().setCursor(Cursor.HAND);
        
        v.getMain().getRect().setOnMouseDragged((t) ->{
           // double offsetX = t.getSceneX() - v.getMain().getRect().getX();
           // double offsetY = t.getSceneY() - v.getMain().getRect().getY();
            //System.out.println();
            v.getMain().setXCoordinate( t.getSceneX() );
            v.getMain().setYCoordinate( t.getSceneY() );
            /* for( int i = 0; i < adj.size(); i++)
            {
            
            adj.get(i).setXCoordinate(t.getSceneX());
            adj.get(i).setYCoordinate(t.getSceneY());
            //System.out.println( "X: " + adj.get(i).getXCoodinate() );
            //System.out.println( "Y: " +  adj.get(i).getYCoordinate() );
            }*/
            
             
           // root.requestLayout();
            
        });
       // MouseControlUtil.makeDraggable(v);
        /*
        int ex[] = { 1 ,2 , 3, 4, 5 };
        
        ex = v.shiftRight(ex);
        
        for( int i = 0; i < 5; i++)
            System.out.println( ex[i] );
        
         */
        
        
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
