package GameManagement;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GamePlay extends Application {

    // properties
    GameMapper gameMapper;
    final String password = "yusuf123";
    private Scene window;
    double originX, originY;
    Text tLab;
    Text timeLL;
    private DigitalClock clock;
    private boolean running;
    TimeKeeper t1;
    private long time;
    private Group root;
    private Scene scene;
    VBox vbox;
    private ArrayList<Double> fitHeightList;
    private ArrayList<Double> fitWidthList;
    private ArrayList<Double> initialListX;
    private ArrayList<Double> initialListY;
    private ArrayList<ImageView> blockList;
    private int startLevel;

    // constructor
    public GamePlay( String gameMode  )
    {
        if( gameMode.equals("NormalMode") )
        {
            gameMapper = new NormalModeMapper(password);
            startLevel = 4;
        }
        else if( gameMode.equals("ChallangeMode") )
        {
            gameMapper = new ChallangeModeMapper( password );
        }
        else
        {
            gameMapper = new DynamicModeMapper( password );
        }
        // boxes and scenes
        root = new Group();
        scene = new Scene(root, 1000, 1000);
        vbox = new VBox();

        // array initializations
        fitHeightList = new ArrayList<>();
        fitWidthList = new ArrayList<>();
        initialListX = new ArrayList<>();
        initialListY = new ArrayList<>();
        blockList = new ArrayList<>();

        // time initialization
        tLab   = new Text(10,20,null);
        timeLL = new Text(100,20,null);
        clock = new DigitalClock();
        running = false;
        t1 = new TimeKeeper();
    }


    @Override
    public void start(Stage primaryStage) {
        // first initialization for showing
        root = new Group();
        scene = new Scene(root, 1000, 1000);
        vbox = new VBox();

        // assignments
        blockList = gameMapper.getInitialImageList();
        fitHeightList = gameMapper.getOriginalHeightScale();
        fitWidthList = gameMapper.getOriginalWidthScale();
        initialListX = gameMapper.getInitialPositionX();
        initialListY = gameMapper.getInitialPositionY();
        Square[][] boardSquares = gameMapper.getSquares();

        /************** Listeners *******************/
        for (int i = 0; i < blockList.size(); i++) {
            blockList.get(i).setCursor(Cursor.HAND);
            double fitHeight = fitHeightList.get(i);
            double fitWidth = fitWidthList.get(i);

            /*************When mouse is pressed********************/
            blockList.get(i).setOnMousePressed((t) -> {
                        // calculation of origin position of image with respect to scene
                        originX = t.getSceneX();
                        originY = t.getSceneY();

                        ImageView blockPiece = (ImageView) (t.getSource());
                        blockPiece.toFront(); // newer image will be in front

                        if (t.getClickCount() % 2 == 0) { // rotate logic
                            double orX = blockPiece.getX();
                            double orY = blockPiece.getY();
                            blockPiece.setFitWidth(fitWidth);
                            blockPiece.setFitHeight(fitHeight);

                            SnapshotParameters param = new SnapshotParameters();
                            param.setFill(Color.TRANSPARENT);
                            param.setTransform(new Rotate(90, blockPiece.getImage().getHeight(), blockPiece.getImage().getWidth()));

                            blockPiece.setImage(blockPiece.snapshot(param, null));
                            blockPiece.setFitWidth(100);
                            blockPiece.setFitHeight(100);
                        }
                    }
            );

            /**************When mouse is dragged****************/
            blockList.get(i).setOnMouseDragged(  (t) ->
                    {
                        ImageView blockPiece = (ImageView)(t.getSource());

                        blockPiece.setFitWidth(fitWidth);
                        blockPiece.setFitHeight(fitHeight);

                        double offsetX = t.getSceneX() - originX;
                        double offsetY = t.getSceneY() - originY;

                        blockPiece.setX( offsetX + blockPiece.getX() );
                        blockPiece.setY( offsetY + blockPiece.getY() );

                        blockPiece.setPreserveRatio(false);

                        originX  = t.getSceneX();
                        originY = t.getSceneY();
                    }

            );

            /*************** When mouse is released *************/
            blockList.get(i).setOnMouseReleased((t)-> {
                int count = 0;
                int count2 = 0;
                ImageView blockPiece = (ImageView)(t.getSource());

                double xLoc = originX , yLoc = originY;
                for( int k = 0; k < startLevel ; k++ ) {
                    for (int j = 0; j < boardSquares[k].length; j++) {
                        boardSquares[k][j].setFilled(false);
                        for( int noOfImage = 0; noOfImage < blockList.size(); noOfImage++ ) {
                            if (root.getChildren().get(root.getChildren().indexOf(blockList.get(noOfImage))).contains((300) + (k) * 100, (150) + (j) * 100)) {
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

                System.out.println(gameMapper.getSQUARESIZE());

                if(count % 5 == 0 && count != 0){
                    //System.out.println(count);
                    System.out.println("True");
                    System.out.println(blockPiece.getX());
                    System.out.println(blockPiece.getY());
                    //blockPiece.setPickOnBounds(true);
                    //System.out.println(blockPiece.getX());

                    double xlocation=0,ylocation=0;
                    System.out.println("X (ilk) lokasyonu " + blockPiece.getImage().getWidth());
                    System.out.println("Y (ilk) lokasyonu " + blockPiece.getImage().getHeight());
                    System.out.println(gameMapper.getCurrentLevel());
                    System.out.println(boardSquares[0].length);

                    for(double b = (blockPiece.getY()+blockPiece.getImage().getHeight());b >blockPiece.getY();b-- ){
                        for(double a = (blockPiece.getX()+ blockPiece.getImage().getWidth());a>blockPiece.getX();a--){
                            if(root.getChildren().get(root.getChildren().indexOf(blockPiece)).contains(a, b)){
                                xlocation = a;
                                ylocation = b;
                            }

                        }
                    }
                    System.out.println("X lokasyonu " + xlocation);
                    System.out.println("Y lokasyonu " + ylocation);

                    double maxheight=0;
                    double maxwidth=0;


                    for(double a = xlocation ; a <= blockPiece.getImage().getWidth() ; a++){
                        if(root.getChildren().get(root.getChildren().indexOf(blockPiece)).contains(a, ylocation))
                            maxwidth = a;
                    }

                    for(double a = ylocation ; a <= blockPiece.getImage().getHeight() ; a++){
                        if(root.getChildren().get(root.getChildren().indexOf(blockPiece)).contains(xlocation, a))
                            maxheight = a;
                    }
                    System.out.println("Max Height" + maxheight + " Max Width" + maxwidth);
                    System.out.println("Origin 1- "+ blockPiece.getX() + " 2- " + blockPiece.getY() );
                    System.out.println("loc 1- "+ xlocation + " 2- " + ylocation );
                    if(xlocation==225)
                        xlocation++;
                    if(ylocation==-75)
                        ylocation++;
                    int x = ((int)xlocation - 225) /100 ;
                    int y = ((int)ylocation - 75) / 100;

                    System.out.println(" 1- "+ x + " 2- " + y );

                    if(maxwidth  == gameMapper.getCurrentLevel())
                        x--;
                    if(maxheight  == boardSquares[0].length)
                        y--;

                    if( x>=0 && x < gameMapper.getCurrentLevel()  && y >= 0 && y < boardSquares[0].length  && blockPiece.preserveRatioProperty().getValue() == false && count2==0) {
                        if (boardSquares[x][y].getStateOfSquare()) {
                            System.out.println("Yok Artık");


                            double stickX = 250+x*100 ;
                            double stickY = 100+y*100 ;
                            blockPiece.setX(stickX + blockPiece.getX() - xlocation);
                            blockPiece.setY(stickY + blockPiece.getY() - ylocation);
                        }
                        else {
                            System.out.println("False1");
                            blockPiece.setX(gameMapper.getInitialPositionX().get(blockList.indexOf(blockPiece)));
                            blockPiece.setY(gameMapper.getInitialPositionY().get(blockList.indexOf(blockPiece)));
                            blockPiece.setPreserveRatio(true);
                            blockPiece.setFitWidth(100);
                            blockPiece.setFitHeight(100);
                        }
                    }
                    else {
                        System.out.println("False2");
                        blockPiece.setX(gameMapper.getInitialPositionX().get(blockList.indexOf(blockPiece)));
                        blockPiece.setY(gameMapper.getInitialPositionY().get(blockList.indexOf(blockPiece)));
                        blockPiece.setPreserveRatio(true);
                        blockPiece.setFitWidth(100);
                        blockPiece.setFitHeight(100);
                    }
                }
                else{
                    System.out.println("False3");
                    blockPiece.setX(gameMapper.getInitialPositionX().get(blockList.indexOf(blockPiece)));
                    blockPiece.setY(gameMapper.getInitialPositionY().get(blockList.indexOf(blockPiece)));
                    blockPiece.setPreserveRatio(true);
                    blockPiece.setFitWidth(100);
                    blockPiece.setFitHeight(100);
                }

                if ( gameMapper.isLevelFinished(startLevel)){
                   /* try {
                        t1.stopTimer(t1.getTime());
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    startLevel++;
                    start( primaryStage );
                }
                /*if(){
                    System.out.println("Game is over");

                    for( int noOfBlock = 0; noOfBlock < blockList.size(); noOfBlock++)
                        blockList.get(noOfBlock).setDisable( true );
                }*/
            });
        }


            /*****  adding parameters into root *******/

            // board creation
            for (int i = 0; i < boardSquares.length; i++)
                for (int k = 0; k < boardSquares[i].length; k++)
                    root.getChildren().add(boardSquares[i][k].getRect());

            vbox.getChildren().addAll(clock);

            root.getChildren().add(gameMapper.getStickView());
            root.getChildren().add(vbox);

            // time keeper
            for (int i = 0; i < blockList.size(); i++)
                root.getChildren().add(blockList.get(i));
            runClock();
            t1.run(timeLL);

            /*********************************/
            primaryStage.setTitle("Katamino");
            primaryStage.setScene(scene);
            primaryStage.show();
            window = scene;
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
