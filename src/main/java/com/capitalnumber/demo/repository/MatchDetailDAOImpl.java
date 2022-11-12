package com.capitalnumber.demo.repository;

import com.capitalnumber.demo.exceptions.ResourceUnavailableException;
import com.capitalnumber.demo.mappers.MatchDetailMapper;
import com.capitalnumber.demo.model.MatchDetail;
import com.capitalnumber.demo.utils.MatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MatchDetailDAOImpl implements MatchDetailDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public MatchDetail save(MatchDetail matchDetail) {

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("startTime",matchDetail.getMatchStartTime())
                .addValue("homeTeamName", matchDetail.getHomeTeamName())
                .addValue("awayTeamName", matchDetail.getAwayTeamName())
                .addValue("homeTeamScore", matchDetail.getHomeTeamScore())
                .addValue("awayTeamScore", matchDetail.getAwayTeamScore());
        jdbcTemplate.update(MatchQuery.SAVE_MATCH, parameters, holder);
        matchDetail.setId(Long.parseLong(holder.getKeys().get("m_id").toString()));
        return matchDetail;
    }

    @Override
    public MatchDetail findById(Long id) {
        Map params = new HashMap();
        params.put("id", id);
        return jdbcTemplate.queryForObject(MatchQuery.SELECT_MATCH_BY_ID, params, new MatchDetailMapper());
    }

    @Override
    public void deleteUpCommingMatchces() throws ResourceUnavailableException {
        Map params = new HashMap();
        params.put("currentTimeStamp", LocalDateTime.now());
        int update = jdbcTemplate.update(MatchQuery.DELETE_UPCOMING_MATCHES, params);
        if(update == 0) throw new ResourceUnavailableException("Could not locate the resource");
    }

    @Override
    public List<MatchDetail> getAllWinnerList() {
        return jdbcTemplate.query(MatchQuery.ALL_WINNERS, new MatchDetailMapper());
    }

    @Override
    public void updateMatchDetail(Long id, MatchDetail p) {

        Map params = new HashMap();
        params.put("mawayteamname", p.getAwayTeamName());
        params.put("mhometeamname", p.getHomeTeamName());
        params.put("mhometeamscore", p.getHomeTeamScore());
        params.put("mawayteamscore", p.getAwayTeamScore());
        params.put("mmatchstarttime", p.getMatchStartTime());
        params.put("mid", p.getId());
        jdbcTemplate.update(MatchQuery.UPDATE_MATCH_DETAIL, params);
    }

    @Override
    public List<MatchDetail> getMatchesByTeamId(Long id) {
        Map params = new HashMap();
        params.put("id", id);
        return jdbcTemplate.query(MatchQuery.MATCHES_BY_TEAM_ID, params, new MatchDetailMapper());
    }

    @Override
    public List<MatchDetail> getUpcomingMatchesByTeamId(Long id) {
        Map params = new HashMap();
        params.put("id", id);
        return jdbcTemplate.query(MatchQuery.UPCOMING_MATCHES_BY_TEAM_ID, params, new MatchDetailMapper());
    }
}
