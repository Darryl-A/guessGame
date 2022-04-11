/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.TestApplicationConfiguration;
import com.sg.mastermind.entity.Game;
import java.util.List;
import org.junit.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
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
//@Profile("database2")
public class gameDaoDBImplTest {
    
    @Autowired
    gameDao GD;
    
    @Autowired
    roundDao RD;
    
//    public gameDaoDBImplTest() {
//    }

//    @org.junit.jupiter.api.BeforeAll
//    public static void setUpClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterAll
//    public static void tearDownClass() throws Exception {
//    }

//    @org.junit.jupiter.api.BeforeEach
//    public void setUp() throws Exception {
//    }

//    @org.junit.jupiter.api.AfterEach
//    public void tearDown() throws Exception {
//    }
    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        List<Game> games = GD.getAllGames();
        for(Game game: games){
            GD.deleteGameById(game.getGameID());
        }
    }
    
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Test of getAllGames method, of class gameDaoDBImpl.
     */
    //@org.junit.jupiter.api.Test
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
        List<Game> games = GD.getAllGames();
        
        //Assert
        //Assert 1 - List should have 2 games
        assertEquals(games.size(), 2);
        
        //Assert 2 - List should contain game 1
        assertTrue(games.contains(game1));
        
        //Assert 3 - List should contain game 1
        assertTrue(games.contains(game2));
    }

    /**
     * Test of getGameById method, of class gameDaoDBImpl.
     */
    //@org.junit.jupiter.api.Test
    @Test
    public void testGetGameById() {
        Game game = new Game();
        //game.setGameID(1);
        game.setAnswer("1234");
        game.setGameStatus(true);
        game = GD.addGame(game);
        
        //Get a new game with the same ID
        Game newGame = GD.getGameById(game.getGameID());
        //newGame = GD.addGame(newGame);
        
        //Assert - both should be same
        assertEquals(game, newGame);
    }

    /**
     * Test of addGame method, of class gameDaoDBImpl.
     */
    //@org.junit.jupiter.api.Test
    @Test
    public void testAddGame() {
        
        //Arrange
        Game game = new Game();
        game.setAnswer("1234");
        game.setGameStatus(true);
        game = GD.addGame(game);
        
        //Get a with that ID
        Game newGame = GD.getGameById(game.getGameID());
        
        //Assert - Should be the same
        assertEquals(game, newGame);
    }

    /**
     * Test of updateGame method, of class gameDaoDBImpl.
     */
    //@org.junit.jupiter.api.Test
    @Test
    public void testUpdateGame() {
        //Arrange
        Game game = new Game();
        game.setAnswer("1234");
        game.setGameStatus(true);
        
        //Add the game
        game = GD.addGame(game);
        
        Game newGame = GD.getGameById(game.getGameID());
        
        //Assert - should be equal
        assertEquals(game, newGame);
        
        //Now make changes and see if it returns false
        //game.setAnswer("4321");
        game.setGameStatus(false);
        GD.updateGame(game);
        
        //Assert - answer was changed, shouldn't be equal
        assertNotEquals(game,newGame);
        
        //Get back the old game again
        newGame = GD.getGameById(game.getGameID());
        
        //New fetch should be equal
        assertEquals(game, newGame);
    }

    /**
     * Test of deleteGameById method, of class gameDaoDBImpl.
     */
    //@org.junit.jupiter.api.Test
    @Test
    public void testDeleteGameById() {
        //Arrange
        Game game = new Game();
        game.setAnswer("1234");
        game.setGameStatus(true);
        GD.addGame(game);
        int ID = game.getGameID();
        
        //First check if the game exists
        Game newGame = GD.getGameById(ID);
        
        //If exists, assert should be equal
        assertEquals(game, newGame);
        
        //Now remove
        GD.deleteGameById(game.getGameID());
        
        //Create a game from this ID - should be null
        Game deletedGame = GD.getGameById(game.getGameID());
        
        //Assert- should be null
        assertNull(deletedGame);
    }
    
}
