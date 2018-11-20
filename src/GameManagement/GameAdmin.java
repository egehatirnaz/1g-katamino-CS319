package GameManagement;

/*
  Game Management Class

  This class is the center piece of system decomposition.

  Ege Hatırnaz
  07.11.2018
 */

import java.lang.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.embed.swing.JFXPanel;
import java.nio.file.Paths;


public class GameAdmin{

    ControlManager controlManager;
    GameMapper gameMapper;
    LeaderBoard leaderBoard;
    LeaderBoardDataBase leaderBoardDB;
    FrameManager demonstration;
    Player player;
    private final int LEVEL_FIRST = 1;
    private final int LEVEL_LAST = 5; // TODO : CHANGE THIS ACCORDINGLY TO THE LAST LEVEL OF THE GAME
    int currentLevel;
    boolean finishGame;
    String gameMode;
    int availableHint;
    private final JFXPanel fx = new JFXPanel(); //panel for sound. must be called.
    private MediaPlayer media;


    private static GameAdmin gameManager = new GameAdmin();


    /* Main method for testing stuff */
    public static void main(String[] args){
        GameAdmin gAdmin = new GameAdmin();
    }

    /*
       GameAdmin Constructor
     */

    public GameAdmin(){
        setMedia("src/GameManagement/media/title_music.mp3");
        media.play();
    }



    public String getGameMode(){

        return gameMode;
    }

    public void pairBlockandID(int blockID){

    }

    public int getCurrentLevel(){

        return currentLevel;
    }

    public void refreshSetBlocks(){

    }

    public void setTime(float time){

    }

    public void finishGame(boolean finishGame){

    }

    public void finishLevel(boolean isFilled){

    }

    public void setMedia(String loc){
        media = new MediaPlayer(new Media(Paths.get(loc).toUri().toString()));
    }
    public void setVolume(double val){
        //0.0 = mute ; 1.0 = full volume
        if(media != null){
            media.setVolume(val);
        }
    }
    public double getVolume(){
        if(media != null){
            return media.getVolume();
        }
        return 0.0;
    }
    public void muteMusic(){
        setVolume(0.0);
    }
    public void unmuteMusic(){
        setVolume(1.0);
    }


    /*** GETTER AND SETTER METHODS ****/

    public ControlManager getControlManager() {
        return controlManager;
    }

    public void setControlManager(ControlManager controlManager) {
        this.controlManager = controlManager;
    }

    public GameMapper getGameMapper() {
        return gameMapper;
    }

    public void setGameMapper(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public LeaderBoardDataBase getLeaderBoardDB() {
        return leaderBoardDB;
    }

    public void setLeaderBoardDB(LeaderBoardDataBase leaderBoardDB) {
        this.leaderBoardDB = leaderBoardDB;
    }

    public FrameManager getDemonstration() {
        return demonstration;
    }

    public void setDemonstration(FrameManager demonstration) {
        this.demonstration = demonstration;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isFinishGame() {
        return finishGame;
    }

    public void setFinishGame(boolean finishGame) {
        this.finishGame = finishGame;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getAvailableHint() {
        return availableHint;
    }

    public void setAvailableHint(int availableHint) {
        this.availableHint = availableHint;
    }
}