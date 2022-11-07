package com.capitalnumber.demo.repository;

import com.capitalnumber.demo.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDAO{
    List<Team> getTeamDetails();
}
