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

    final int startx= 200;
    final int starty= 190;
    double originX, originY;
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

        GameMapper gm = new GameMapper( 1 );
        Group root = new Group();

        /*Timeline time = new Timeline();
        Text timer = new Text();
        timer.setText(time.toString());
        timer.setX(100);
        timer.setY(100);
        root.getChildren().add(timer);*/

        int start;



        Square[][] boardSquares = gm.getSquares();
        for( int i = 0; i < boardSquares.length; i++ )
        {
            for( int k = 0; k < boardSquares[i].length; k++ ){
                root.getChildren().add( boardSquares[i][k].getRect());
                System.out.println( boardSquares[i][k].getXCoodinate() + " y: " +  boardSquares[i][k].getYCoordinate() );

                System.out.println(root.getChildren().get(i).computeAreaInScreen());
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

        Image green = new Image(Paths.get("src/GameManagement/media/green.png").toUri().toString());
        Image blue = new Image( Paths.get( "src/GameManagement/media/blue.png" ).toUri().toString() );
        Image yellow = new Image( Paths.get( "src/GameManagement/media/yellow.png" ).toUri().toString() );
        Image stick = new Image(Paths.get("src/GameManagement/media/Stick.png").toUri().toString());
        Image award = new Image(Paths.get("src/GameManagement/media/award.png").toUri().toString());
        //JPanel P = new JPanel();
        Scene scene = new Scene(root, 1000, 1000);
        ImageView img1 = new ImageView(green);
        ImageView img2 = new ImageView(blue);
        ImageView img3 = new ImageView(yellow);
        ImageView stickView = new ImageView(stick);
        ImageView awardView = new ImageView(award);
        stickView.setX(485);
        stickView.setY(130);

        ArrayList<ImageView> imageList =  new ArrayList<ImageView>();

        imageList.add( img1 );
        imageList.add( img2 );
        imageList.add( img3 );

        for( int i = 0; i < imageList.size(); i++ )
        {
            imageList.get(i).setCursor(Cursor.HAND);

            imageList.get(i).setOnMousePressed( (t) -> {
                        originX = t.getSceneX();
                        originY = t.getSceneY();

                        ImageView anImage = (ImageView)(t.getSource());
                        anImage.toFront();
                        double angle = anImage.getRotate();

                        if(t.getClickCount()%2==0) {
                            //anImage.setRotate(40);
                            //System.out.println(anImage.getRotate());
                            SnapshotParameters param = new SnapshotParameters();
                            param.setFill(Color.TRANSPARENT);
                            param.setTransform(new Rotate(90,anImage.getImage().getHeight()/2,anImage.getImage().getWidth()/2));
                            anImage.setImage(anImage.snapshot(param,null));

                            //anImage.setRotate(anImage.getRotate()+90);
                            // anImage.getTransforms().add(new Rotate(90,anImage.getX(),anImage.getY()));

                            System.out.println("Rotation" + anImage.getX());
                            System.out.println("Rotation" + anImage.getBoundsInParent());
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
                        Circle c = new Circle();
                        c.setCenterY((250) + (j) * 100);
                        c.setCenterX((250) + (k) * 100);
                        c.setRadius(3);
                        //c.setFill(Color.RED);
                        boardSquares[k][j].setFilled(false);
                        //root.getChildren().add(c);
                        for( int noOfImage = 0; noOfImage < imageList.size(); noOfImage++ ) {
                            if (root.getChildren().get(root.getChildren().indexOf(imageList.get(noOfImage))).contains((250) + (k) * 100, (250) + (j) * 100)) {
                                //c.setFill(Color.GREEN);
                                boardSquares[k][j].setFilled(true);
                            }
                        }
                    }
                }
                if(gm.isLevelFinished()){
                    System.out.println("Malatya");
                    //awardView.setX(;
                    awardView.setX(500);
                    awardView.setY(200);
                    root.getChildren().add(awardView);
                    img1.setDisable(true);
                    img2.setDisable(true);
                    img3.setDisable(true);
                }
            });
        }





        /*EventHandler<MouseEvent> eventH = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView img;
                img = (ImageView) event.getSource() ;
                if(event.getClickCount() % 2 == 0) {
                    System.out.println( "Rotated!!" );
                    img.setRotate(img.getRotate() + 90);
                }


                Point p = MouseInfo.getPointerInfo().getLocation();
                img.setX(event.getSceneX());
                img.setY(event.getSceneY());
                //if(img.getBoundsInParent().contains(boardSquares[1][1]))
                System.out.println(img.getX());
                //img.getBoundsInParent().;
                //System.out.println(img.getX());
                //System.out.println(p.getX());
                //System.out.println(p.getY());
                System.out.println("İşte bu amk " + event.getSceneX());
                //event.getSceneX();
                //event.getSceneY();
                //System.out.println(img.getBoundsInParent());
                for( int i = 0; i < boardSquares.length; i++ ) {
                    for (int k = 0; k < boardSquares[i].length; k++) {
                        Circle c = new Circle();
                        c.setCenterY((250)+(k)*100);
                        c.setCenterX((250)+(i)*100);
                        c.setRadius(3);
                        c.setFill(Color.RED);
                        root.getChildren().add(c);
                        if(root.getChildren().get(root.getChildren().indexOf(img)).contains((250)+(i)*100,(250)+(k)*100)){
                            c.setFill(Color.GREEN);
                            boardSquares[i][k].setFilled(true);
                        };

                    }


        };

*/

        //img1.setX(150);
        //System.out.println(root.getChildren().get(root.getChildren().indexOf(boardSquares[0][0])).getScaleX());
       // img1.addEventHandler(MouseEvent.MOUSE_CLICKED, eventH);
/*
        Image two = new Image(Paths.get("src/GameManagement/media/blue.png").toUri().toString());
        ImageView img2 = new ImageView(two);
        Image three = new Image(Paths.get("src/GameManagement/media/yellow.png").toUri().toString());
        ImageView img3 = new ImageView(three);
        img3.setX(150);
        img3.setY(140);
*/
        //img2.addEventHandler(MouseEvent.MOUSE_CLICKED, eventH);
        //img3.addEventHandler(MouseEvent.MOUSE_CLICKED, eventH);

        //JLabel picLabel = new JLabel(new ImageIcon(img1));
        //HBox pictures = new HBox();
        //img2.setRotate(180);
        //img3.setRotate(180);
        //if(img1.getOnDragDropped() != null ){
        //    img1.setRotate(10);
        //}
        /*MouseControlUtil.makeDraggable(img1);
        MouseControlUtil.makeDraggable(img2);
        MouseControlUtil.makeDraggable(img3);*/
        root.getChildren().add(img1);
        root.getChildren().add(img2);
        root.getChildren().add(img3);

        root.getChildren().add(stickView);


        /*for( int i = 0; i < boardSquares.length; i++ ) {
            for (int k = 0; k < boardSquares[i].length; k++) {
                Circle c = new Circle();
                c.setCenterY((250)+(k)*100);
                c.setCenterX((250)+(i)*100);
                c.setRadius(3);
                root.getChildren().add(c);
                System.out.println(img3.getX() + "y : " + img3.getY());
                System.out.println(root.getChildren().get(root.getChildren().indexOf(img3)).contains((250)+(i)*100,(250)+(k)*100) + "x : " + ((startx)+(i)*100) + " y : " + ((starty)+(k)*100));
            }
        }*/

        //System.out.println(root.getChildren().get(root.getChildren().indexOf(img3)).contains(root.getChildren().get(root.getChildren().indexOf(boardSquares[1][2]))));
        //gp.add(pictures,1,1);
        //System.out.println(boardSquares[0][0].setXCoordinate(););
        //root.getChildren().get(root.getChildren().indexOf(img3))

        //root.getChildren().add(gp);

        //root.getChildren().get(root.getChildren().indexOf(img3)).con
        primaryStage.setTitle("Katamino");
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