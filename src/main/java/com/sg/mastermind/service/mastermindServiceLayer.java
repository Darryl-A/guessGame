package com.sg.mastermind.service;

import com.sg.mastermind.dao.gameDao;
import com.sg.mastermind.dao.roundDao;
import com.sg.mastermind.entity.Game;
import com.sg.mastermind.entity.Round;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author darrylanthony
 */
public class mastermindServiceLayer{
    //Import the DI for both Daos
    @Autowired
    gameDao GD;
    
    @Autowired
    roundDao RD;
    
    //Create a new game
    public int createNewGame(){
        //Start a game
        Game newGame = new Game();
        
        //Generates an answer
        String answer = generateAnswer();
        newGame.setAnswer(answer);
        newGame.setGameStatus(true);
        
        //Add the game
        newGame = GD.addGame(newGame);
        
        int gameID = newGame.getGameID();
        return gameID;
        
    }
    
    //Generates an answer when the game starts
    public String generateAnswer(){
        
        //Create a random number generator
        Random rand = new Random();
        
        //Create an array to store 4 numbers
        int[] digits = new int[4];
        
        //Add the first digit to array
        int firstDigit = rand.nextInt(10);
        digits[0] = firstDigit;
        
        //Add second as long as it's not the same others
        int secondDigit = rand.nextInt(10);
        while(secondDigit == firstDigit)
            secondDigit = rand.nextInt(10);
        digits[1] = secondDigit;
        
        //Add third as long as it's not the same others
        int thirdDigit = rand.nextInt(10);
        while(thirdDigit == secondDigit || thirdDigit == firstDigit)
            thirdDigit = rand.nextInt(10);
        digits[2] = thirdDigit;
        
        //Add fourth as long as it's not the same others
        int fourthDigit = rand.nextInt(10);
        while(fourthDigit == thirdDigit || fourthDigit == secondDigit ||fourthDigit == firstDigit)
            fourthDigit = rand.nextInt(10);
        digits[3] = fourthDigit;
        
        //Convert the array of integers into a string
        String gameNumber = String.join("", IntStream.of(digits).mapToObj(String::valueOf).toArray(String[]::new));
        return gameNumber;
    }
    
    public Round guess (Round round){
        
        //Get the round id
        int roundID = round.getRoundID();
        
        //Get the game being played
        Game currentGame = GD.getGameById(roundID);
        
        //Get the correct answer
        String correctAnswer = currentGame.getAnswer();
        
        //Get the user guess
        String userGuess = round.getGuess();
        
        //Use the formula to see if answer is correct
        String roundResult = calculateResult(correctAnswer, userGuess);
        
        //If guess is correct,
        if(userGuess.equals(correctAnswer)){
            currentGame.setGameStatus(false);
            GD.updateGame(currentGame);
        }
        
        //Finally, add the round.
        Round finishedRound = RD.addRound(round);
        return finishedRound;
    }
    
    
    //Calculate the result of the round
    public static String calculateResult(String correctAnswer, String userGuess){
        char[] first = correctAnswer.toCharArray();
        char[] second = userGuess.toCharArray();
        
        int e = 0;
        int p = 0;
        
        for(int i = 0; i<first.length; i++){
            char str = second[i];
            String pop = Character.toString(str);
            if (correctAnswer.contains(pop)){
                if(first[i] == second[i]){
                    e++;
                }
                else
                    p++;
            }
        }
        String result = "e:" + e + ":p:" + p;
        return result;
    }
    
    //Returns a list of all games
    public List<Game> getAllGames(){
        //Store all the names in a list
        List<Game> allGames = GD.getAllGames();
        
        //Make sure games in progess are not displayed
        for (Game g: allGames){
            if(g.getGameStatus() == true){
                g.setAnswer("Nice try buddy.");
            }
        }
        return allGames;
    }
    
    
    //Returns a game based on ID
    public Game getGameByID(int gameID){
        //Retrieve the game
        Game currentGame = GD.getGameById(gameID);
        
        //If in progress, hide answer
        if(currentGame.getGameStatus() == true){
                currentGame.setAnswer("Nice try buddy.");
            }
        return currentGame;
    }
    
    //Gets all the rounds of a specific game
    public List<Round> getRoundsByGameID(int gameID){
        return RD.getAllRoundsByGameID(gameID);
    }
}