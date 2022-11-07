package com.capitalnumber.demo.repository;

import com.capitalnumber.demo.model.MatchDetail;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchDetailDAO {
    MatchDetail save(MatchDetail matchDetail);
    MatchDetail findById(Long id);

    void deleteUpCommingMatchces();

    List<MatchDetail> getAllWinnerList();
    void updateMatchDetail(Long id, MatchDetail patchedMatchDetails);

    List<MatchDetail> getMatchesByTeamId(Long id);

    List<MatchDetail> getUpcomingMatchesByTeamId(Long id);
}
