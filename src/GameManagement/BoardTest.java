/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author mert epsileli - burak korkmaz - yusuf samsum
 */
public class BoardTest extends Application {

    private Scene window;
    double originX, originY;
    @Override
    public void start(Stage primaryStage) {
        int currentLevel = 1;
        GameMapper gm = new GameMapper( currentLevel );
        Group root = new Group();

        /*Timeline time = new Timeline();
        Text timer = new Text();
        timer.setText(time.toString());
        timer.setX(100);
        timer.setY(100);
        root.getChildren().add(timer);*/


        Square[][] boardSquares = gm.getSquares();
        for( int i = 0; i < boardSquares.length; i++ )
        {
            for( int k = 0; k < boardSquares[i].length; k++ ){
                root.getChildren().add( boardSquares[i][k].getRect());
                System.out.println( boardSquares[i][k].getXCoodinate() + " y: " +  boardSquares[i][k].getYCoordinate() );

                // System.out.println(root.getChildren().get(i).computeAreaInScreen());
            }
        }


        Scene scene = new Scene(root, 1000, 1000);


        ArrayList<ImageView> imageList =  gm.getInitialImageList();
        ArrayList<Double> fitHeightList = gm.getOriginalHeightScale();
        ArrayList<Double> fitWidthList = gm.getOriginalWidthScale();

        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setCursor(Cursor.HAND);

            double fitHeight = fitHeightList.get(i);
            double fitWidth = fitWidthList.get(i);
            imageList.get(i).setOnMousePressed( (t) -> {
                        originX = t.getSceneX();
                        originY = t.getSceneY();

                        // Image test = new Image(Paths.get("src/GameManagement/media/1.png").toUri().toString());
                        ImageView anImage = (ImageView)(t.getSource());
                        anImage.setFitWidth(fitWidth);
                        anImage.setFitHeight(fitHeight);
                        anImage.setPreserveRatio(false);
                        // rotate logic
                        if(t.getClickCount()%2==0) {
                            SnapshotParameters param = new SnapshotParameters();
                            param.setFill(Color.TRANSPARENT);
                            param.setTransform(new Rotate(90,anImage.getImage().getHeight()/2,anImage.getImage().getWidth()/2));
                            anImage.setImage(anImage.snapshot(param,null));

                            //System.out.println("Rotation" + anImage.getX());
                            //System.out.println("Rotation" + anImage.getBoundsInParent());
                        }
                    }
            );

            imageList.get(i).setOnMouseDragged(  (t) ->
                    {
                        double offsetX = t.getSceneX() - originX;
                        double offsetY = t.getSceneY() - originY;

                        ImageView anImage = (ImageView)(t.getSource());
                        anImage.setX( offsetX + anImage.getX() );
                        anImage.setY( offsetY + anImage.getY() );


                        originX  = t.getSceneX();
                        originY = t.getSceneY();
                    }

            );
            imageList.get(i).setOnMouseReleased((t)-> {
                for( int k = 0; k < gm.getCurrentLevel(); k++ ) {
                    for (int j = 0; j < boardSquares[k].length; j++) {
                        /*Circle c = new Circle();
                        c.setCenterY((250) + (j) * 100);
                        c.setCenterX((250) + (k) * 100);
                        c.setRadius(3);
                        c.setFill(Color.RED);*/
                        boardSquares[k][j].setFilled(false);
                        //root.getChildren().add(c);
                        for( int noOfImage = 0; noOfImage < imageList.size(); noOfImage++ ) {
                            if (root.getChildren().get(root.getChildren().indexOf(imageList.get(noOfImage))).contains((300) + (k) * 100, (150) + (j) * 100)) {
                                //c.setFill(Color.GREEN);
                                boardSquares[k][j].setFilled(true);
                            }
                        }
                    }
                }
                if(gm.isLevelFinished()){
                    System.out.println("Game is over");
                    ImageView awardView = gm.getAward();
                    root.getChildren().add(awardView);
                    for( int noOfBlock = 0; noOfBlock < imageList.size(); noOfBlock++)
                        imageList.get(noOfBlock).setDisable( true );
                    gm.updateLevel();
                }
            });
        }

        for( int i = 0; i < imageList.size(); i++)
            root.getChildren().add( imageList.get(i));

        root.getChildren().add(gm.getStickView( currentLevel ));

        primaryStage.setTitle("Katamino");
        primaryStage.setScene(scene);
        primaryStage.show();
        window = scene;
    }

    public Scene returnScene() {
        return window;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
