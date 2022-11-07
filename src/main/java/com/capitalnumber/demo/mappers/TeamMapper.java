package com.capitalnumber.demo.mappers;

import com.capitalnumber.demo.model.MatchDetail;
import com.capitalnumber.demo.model.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMapper implements RowMapper<Team> {
    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team t = new Team();
        t.setId(rs.getLong("t_id"));
        t.setName(rs.getString("t_name"));
        return t;
    }
}