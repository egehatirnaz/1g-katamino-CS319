package GameManagement;

/*
* @author: Yusuf Samsum
* @version: 12/13/2018
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.*;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;


public class VertexSaveAsImage extends Application {

    private Vertex block;
    private ArrayList<Square> adjacencyList;
    private Pane vbox;
    private static final int TOLERANCE_THRESHOLD = 0xFF;
    BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    //javafx.embed.swing.SwingFXUtils fXUtils;
    File file;
    /*public VertexSaveAsImage( Vertex block, String savingLocation, Stage stage )
    {
        vbox = null;
        if( block != null ){
            this.block = block;
            adjacencyList = block.getAdjacent();
        }
        file = new File(savingLocation);
        System.out.println( "so far so good" );
        this.start(stage);
    }
*/
    public void setRequirements( Vertex block, String savingLocation)
    {
        vbox = null;
        if( block != null ){
            this.block = block;
            adjacencyList = block.getAdjacent();
        }
        file = new File(savingLocation);
    }
    @Override
    public void start(Stage primaryStage) {
        vbox = new Pane();

        Square main = block.getMain();

        vbox.getChildren().add(main.getRect());
        for( int i = 0; i < adjacencyList.size(); i++ )
        {
            vbox.getChildren().add(adjacencyList.get(i).getRect());
        }

        Scene scene = new Scene(vbox, 1, 1);
        //scene.setFill(Color.AQUA);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        WritableImage snapshot = vbox.snapshot(new SnapshotParameters(), null);
        ImageView view = new ImageView(snapshot);
        Image trans = makeTransparent(view.getImage());
        ImageView newView= new ImageView( trans) ;
        vbox.getChildren().add(newView);
        System.out.println(vbox.getChildren().size());

        //primaryStage.show();
    }

    private Image makeTransparent(Image inputImage) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);

                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                if (r >= TOLERANCE_THRESHOLD
                        && g >= TOLERANCE_THRESHOLD
                        && b >= TOLERANCE_THRESHOLD) {
                    argb &= 0x00FFFFFF;
                }

                writer.setArgb(x, y, argb);
            }
        }

        saveImage(outputImage);

        return outputImage;
    }
    
    private void saveImage(WritableImage snapshot) {
        BufferedImage image;
        image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, bufferedImage);
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            Logger.getLogger(VertexSaveAsImage.class.getName()).log(Level.SEVERE, null, ex);
        };
    }

   /* public static void main( String args[] )
    {
        /*Square s = new Square( 100.0, 100.0, 100, 100 );
        int directions[][] = { {1,0,0,0}, {2,0,0,0}, {3,0,0,0}, {0,1,0,0} };
        String loc = "src/GameManagement/media/aBlock.png";
        Color c = Color.AQUA;

        Vertex v = new Vertex(s,directions,c,loc);

        VertexSaveAsImage save = new VertexSaveAsImage( v, loc );

        launch(args);
    }*/
}