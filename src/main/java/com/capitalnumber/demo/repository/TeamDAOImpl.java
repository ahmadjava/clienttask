package com.capitalnumber.demo.repository;

import com.capitalnumber.demo.mappers.TeamMapper;
import com.capitalnumber.demo.model.Team;
import com.capitalnumber.demo.utils.TeamQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDAOImpl implements TeamDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<Team> getTeamDetails() {
        return  jdbcTemplate.query(TeamQuery.TEAMS, new TeamMapper());
    }
}
