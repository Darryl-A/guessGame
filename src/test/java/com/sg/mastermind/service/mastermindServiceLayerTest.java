/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.mastermind.service;

import com.sg.mastermind.TestApplicationConfiguration;
import com.sg.mastermind.dao.gameDao;
import com.sg.mastermind.dao.roundDao;
import com.sg.mastermind.entity.Game;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author darrylanthony
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class mastermindServiceLayerTest {
    
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
    }

    /**
     * Test of generateAnswer method, of class mastermindServiceLayer.
     */
    @Test
    public void testGenerateAnswer() {
    }

    /**
     * Test of guess method, of class mastermindServiceLayer.
     */
    @Test
    public void testGuess() {
    }

    /**
     * Test of calculateResult method, of class mastermindServiceLayer.
     */
    @Test
    public void testCalculateResult() {
    }

    /**
     * Test of getAllGames method, of class mastermindServiceLayer.
     */
    @Test
    public void testGetAllGames() {
    }

    /**
     * Test of getGameByID method, of class mastermindServiceLayer.
     */
    @Test
    public void testGetGameByID() {
    }

    /**
     * Test of getRoundsByGameID method, of class mastermindServiceLayer.
     */
    @Test
    public void testGetRoundsByGameID() {
    }
    
}
