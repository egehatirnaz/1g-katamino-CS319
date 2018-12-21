/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
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
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author mert epsileli - burak korkmaz - yusuf samsum - frtyldz
 */
public class BoardTest extends Application {

    private Scene window;
    double originX, originY;
    int startLevel = 4;

    //Text tLab = new Text(10,20,"Time: ");
    //Text timeLL = new Text(100,20," Hey");
    //static int i = 0;
    //private long time;

    private VBox vbox = new VBox();
    private DigitalClock clock = new DigitalClock();
    private boolean running = false;
    TimeKeeper t1 = new TimeKeeper();

    @Override
    public void start(Stage primaryStage) {
        //setBlocks();
        vbox.getChildren().addAll(clock);
        /*tLab.setFont(Font.font ("Verdana", 20));
        tLab.setFill(Color.RED);
        timeLL.setFont(Font.font ("Verdana", 20));*/
        Group root = new Group();
        GameMapper gm = new NormalModeMapper();
       // System.out.println( "Stage im " + gm.getCurrentLevel() );
        ArrayList<ImageView> imageList =  gm.getInitialImageList();
        System.out.println( "initial image list start method: " + gm.getInitialImageList().size() );

        final ArrayList<Double> fitHeightList = gm.getOriginalHeightScale();
        final ArrayList<Double> fitWidthList = gm.getOriginalWidthScale();

        Square[][] boardSquares = gm.getSquares();

        for( int i = 0; i < boardSquares.length; i++ )
        {
            for( int k = 0; k < boardSquares[i].length; k++ ){
                root.getChildren().add( boardSquares[i][k].getRect());
                // System.out.println( boardSquares[i][k].getXCoodinate() + " y: " +  boardSquares[i][k].getYCoordinate() );

                // System.out.println(root.getChildren().get(i).computeAreaInScreen());
            }
        }
        root.getChildren().add(vbox);
        Scene scene = new Scene(root, 1000, 1000);

        //System.out.println( "ImageSize: "  + imageList.size() );
        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setCursor(Cursor.HAND);
           // System.out.println( "FitSize: " + fitHeightList.size() );
            double fitHeight = fitHeightList.get(i);
            double fitWidth = fitWidthList.get(i);
            imageList.get(i).setOnMousePressed( (t) -> {
                        originX = t.getSceneX();
                        originY = t.getSceneY();


                        // Image test = new Image(Paths.get("src/GameManagement/media/1.png").toUri().toString());
                        ImageView anImage = (ImageView)(t.getSource());

                        //System.out.println( "x1 : "  + anImage.getX() );
                        //System.out.println( "y1 : "  + anImage.getY() );
                        anImage.toFront();
                        // rotate logic
                        if(t.getClickCount()%2==0) {
                            anImage.setFitWidth(fitWidth);
                            anImage.setFitHeight(fitHeight);
                            SnapshotParameters param = new SnapshotParameters();
                            param.setFill(Color.TRANSPARENT);
                            param.setTransform(new Rotate(90,anImage.getImage().getHeight()/2,anImage.getImage().getWidth()/2));
                            anImage.setImage(anImage.snapshot(param,null));
                            anImage.setFitWidth(100);
                            anImage.setFitHeight(100);
                            //System.out.println("Rotation" + anImage.getX());
                            //System.out.println("Rotation" + anImage.getBoundsInParent());
                        }
                    }
            );

            imageList.get(i).setOnMouseDragged(  (t) ->
                    {
                        ImageView anImage = (ImageView)(t.getSource());

                        anImage.setFitWidth(fitWidth);
                        anImage.setFitHeight(fitHeight);

                        double offsetX = t.getSceneX() - originX;
                        double offsetY = t.getSceneY() - originY;

                        //System.out.println( "x2 : "  + anImage.getX() );
                        //System.out.println( "y2 : "  + anImage.getY() );

                        anImage.setX( offsetX + anImage.getX() );
                        anImage.setY( offsetY + anImage.getY() );

                        anImage.setPreserveRatio(false);

                        originX  = t.getSceneX();
                        originY = t.getSceneY();
                    }

            );
            imageList.get(i).setOnMouseReleased((t)-> {
                for( int k = 0; k < gm.getCurrentLevel() + 2; k++ ) {
                    for (int j = 0; j < boardSquares[k].length; j++) {
                        Circle c = new Circle();
                        c.setCenterY((250) + (j) * 100);
                        c.setCenterX((250) + (k) * 100);
                        c.setRadius(3);
                        c.setFill(Color.RED);
                        boardSquares[k][j].setFilled(false);
                        root.getChildren().add(c);
                        for( int noOfImage = 0; noOfImage < imageList.size(); noOfImage++ ) {
                            if (root.getChildren().get(root.getChildren().indexOf(imageList.get(noOfImage))).contains((300) + (k) * 100, (150) + (j) * 100)) {
                                c.setFill(Color.GREEN);
                                boardSquares[k][j].setFilled(true);
                            }
                        }
                    }
                }
                if ( gm.isLevelFinished(startLevel)){
                    try {
                        t1.stopTimer(t1.getTime());
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startLevel++;
                    start( primaryStage );
                }
                /*if(){
                    System.out.println("Game is over");
                    for( int noOfBlock = 0; noOfBlock < imageList.size(); noOfBlock++)
                        imageList.get(noOfBlock).setDisable( true );
                }*/
            });
        }

        for( int i = 0; i < imageList.size(); i++)
            root.getChildren().add( imageList.get(i));

        //t1.startTimer(00);
        //t1.run(timeLL);
        //t1.stopTimer(t1.getTime());
        /*Thread clock = new Thread();
        System.out.println(clock.start());
        root.getChildren().add(gm.getStickView());
        root.getChildren().add(10, tLab);
        root.getChildren().add(10, timeLL);*/
        primaryStage.setTitle("Katamino");
        primaryStage.setScene(scene);
        primaryStage.show();
        window = scene;
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent arg0) {
                running = false;
                primaryStage.close();
            }

        });
        runClock();
        //t1.run(timeLL);
    }

    private void runClock() {
        running = true;
        new Thread(() -> {
            long last = System.nanoTime();
            double delta = 0;
            double ns = 1000000000.0 / 1;
            int count = 0;
            int number = 0;
            boolean check = false;

            while (running) {
                long now = System.nanoTime();
                delta += (now - last) / ns;
                last = now;

                while (delta >= 1) {
                    count = (count + 1) % 60;
                    if((count % 60 == 0) && (check)){
                        number ++;
                    }
                    check = true;
                    DecimalFormat df = new DecimalFormat("000");
                    clock.refreshDigits(df.format(count));
                    clock.refreshMinute(number);
                    delta--;
                }
            }
        }).start();
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
