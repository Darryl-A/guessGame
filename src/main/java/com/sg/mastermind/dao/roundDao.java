/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Round;
import java.util.List;

/**
 *
 * @author darrylanthony
 */
public interface roundDao {
    List<Round> getAllRoundsByGameID(int gameId);
    Round getRoundByID(int roundId);
    Round addRound(Round round);
}
