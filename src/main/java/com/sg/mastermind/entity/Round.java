package com.sg.mastermind.entity;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.roundID;
        hash = 59 * hash + Objects.hashCode(this.result);
        hash = 59 * hash + Objects.hashCode(this.roundTime);
        hash = 59 * hash + Objects.hashCode(this.guess);
        hash = 59 * hash + this.gameID;
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
        final Round other = (Round) obj;
        if (this.roundID != other.roundID) {
            return false;
        }
        if (this.gameID != other.gameID) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        return Objects.equals(this.roundTime, other.roundTime);
    }

}