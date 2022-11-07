package com.capitalnumber.demo.service;

import com.capitalnumber.demo.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    List<Team> getTeamDetails();
}
