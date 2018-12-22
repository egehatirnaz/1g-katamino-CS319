package GameManagement;
import java.lang.*;

public class Player {
    private String nickName;
    private int time;


    public Player(String nickName){
        this.nickName = nickName;
        //this.playerID = playerID;
        this.time = 0;
    }

    public Player(String nickName, int time){
        this.nickName = nickName;
        //this.playerID = playerID;
        this.time = time;
    }

    public String getNickName(){
        return nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public int getTime(){
        return time;
    }
    
    public String toString() {
    	return getNickName() + "  " + String.valueOf(time);
    }

    public void setTime(int time) {
        this.time = time;
    }
}