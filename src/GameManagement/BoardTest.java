/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jfxtras.labs.util.event.MouseControlUtil;

import javax.swing.*;
import java.nio.file.Paths;

/**
 *
 * @author User
 */
public class BoardTest extends Application {
    
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
        
        GameMapper gm = new GameMapper( 5 );
        Group root = new Group();
        
        Square[][] boardSquares = gm.getSquares();
        for( int i = 0; i < boardSquares.length; i++ )
        {
            for( int k = 0; k < boardSquares[i].length; k++ ){
                root.getChildren().add( boardSquares[i][k].getRect());
                System.out.println( boardSquares[i][k].getXCoodinate() + " y: " +  boardSquares[i][k].getYCoordinate() );
            }              
        }
        /*
        if( gm.getLevelStick() != null );
            root.getChildren().add( gm.getLevelStick() );

*/
        /*GridPane gp = new GridPane();
        gp.setPadding(new Insets(5));
        gp.setHgap(10);
        gp.setVgap(10);*/
        Image one = new Image(Paths.get("src/GameManagement/media/green.png").toUri().toString());

        ImageView img1 = new ImageView(one);
        EventHandler<MouseEvent> eh = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().remove(img1);
                img1.setRotate(90);
                root.getChildren().add(img1);
                System.out.println("girdim");
            }
        };
        JPanel P = new JPanel();
        Scene scene = new Scene(root, 1000, 1000);

        img1.setX(150);
        img1.setX(200);
        img1.addEventHandler(MouseEvent.MOUSE_PRESSED, eh);
        Image two = new Image(Paths.get("src/GameManagement/media/blue.png").toUri().toString());
        ImageView img2 = new ImageView(two);
        Image three = new Image(Paths.get("src/GameManagement/media/yellow.png").toUri().toString());
        ImageView img3 = new ImageView(three);
        img1.setRotate(90);
        //JLabel picLabel = new JLabel(new ImageIcon(img1));
        //HBox pictures = new HBox();
        //img2.setRotate(180);
        //img3.setRotate(180);

        MouseControlUtil.makeDraggable(img1);
        MouseControlUtil.makeDraggable(img2);
        MouseControlUtil.makeDraggable(img3);
        root.getChildren().add(img1);
        root.getChildren().add(img2);
        root.getChildren().add(img3);
        //gp.add(pictures,1,1);


        //root.getChildren().add(gp);


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
