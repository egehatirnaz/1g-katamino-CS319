/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import jfxtras.labs.scene.control.window.Window;
import jfxtras.labs.scene.layout.ScalableContentPane;
import jfxtras.labs.util.event.MouseControlUtil;

/**
 *
 * @author User
 */
public class VertexTest extends Application {

    double translationX = 0;
    double translationY = 0;

    
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

        // v.rotateRight();
        
        ArrayList<Square> adj = v.getAdjacent();


        Image one = new Image(Paths.get("src/GameManagement/media/green.png").toUri().toString());
        ImageView img1 = new ImageView(one);
        Image two = new Image(Paths.get("src/GameManagement/media/blue.png").toUri().toString());
        ImageView img2 = new ImageView(two);
        Image three = new Image(Paths.get("src/GameManagement/media/yellow.png").toUri().toString());
        ImageView img3 = new ImageView(three);
        img1.setRotate(90);
        //img2.setRotate(180);
        //img3.setRotate(180);

        MouseControlUtil.makeDraggable(img1);
        MouseControlUtil.makeDraggable(img2);
        MouseControlUtil.makeDraggable(img3);
        root.getChildren().add(img1);
        root.getChildren().add(img2);
        root.getChildren().add(img3);


        int ex[] = { 1 ,2 , 3, 4, 5 };
        
        ex = v.shiftRight(ex);
        
        for( int i = 0; i < 5; i++)
            System.out.println( ex[i] );
        
         
        Scene scene = new Scene(root, 1000, 1000);
        
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
