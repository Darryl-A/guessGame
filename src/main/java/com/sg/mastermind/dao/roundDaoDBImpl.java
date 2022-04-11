package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Profile("database")
public class roundDaoDBImpl implements roundDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public roundDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public List<Round> getAllRoundsByGameID(int gameID) {
        try {
        final String SELECT_ROUNDS_BY_GAMEID = "SELECT * FROM round "
                + "WHERE gameID = ?";
        List<Round> allRounds = jdbcTemplate.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameID);
        return allRounds;
        //return jdbcTemplate.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameID);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Round getRoundByID(int roundID) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundID = ?";
            return jdbcTemplate.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundID);
        } catch(DataAccessException ex) {
            return null;
        }    
    }

    @Override
    @Transactional
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO round(result, guess, gameID,roundTime) VALUES(?,?,?,?)";
        jdbcTemplate.update(INSERT_ROUND, 
                round.getResult(), 
                round.getGuess(),
                round.getGameID(),
                round.getGuessTime());
        
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundID(newId);
        return getRoundByID(newId);
    }
    
    public static final class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundID(rs.getInt("roundID"));
            round.setResult(rs.getString("result"));
            Timestamp timestamp = rs.getTimestamp("roundTime");
            round.setGuessTime(timestamp.toLocalDateTime());
            round.setGuess(rs.getString("guess"));
            round.setGameID(rs.getInt("gameID"));
            return round;
        }
    }
}