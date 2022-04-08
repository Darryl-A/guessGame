package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class roundDaoDBImpl implements roundDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Round> getAllRoundsByGameID(int gameID) {
        try {
        final String SELECT_ROUNDS_BY_GAMEID = "SELECT * FROM round "
                + "WHERE gameID = ? ORDER BY roundTime";
        List<Round> allRounds = jdbc.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameID);
        return allRounds;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Round getRoundByID(int roundID) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundID = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundID);
        } catch(DataAccessException ex) {
            return null;
        }    
    }

    @Override
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO round(result, guess, gameID) VALUES(?,?,?)";
        jdbc.update(INSERT_ROUND, 
                round.getResult(), 
                round.getGuess(),
                round.getGameID());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundID(newId);
        return round;
    }
    
    public static final class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundID(rs.getInt("round_id"));
            round.setResult(rs.getString("result"));
            Timestamp timestamp = rs.getTimestamp("roundTime");
            round.setGuessTime(timestamp.toLocalDateTime());
            round.setGuess(rs.getString("guess"));
            round.setGameID(rs.getInt("gameID"));
            return round;
        }
    }
}