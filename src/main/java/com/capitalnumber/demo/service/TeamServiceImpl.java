package com.capitalnumber.demo.service;

import com.capitalnumber.demo.model.Team;
import com.capitalnumber.demo.repository.TeamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamDAO teamDAO;
    @Override
    public List<Team> getTeamDetails() {
        return teamDAO.getTeamDetails();
    }
}
