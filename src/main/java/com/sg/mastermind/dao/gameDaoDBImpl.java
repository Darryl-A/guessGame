package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Profile("database")
public class gameDaoDBImpl implements gameDao{
    
    //private final JdbcTemplate jdbcTemplate;

//    @Autowired
//    public gameDaoDBImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbcTemplate.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int gameID) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameID = ?";
            return jdbcTemplate.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameID);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game(gameStatus, answer) VALUES(?,?)";
        jdbcTemplate.update(INSERT_GAME, 
                game.getGameStatus(), 
                game.getAnswer());
        
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameID(newId);
        return game;
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET gameStatus = ?  WHERE gameID = ?";
        jdbcTemplate.update(UPDATE_GAME,
                game.getGameStatus(),
                game.getGameID());
    }

    @Override
    public void deleteGameById(int gameID) {
        final String DELETE_GAME_FROM_ROUND = "DELETE FROM round WHERE gameID = ? ";
        jdbcTemplate.update(DELETE_GAME_FROM_ROUND, gameID);
        
        final String DELETE_GAME ="DELETE FROM game WHERE gameID = ?";
        jdbcTemplate.update(DELETE_GAME,gameID);
    }
    
    public static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(rs.getInt("gameID"));
            game.setGameStatus(rs.getBoolean("gameStatus"));
            game.setAnswer(rs.getString("answer"));
            return game;
        }
    }
}