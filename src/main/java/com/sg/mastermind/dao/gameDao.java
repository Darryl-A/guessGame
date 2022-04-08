/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.mastermind.dao;

import java.util.List;
import com.sg.mastermind.entity.Game;

/**
 *
 * @author darrylanthony
 */
public interface gameDao {
    List<Game> getAllGames();
    Game getGameById(int id);
    Game addGame(Game game);
    void updateGame(Game game);
    void deleteGameById(int id);
}
