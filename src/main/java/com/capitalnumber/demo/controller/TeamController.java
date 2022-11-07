package com.capitalnumber.demo.controller;


import com.capitalnumber.demo.model.Team;
import com.capitalnumber.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping
    @ResponseBody
    public List<Team> getTeamDetails(){
        List<Team> teams = teamService.getTeamDetails();
        return teams;
    }

}
