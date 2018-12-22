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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
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
import java.io.IOException;
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
    int oldNumber = 0;
    int oldTime = 0;
    Text tLab = new Text(10,20,"Time: ");
    Text timeLL = new Text(100,20," Hey");
    //static int i = 0;
    private long time;
    PlayerDatabase pD = new PlayerDatabase("yusuf123");
    ArrayList<ImageView> imageList;
    private DigitalClock clock;
    private boolean running = false;
    TimeKeeper t1 = new TimeKeeper();
    GameMapper gm = new NormalModeMapper("yusuf123");

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 1000);
        VBox vbox = new VBox();
        clock = new DigitalClock(true);
        //setBlocks();
        vbox.getChildren().addAll(clock);
       // System.out.println( "Stage im " + gm.getCurrentLevel() );
        imageList =  gm.getInitialImageList();
        System.out.println( "initial image list start method: " + gm.getInitialImageList().size());

        final ArrayList<Double> fitHeightList = gm.getOriginalHeightScale();
        final ArrayList<Double> fitWidthList = gm.getOriginalWidthScale();
        final ArrayList<Double> initialListX = gm.getInitialPositionX();
        final ArrayList<Double> initialListY = gm.getInitialPositionY();

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

        //System.out.println( "ImageSize: "  + imageList.size() );
        //System.out.println( "ImageSize: "  + imageList.size() );
        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setCursor(Cursor.HAND);
            System.out.println( "FitSize: " + fitHeightList.size() );
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
                        if( (t.getButton() == MouseButton.PRIMARY) && (t.getClickCount()%2==0 ) ) {
                            double orX = anImage.getX();
                            double orY = anImage.getY();
                            anImage.setFitWidth(fitWidth);
                            anImage.setFitHeight(fitHeight);

                            SnapshotParameters param = new SnapshotParameters();
                            param.setFill(Color.TRANSPARENT);
                            param.setTransform(new Rotate(90,anImage.getImage().getHeight(),anImage.getImage().getWidth()));
                            //anImage.setScaleY(-1);
                            anImage.setImage(anImage.snapshot(param,null));
                            anImage.setFitWidth(100);
                            anImage.setFitHeight(100);
                            //System.out.println("Rotation" + anImage.getX());
                            //System.out.println("Rotation" + anImage.getBoundsInParent());
                        }
                        else if(  t.getButton() == MouseButton.SECONDARY  )
                        {
                            double orX = anImage.getX();
                            double orY = anImage.getY();
                            anImage.setFitWidth(fitWidth);
                            anImage.setFitHeight(fitHeight);

                            SnapshotParameters param = new SnapshotParameters();
                            param.setFill(Color.TRANSPARENT);
                            //param.setTransform(new Rotate(90,anImage.getImage().getHeight(),anImage.getImage().getWidth()));
                            param.setTransform(new Scale( 1,-1));
                            anImage.setImage(anImage.snapshot(param,null));
                            anImage.setFitWidth(100);
                            anImage.setFitHeight(100);
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
                int count = 0;
                int count2 = 0;
                //boolean isEmpty = true;
                ImageView anImage = (ImageView)(t.getSource());

                double xLoc = originX , yLoc = originY;
                for( int k = 0; k < startLevel ; k++ ) {
                    for (int j = 0; j < boardSquares[k].length; j++) {
                        Circle c = new Circle();
                        c.setCenterY((gm.getBOARDCOORDY())+ 50 + (j) * 100);
                        c.setCenterX((gm.getBOARDCOORDX())+ 50 + (k) * 100);
                        c.setRadius(3);
                        c.setFill(Color.RED);
                        boardSquares[k][j].setFilled(false);
                        root.getChildren().add(c);

                        for( int noOfImage = 0; noOfImage < imageList.size(); noOfImage++ ) {
                            if (root.getChildren().get(root.getChildren().indexOf(imageList.get(noOfImage))).contains((gm.getBOARDCOORDX()+50) + (k) * 100, (gm.getBOARDCOORDY()+50) + (j) * 100)) {
                                c.setFill(Color.GREEN);
                                if(boardSquares[k][j].getStateOfSquare()!= true) {
                                    boardSquares[k][j].setFilled(true);
                                    count++;
                                }
                                else
                                    count2++;
                            }
                        }
                    }
                }

                System.out.println(gm.getSQUARESIZE());

                if( count != 0){
                    //System.out.println(count);
                    System.out.println("True");
                    System.out.println(anImage.getX());
                    System.out.println(anImage.getY());
                    //anImage.setPickOnBounds(true);
                    //System.out.println(anImage.getX());
                    boolean outside = false;

                    double xlocation=0,ylocation=0;
                    System.out.println("X (ilk) lokasyonu " + anImage.getImage().getWidth());
                    System.out.println("Y (ilk) lokasyonu " + anImage.getImage().getHeight());
                    System.out.println(gm.getCurrentLevel());
                    System.out.println(boardSquares[0].length);


                    System.out.println("X lokasyonu " + anImage.getX());
                    System.out.println("Y lokasyonu " + anImage.getY());
                    System.out.println("X lokasyonu " + (anImage.getX() + anImage.getImage().getWidth()));
                    System.out.println("Y lokasyonu " + (anImage.getY() + anImage.getImage().getHeight()));
                    for(double a = (anImage.getX()+ anImage.getImage().getWidth());a>anImage.getX();a= a - 10){
                        for(double b = (anImage.getY()+anImage.getImage().getHeight());b >anImage.getY();b= b - 10){
                            if(root.getChildren().get(root.getChildren().indexOf(anImage)).contains(a, b)){
                                xlocation = a;
                                ylocation = b;
                                //System.out.println(xlocation + " " );
                                //System.out.println(ylocation + " " );

                                if((xlocation > gm.getBOARDCOORDX()-25 && xlocation < (gm.getBOARDCOORDX()+25 + (gm.getCurrentLevel()) * 100 ) && (ylocation > gm.getBOARDCOORDY()-25 && ylocation < gm.getBOARDCOORDY()+25 + (boardSquares[0].length)*100 ))== false && !outside ) {
                                    outside = true;
                                    System.out.println(xlocation + " false " +  (300 + (gm.getCurrentLevel() * 100)));
                                    System.out.println(ylocation + " false " +  (150 + boardSquares[0].length * 100));
                                }
                            }

                        }
                    }
                    System.out.println(":)) " + outside);

                    System.out.println("X lokasyonu " + xlocation);
                    System.out.println("Y lokasyonu " + ylocation);

                    double maxheight=0;
                    double maxwidth=0;


                    for(double a = xlocation ; a <= anImage.getImage().getWidth() ; a++){
                        if(root.getChildren().get(root.getChildren().indexOf(anImage)).contains(a, ylocation))
                            maxwidth = a;
                    }

                    for(double a = ylocation ; a <= anImage.getImage().getHeight() ; a++){
                        if(root.getChildren().get(root.getChildren().indexOf(anImage)).contains(xlocation, a))
                            maxheight = a;
                    }
                    System.out.println("Max Height" + maxheight + " Max Width" + maxwidth);
                    System.out.println("Origin 1- "+ anImage.getX() + " 2- " + anImage.getY() );
                    System.out.println("loc 1- "+ xlocation + " 2- " + ylocation );
                    if(xlocation==gm.getBOARDCOORDX()-25)
                        xlocation++;
                    if(ylocation==gm.getBOARDCOORDY()-25)
                        ylocation++;
                    int x = ((int)xlocation - ((int)gm.getBOARDCOORDX()-25)) /100 ;
                    int y = ((int)ylocation - ((int)gm.getBOARDCOORDY()-25)) / 100;

                    System.out.println(" 1- "+ x + " 2- " + y );

                    if(maxwidth  == gm.getCurrentLevel())
                        x--;
                    if(maxheight  == boardSquares[0].length)
                        y--;

                    if( x>=0 && x < gm.getCurrentLevel()  && y >= 0 && y < boardSquares[0].length  && anImage.preserveRatioProperty().getValue() == false && count2==0 && !outside) {
                        if (boardSquares[x][y].getStateOfSquare()) {
                            System.out.println("Yok Artık");


                            double stickX = gm.getBOARDCOORDX()+x*100 ;
                            double stickY = gm.getBOARDCOORDY()+y*100 ;
                            anImage.setX(stickX + anImage.getX() - xlocation);
                            anImage.setY(stickY + anImage.getY() - ylocation);
                        }
                        else {
                            returnToInitialPlace(anImage);
                        }
                    }
                    else {
                        returnToInitialPlace(anImage);
                    }
                }
                else{
                    returnToInitialPlace(anImage);
                }

                if ( gm.isLevelFinished(startLevel)){
                   /* try {
                        t1.stopTimer(t1.getTime());
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    startLevel++;
                        this.start( primaryStage );
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
        //Thread clock = new Thread();
        //System.out.println(clock.start());
        root.getChildren().add(gm.getStickView());
        /*root.getChildren().add(10, tLab);
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
    }

    private void runClock() {
        running = true;
        new Thread(() -> {
            long last = System.nanoTime();
            double delta = 0;
            double ns = 1000000000.0 / 1;
            int count = oldTime;
            int number = oldNumber;
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
                    DecimalFormat df = new DecimalFormat("0000");
                    clock.refreshDigits(df.format(count));
                    clock.refreshMinute(number);
                    System.out.println( "Clock: " + number );
                    delta--;
                }
                oldTime = count;
                oldNumber = number;
                int lastPoint = (oldTime + (60 * oldNumber));
                pD.updatePlayerTime(pD.getLastNickname(), lastPoint);
            }
        }).start();
    }

    private void returnToInitialPlace( ImageView blockPiece )
    {
        blockPiece.setX(gm.getInitialPositionX().get(imageList.indexOf(blockPiece)));
        blockPiece.setY(gm.getInitialPositionY().get(imageList.indexOf(blockPiece)));
        blockPiece.setPreserveRatio(true);
        blockPiece.setFitWidth(100);
        blockPiece.setFitHeight(100);
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
