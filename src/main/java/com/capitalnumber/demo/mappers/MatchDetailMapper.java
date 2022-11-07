package com.capitalnumber.demo.mappers;

import com.capitalnumber.demo.model.MatchDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchDetailMapper implements RowMapper<MatchDetail> {
    @Override
    public MatchDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        MatchDetail md = new MatchDetail();
        md.setId(rs.getLong("m_id"));
        md.setMatchStartTime(rs.getTimestamp("m_match_start_time").toLocalDateTime());
        md.setHomeTeamName(rs.getString("m_home_team_name"));
        md.setAwayTeamName(rs.getString("m_away_team_name"));
        md.setHomeTeamScore(rs.getInt("m_home_team_score"));
        md.setAwayTeamScore(rs.getInt("m_away_team_score"));
        return md;
    }
}
