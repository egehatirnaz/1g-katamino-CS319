package GameManagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BlockMaker extends Application {

    BlocksDatabase database;


    public BlockMaker(){
         database = new BlocksDatabase(GamePlay.password);
    }

    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button();
        btn.setText("Make all blocks!!");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                blockMaker("n");
                System.out.println("Blocks created!!");
            }
        });

        Button dynamic = new Button();
        dynamic.setText( "Dynamic Mode Blocks!" );
        dynamic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                blockMaker("d");
                System.out.println( "Dynamic blocks are ready!" );
            }
        });

        Pane root = new Pane();
        root.getChildren().add( btn );
        //root.getChildren().add( dynamic );
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void blockMaker( String mode )
    {
        ArrayList<Direction> adjacents = new ArrayList<Direction>();
        String loc = "";
        for( int i = 1 ; i <= 12; i++)
        {
            loc = "src/GameManagement/media/";
            String blockName = mode + i;
            adjacents = database.getDirections( blockName );
            VertexSaveAsImage save = new VertexSaveAsImage();
            Square main = new Square( 100, 100,100,100);
            Vertex v;
            Direction[] directions = new Direction[ adjacents.size()];
            for ( int k = 0; k < adjacents.size(); k++)
            {
                directions[ k ]  = adjacents.get(k);
            }
            loc += blockName + ".png";
            Color c = Color.rgb(database.getColor(blockName).getRed(),database.getColor(blockName).getGreen(),
                    database.getColor(blockName).getBlue() );
            v = new Vertex(main, directions, c , loc);
            save.setRequirements( v , loc);
            save.start(new Stage());
        }

    }
/*
    public void setBlocks()
    {
        Square s = new Square( 100.0, 100.0, 100, 100 );
        int directions1[][] = { {1,0,0,0}, {1,0,0,1}, {2,0,0,1}, {3,0,0,1} };
        Direction[] directions = new Direction[4];
        for ( int i = 0; i < directions.length; i++)
        {

        }
        String loc = "src/GameManagement/media/aBlock.png";
        Color c = Color.BLUE;
        System.out.println( c );
        Vertex v = new Vertex(s,directions,c,loc);

        VertexSaveAsImage save = new VertexSaveAsImage();

        save.setRequirements( v, loc);
        save.start(new Stage());

    }

*/}

