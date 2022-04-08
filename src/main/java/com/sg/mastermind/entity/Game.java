package com.sg.mastermind.entity;

/**
 * 
 * @author darrylanthony
 */

public class Game{
    int gameID;
    boolean gameStatus;
    String answer;
    
    //For row mapper
    public Game(){
    }
    
    public Game (int gameID){
        this.gameID = gameID;
    }
    
    public void setGameID(int gameID){
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public boolean getGameStatus() {
        return gameStatus; 
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}