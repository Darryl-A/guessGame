/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.TestApplicationConfiguration;
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
public class roundDaoDBImplTest {
    
    @Autowired
    gameDao GD;
    
    @Autowired
    roundDao RD;
    
    public roundDaoDBImplTest() {
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        List<Game> rounds = RD.();
//        for(Game game: games){
//            GD.deleteGameById(game.getGameID());
//        }
//    }
//    
//    @After
//    public void tearDown() {
//    }

    /**
     * Test of getAllRoundsByGameID method, of class roundDaoDBImpl.
     */
    @Test
    public void testGetAllRoundsByGameID() {
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
        List<Round> rounds = RD.getAllRoundsByGameID(gameID);
        
        //Assert
        //Assert 1 - List should have 3 elements
        assertEquals(rounds.size(), 3);
        
        //Assert - list should have all 3 rounds
        assertTrue(rounds.contains(round1));
        assertTrue(rounds.contains(round2));
        assertTrue(rounds.contains(round3));
    }

    /**
     * Test of getRoundByID method, of class roundDaoDBImpl.
     */
    @Test
    public void testGetRoundByID() {
        //Arrange
        Game game = new Game();
        game.setAnswer("1234");
        game.setGameStatus(true);
        GD.addGame(game);
        
        Round round = new Round();
        round.setGuess("5678");
        round.setGameID(game.getGameID());
        round.setResult("e:0:p:0");
        RD.addRound(round);
        
        Round newRound = RD.getRoundByID(round.getRoundID());
        RD.addRound(newRound);
        
        //Assert - should be equal
        assertEquals(round.getResult(), newRound.getResult());
    }

    /**
     * Test of addRound method, of class roundDaoDBImpl.
     */
    @Test
    public void testAddRound() {
        //Arrange
        Game game = new Game();
        game.setAnswer("1234");
        game.setGameStatus(true);
        game = GD.addGame(game);
  
        Round round = new Round();
        round.setGuess("1234");
        round.setResult("e:0:p:0");
        round.setGameID(game.getGameID());
        RD.addRound(round);
        
        //Get the round
        List<Round> rounds = RD.getAllRoundsByGameID(game.getGameID());
        
        
        //Assert - Should be the same
        assertEquals(rounds.size(), 1);
    }
    
}
