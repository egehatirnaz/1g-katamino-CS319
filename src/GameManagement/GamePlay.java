package GameManagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GamePlay extends Application {

    // properties
    GameMapper gameMapper;
    final public static String password = "yusuf123";
    private Scene window;
    double originX, originY;
    Text tLab;
    Text timeLL;
    private DigitalClock clock;
    private boolean running;
    TimeKeeper t1;
    private long time;
    private Pane root;
    private Scene scene;
    VBox vbox;
    int oldTime;
    int oldNumber;
    private ArrayList<Double> fitHeightList;
    private ArrayList<Double> fitWidthList;
    private ArrayList<Double> initialListX;
    private ArrayList<Double> initialListY;
    private ArrayList<ImageView> blockList;
    private int startLevel;
    private String modeName;
    private int lev;
    private Player player;
    private PlayerDatabase pd;
    private Button backButton;
    private int count;
    private int number;
    private int lastPoint;


    // constructor
    public GamePlay( String gameMode  )
    {
        if( gameMode.equals("NormalMode") )
        {
            gameMapper = new NormalModeMapper(password);
            startLevel = 3;

        }
        else if( gameMode.equals("ChallangeMode") )
        {
            gameMapper = new ChallangeModeMapper( password );
            startLevel = 4;
        }
        else if( gameMode.equals( "DynamicMode" ) )
        {
            gameMapper = new DynamicModeMapper( password );
            startLevel = 1;
        }
        pd = new PlayerDatabase(password);
        player = new Player(pd.getLastNickname(), 0);
        modeName = gameMode;

        // array initializations
        fitHeightList = new ArrayList<>();
        fitWidthList = new ArrayList<>();
        initialListX = new ArrayList<>();
        initialListY = new ArrayList<>();
        blockList = new ArrayList<>();

        // time initialization
        tLab   = new Text(10,20,null);
        timeLL = new Text(100,20,null);
        clock = new DigitalClock(true);//?
        running = false;
        t1 = new TimeKeeper();
        runClock();
    }


    @Override
    public void start(Stage primaryStage) {
        // first initialization for showing
        root = new Pane();
        scene = new Scene(root, primaryStage.getWidth(),primaryStage.getHeight());
        vbox = new VBox();

        Button btn = new Button();
        btn.setText("Exit");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });


        btn.setLayoutX(50);
        btn.setLayoutY(840);
        btn.setPrefWidth(140);
        btn.setStyle("-fx-background-radius: 20; -fx-border-color: #a48129; -fx-border-radius: 20;");
        btn.setOpacity(0.5);
        root.getChildren().add(btn);


        // assignments
        blockList = gameMapper.getInitialImageList();
        fitHeightList = gameMapper.getOriginalHeightScale();
        fitWidthList = gameMapper.getOriginalWidthScale();
        initialListX = gameMapper.getInitialPositionX();
        initialListY = gameMapper.getInitialPositionY();
        Square[][] boardSquares = gameMapper.getSquares();

        lev = gameMapper.getCurrentLevel();
        if( modeName.equals("DynamicMode") )
            lev = boardSquares.length;


        /************** Listeners *******************/
        for (int i = 0; i < blockList.size(); i++) {
            blockList.get(i).setCursor(Cursor.HAND);
            double fitHeight = fitHeightList.get(i);
            double fitWidth = fitWidthList.get(i);

            /************* When mouse is pressed ********************/
            blockList.get(i).setOnMousePressed((t) -> {
                        // calculation of origin position of image with respect to scene
                        originX = t.getSceneX();
                        originY = t.getSceneY();

                        ImageView blockPiece = (ImageView) (t.getSource());
                        blockPiece.toFront(); // newer image will be in front

                // rotate logic
                if( (t.getButton() == MouseButton.PRIMARY) && (t.getClickCount()%2==0 ) ) {
                    blockPiece.setFitWidth(fitWidth);
                    blockPiece.setFitHeight(fitHeight);

                    SnapshotParameters param = new SnapshotParameters();
                    param.setFill(Color.TRANSPARENT);
                    param.setTransform(new Rotate(90,blockPiece.getImage().getHeight(),blockPiece.getImage().getWidth()));
                    blockPiece.setImage(blockPiece.snapshot(param,null));
                    blockPiece.setFitWidth(100);
                    blockPiece.setFitHeight(100);
                    returnToInitialPlace(blockPiece);
                }
                else if(  t.getButton() == MouseButton.SECONDARY  )
                {
                    blockPiece.setFitWidth(fitWidth);
                    blockPiece.setFitHeight(fitHeight);

                    SnapshotParameters param = new SnapshotParameters();
                    param.setFill(Color.TRANSPARENT);
                    param.setTransform(new Scale( 1,-1));
                    blockPiece.setImage(blockPiece.snapshot(param,null));
                    blockPiece.setFitWidth(100);
                    blockPiece.setFitHeight(100);
                    returnToInitialPlace(blockPiece);
                }
            });

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
                int count1 = 0;
                int count2 = 0;
                ImageView blockPiece = (ImageView)(t.getSource());
               // System.out.println( "GAMEPLAY BOARDX: " + gameMapper.getBOARDCOORDX() + "GAMEPLAY BOARDY: " + gameMapper.getBOARDCOORDY() );
                double xLoc = originX , yLoc = originY;
                for( int k = 0; k < lev ; k++ ) {
                    for (int j = 0; j < boardSquares[k].length; j++) {
                        boardSquares[k][j].setFilled(false);
                        for( int noOfImage = 0; noOfImage < blockList.size(); noOfImage++ ) {
                            if (root.getChildren().get(root.getChildren().indexOf(blockList.get(noOfImage))).contains(gameMapper.getBOARDCOORDX()+50 + (k) * 100, (gameMapper.getBOARDCOORDY()+50) + (j) * 100)) {
                                if(boardSquares[k][j].getStateOfSquare()!= true) {
                                    boardSquares[k][j].setFilled(true);
                                    count1++;
                                }
                                else
                                    count2++;
                            }
                        }
                    }
                }

                if( count1 != 0){
                    double xlocation=0,ylocation=0;
                    boolean outside = false;
                    for(double b = (blockPiece.getY()+blockPiece.getImage().getHeight());b >blockPiece.getY();b= b - 10){
                        for(double a = (blockPiece.getX()+ blockPiece.getImage().getWidth());a>blockPiece.getX();a = a - 10){
                            if(root.getChildren().get(root.getChildren().indexOf(blockPiece)).contains(a, b)){
                                xlocation = a;
                                ylocation = b;
                                if((xlocation > gameMapper.getBOARDCOORDX()-25 && xlocation < (gameMapper.getBOARDCOORDX()+25 + lev * 100 ) && (ylocation > (gameMapper.getBOARDCOORDY()-25) && ylocation < gameMapper.getBOARDCOORDY()+25 + (boardSquares[0].length)*100 ))== false && !outside ) {
                                    outside = true;
                                }
                            }

                        }
                    }
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

                    if(xlocation==gameMapper.getBOARDCOORDX()-25)
                        xlocation++;
                    if(ylocation==gameMapper.getBOARDCOORDY()-25)
                        ylocation++;
                    int x = ((int)xlocation - ((int)gameMapper.getBOARDCOORDX()-25)) /100 ;
                    int y = ((int)ylocation - ((int)gameMapper.getBOARDCOORDY()-25)) / 100;

                    if(maxwidth  == lev)
                        x--;
                    if(maxheight  == boardSquares[0].length)
                        y--;

                    if( x>=0 && x < lev  && y >= 0 && y < boardSquares[0].length  && blockPiece.preserveRatioProperty().getValue() == false && count2==0 && outside==false) {
                        if (boardSquares[x][y].getStateOfSquare()) {
                            double stickX = gameMapper.getBOARDCOORDX()+x*100 ;
                            double stickY = gameMapper.getBOARDCOORDY()+y*100 ;
                            blockPiece.setX(stickX + blockPiece.getX() - xlocation);
                            blockPiece.setY(stickY + blockPiece.getY() - ylocation);
                        }
                        else {
                            returnToInitialPlace(blockPiece);
                        }
                    }
                    else {
                        returnToInitialPlace(blockPiece);
                    }
                }
                else {
                    returnToInitialPlace(blockPiece);
                }




                if ( gameMapper.isLevelFinished(lev)){
                    /*try {
                        t1.stopTimer(t1.getTime());
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    if(gameMapper.isGameFinished())
                    {
                        running = false;
                        root.getChildren().add(gameMapper.getAward());
                        pd.updatePlayerTime(player.getNickName(), lastPoint);
                        pd.closeDatabase();
                    }
                    else {
                        lev++;
                        start(primaryStage);
                    }
                }
            });
        }


            /*****  adding parameters into root *******/

            // board creation
            for (int i = 0; i < boardSquares.length; i++)
                for (int k = 0; k < boardSquares[i].length; k++)
                    root.getChildren().add(boardSquares[i][k].getRect());

            vbox.getChildren().addAll(clock);

            if( gameMapper.getStickView() != null )
            {
                root.getChildren().add(gameMapper.getStickView());
            }
            root.getChildren().add(vbox);
            root.setBackground( gameMapper.getBackgroundImage() );


            /**********************time keeper********************/
            for (int i = 0; i < blockList.size(); i++)
                root.getChildren().add(blockList.get(i));
            t1.run(timeLL);

            /*********************************/
            primaryStage.setTitle("Katamino");
            primaryStage.setScene(scene);
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setFullScreen(true);
            primaryStage.setMaximized(false);
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

            //primaryStage.setResizable(false);
            primaryStage.show();
            window = scene;
    }

    private void runClock() {
        running = true;
        new Thread(() -> {
            long last = System.nanoTime();
            double delta = 0;
            double ns = 1000000000.0;

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
                    delta--;
                }
                lastPoint = (count + (60 * number));
            }
        }).start();
    }

    private void returnToInitialPlace( ImageView blockPiece )
    {
        blockPiece.setX( initialListX.get( blockList.indexOf(blockPiece) ) );
        blockPiece.setY( initialListY.get( blockList.indexOf(blockPiece) ) );
        //System.out.println( "Initial PosX: " + blockPiece.getX() + "Initial PosY: " + blockPiece.getY() );
        blockPiece.setPreserveRatio(true);
        blockPiece.setFitWidth(100);
        blockPiece.setFitHeight(100);
    }

    public Scene returnScene() {
        return window;
    }

}
