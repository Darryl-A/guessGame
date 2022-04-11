/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.mastermind.service;

import com.sg.mastermind.TestApplicationConfiguration;
import com.sg.mastermind.dao.gameDao;
import com.sg.mastermind.dao.roundDao;
import com.sg.mastermind.entity.Game;
import com.sg.mastermind.entity.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author darrylanthony
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@Profile("database2")
public class mastermindServiceLayerTest {
    
    @Autowired
    mastermindServiceLayer service;
    
    @Autowired
    gameDao GD;
    
    @Autowired
    roundDao RD;
    
    public mastermindServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Game> games = GD.getAllGames();
        for(Game game: games){
            GD.deleteGameById(game.getGameID());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createNewGame method, of class mastermindServiceLayer.
     */
    @Test
    public void testCreateNewGame() {
        //Arrange
        int ID = service.createNewGame(); 
        
        //Assert - should return the game ID
        assertNotNull(ID);
    }

    /**
     * Test of generateAnswer method, of class mastermindServiceLayer.
     */
    @Test
    public void testGenerateAnswer() {
        
        //Arrange
        String answer1 = service.generateAnswer();
        String answer2 = service.generateAnswer();
        
        //Assert - should not be null
        assertNotNull(answer1);
        assertNotNull(answer2);
        
        //Assert - both should be different
        assertNotEquals(answer1, answer2);
        
    }

    /**
     * Test of guess method, of class mastermindServiceLayer.
     */
    @Test
    public void testGuess() {
        Game game  = new Game();
        game.setAnswer("1234");
        game.setGameStatus(true);
        GD.addGame(game);
        
        int gameID = game.getGameID();
        Round round = new Round();
        round.setGuess("1234");
        round.setGameID(gameID);
        service.guess(round);  
        
        assertEquals(round.getResult(), "e:4:p:0");
        
    }

    /**
     * Test of calculateResult method, of class mastermindServiceLayer.
     */
    @Test
    public void testCalculateResult() {
        //Arrange
        //Test-1
        String correctAnswer1 = "1234";
        String userGuess1 = "1234";
        String result1 = service.calculateResult(correctAnswer1,userGuess1);
        
        //Test-2
        String correctAnswer2 = "1234";
        String userGuess2 = "5678";
        String result2 = service.calculateResult(correctAnswer2,userGuess2);
        
        //Test-3
        String correctAnswer3 = "1234";
        String userGuess3 = "1324";
        String result3 = service.calculateResult(correctAnswer3,userGuess3);
        
        
        //Assert
        //AssertTest-1
        assertEquals("e:4:p:0", result1);
        
        //AssertTest-2
        assertEquals("e:0:p:0", result2);
        
        //AssertTest-3
        assertEquals("e:2:p:2", result3);
        
    }

    /**
     * Test of getAllGames method, of class mastermindServiceLayer.
     */
    @Test
    public void testGetAllGames() {
        //Arrange - create a couple of games
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setGameStatus(true);
        GD.addGame(game1);
        
        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setGameStatus(true);
        GD.addGame(game2);
        
        //Get all the games and store in a list
        List<Game> games = service.getAllGames();
        
        //Assert
        //Assert 1 - List should have 2 games
        assertEquals(games.size(), 2);
    }

    /**
     * Test of getGameByID method, of class mastermindServiceLayer.
     */
    @Test
    public void testGetGameByID() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setGameStatus(false);
        GD.addGame(game);
        
        String answer = service.getGameByID(game.getGameID()).getAnswer();
        
        assertEquals(game.getAnswer(), answer);
    }

    /**
     * Test of getRoundsByGameID method, of class mastermindServiceLayer.
     */
    @Test
    public void testGetRoundsByGameID() {
        //Arrange
        //Create a game
        Game game  =  new Game();
        game.setAnswer("1234");
        game.setGameStatus(true);
        GD.addGame(game);
        
        //Get the id number
        int gameID = game.getGameID();
        
        //Create several rounds
        Round round1 = new Round();
        round1.setGuess("1789");
        round1.setResult("e:1:p:0");
        round1.setGameID(gameID);
        round1 = RD.addRound(round1);
        
        Round round2 = new Round();
        round2.setGuess("5134");
        round2.setResult("e:1:p:1");
        round2.setGameID(gameID);
        round2 = RD.addRound(round2);
        
        Round round3 = new Round();
        round3.setGuess("1289");
        round3.setResult("e:2:p:0");
        round3.setGameID(gameID);
        round3  = RD.addRound(round3);
        
        //Create a list
        List<Round> rounds = service.getRoundsByGameID(gameID);
        
        //Assert
        //Assert 1 - List should have 3 elements
        assertEquals(rounds.size(), 3);
        
        //Assert - list should have all 3 rounds
        assertTrue(rounds.contains(round1));
        assertTrue(rounds.contains(round2));
        assertTrue(rounds.contains(round3));
    }
    
}
