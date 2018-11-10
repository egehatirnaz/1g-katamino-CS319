/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

/**
 *
 * @author firatyildiz
 */
import java.util.*;
import java.lang.*;

public class GameAdmin {
    Settings settings;
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

    /*
       GameManagement Constructor
     */
    public GameAdmin(){

    }

    public void updateLevel(){

    }

    public String getGameMode(){

        return "";
    }

    public void pairBlockandID(int blockID){

    }

    public int getCurrentLevel(){

        return -1;
    }

    public void refreshSetBlocks(){

    }

    public void setTime(float time){

    }

    public void finishGame(boolean finishGame){

    }

    public void finishLevel(boolean isFilled){

    }


    /*** GETTER AND SETTER METHODS ****/

    public Control getManager() {
        return Manager;
    }

    public void setManager(Control manager) {
        Manager = manager;
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

