package com.capitalnumber.demo.service;

import com.capitalnumber.demo.model.MatchDetail;
import com.capitalnumber.demo.repository.MatchDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchDetailsServiceImpl implements MatchDetailService{
    @Autowired
    MatchDetailDAO matchDetailDAO;
    @Override
    public MatchDetail save(MatchDetail matchDetail) {
        return matchDetailDAO.save(matchDetail);
    }

    @Override
    public MatchDetail findById(Long id) {
        return matchDetailDAO.findById(id);
    }

    @Override
    public void deleteUpComingMatches() {
        matchDetailDAO.deleteUpCommingMatchces();
    }

    @Override
    public List<MatchDetail> getAllWinnerList() {
        return matchDetailDAO.getAllWinnerList();
    }

    @Override
    public void updateMatchDetail(Long id, MatchDetail patchedMatchDetails) {
        matchDetailDAO.updateMatchDetail(id, patchedMatchDetails);
    }

    @Override
    public List<MatchDetail> getMatchesByTeamId(Long id) {
        return matchDetailDAO.getMatchesByTeamId(id);
    }

    @Override
    public List<MatchDetail> getUpcomingMatchesByTeamId(Long id) {
        return matchDetailDAO.getUpcomingMatchesByTeamId(id);
    }

}
