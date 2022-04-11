package com.sg.mastermind.entity;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.gameID;
        hash = 97 * hash + (this.gameStatus ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.answer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameID != other.gameID) {
            return false;
        }
        if (this.gameStatus != other.gameStatus) {
            return false;
        }
        return Objects.equals(this.answer, other.answer);
    }
}