/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

/**
 *
 * @author firat yildiz, burak korkmaz
 */

import java.lang.*;
import java.sql.Time;

class Player {
    private String nickName;
    private int playerID;
    private Time time;

    private int maxLevelClassic; // Holds the maximum level for different types of level
    private int maxLevelChallange; // Holds the maximum level for different types of level
    private int maxLevelDynamic; // Holds the maximum level for different types of level

    public Player(String nickName, int playerID){
        this.nickName = nickName;
        this.playerID = playerID;
        time = new Time(0,0,0);

        maxLevelClassic = 0;
        maxLevelChallange = 0;
        maxLevelDynamic = 0;
    }
    
    public Player(String nickName, int playerID, Time time){
        this.nickName = nickName;
        this.playerID = playerID;
        this.time = time;

        maxLevelClassic = 0;
        maxLevelChallange = 0;
        maxLevelDynamic = 0;
    }

    public String getNickName(){
        return nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public Time getTime(){
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void updateLevel(String level){
        if(level.equals("Classic"))
            maxLevelClassic++;
        if(level.equals("Challange"))
            maxLevelChallange++;
        if(level.equals("Dynamic"))
            maxLevelDynamic++;

    }

    public int getMaxLevelChallange() {
        return maxLevelChallange;
    }

    public int getMaxLevelClassic() {
        return maxLevelClassic;
    }

    public int getMaxLevelDynamic() {
        return maxLevelDynamic;
    }
}
