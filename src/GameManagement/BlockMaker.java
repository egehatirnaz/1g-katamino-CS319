package GameManagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BlockMaker extends Application {

    SolutionDatabase database;


    public BlockMaker(){
         database = new SolutionDatabase("yusuf123");
    }

    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                blockMaker();
                System.out.println("Blocks created!!");
            }
        });

        Group root = new Group();
        root.getChildren().add( btn );
        Scene scene = new Scene(root, 1, 1);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void blockMaker()
    {
        ArrayList<Direction> adjacents = new ArrayList<Direction>();
        String loc = "";
        for( int i = 1 ; i <= 12; i++)
        {
            loc = "src/GameManagement/media/";
            String blockName = "n" + i;
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
            v = new Vertex(main, directions, Color.BLUE, loc);
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

