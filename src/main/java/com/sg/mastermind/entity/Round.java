package com.sg.mastermind.entity;

import java.time.LocalDateTime;

/**
 * 
 * @author darrylanthony
 */

public class Round{
    private int roundID;
    private String result;
    private LocalDateTime roundTime = LocalDateTime.now();
    private String guess;
    private int gameID;
    
    
    //For round mapper
    public Round(){}
    
    
    public Round(int gameID, String guess) {
        this.gameID = gameID;
        this.guess = guess;
    }
    

    public Round(int roundId, String result, LocalDateTime roundTime ,String guess,int gameID) {
        this.roundID = roundID;
        this.result = result;
        this.roundTime = roundTime;
        this.guess = guess;
        this.gameID = gameID;
        
 
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getGuessTime() {
        return roundTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.roundTime = guessTime;
    }
    
    
    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
    
    
    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

}