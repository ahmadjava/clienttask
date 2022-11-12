package com.capitalnumber.demo.service;

import com.capitalnumber.demo.exceptions.ResourceUnavailableException;
import com.capitalnumber.demo.model.MatchDetail;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface MatchDetailService {
    MatchDetail save(MatchDetail matchDetail);
    MatchDetail findById(Long id) throws ResourceUnavailableException;

    void deleteUpComingMatches() throws ResourceUnavailableException;

    List<MatchDetail> getAllWinnerList();
    void updateMatchDetail(Long id, MatchDetail patchedMatchDetails);

    List<MatchDetail> getMatchesByTeamId(Long id);

    List<MatchDetail> getUpcomingMatchesByTeamId(Long id);
}
