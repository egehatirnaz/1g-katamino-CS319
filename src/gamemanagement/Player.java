/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemanagement;

/**
 *
 * @author firatyildiz
 */

import java.util.*;
import java.lang.*;

class Player {
    String nickName;
    int playerID;
    int score; 
    
    public Player(String nickName, int playerID){
        this.nickName = nickName;
        this.playerID = playerID;
        score = 0;
    }
    
    public Player(String nickName, int playerID, int score){
        this.nickName = nickName;
        this.playerID = playerID;
        this.score = score;
    }
    
}
