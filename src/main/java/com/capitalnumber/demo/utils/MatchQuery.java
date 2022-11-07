package com.capitalnumber.demo.utils;

public final class MatchQuery {

    private MatchQuery() {

    }
    public static final String SAVE_MATCH = "INSERT INTO public.match_details(" +
            "m_match_start_time, m_home_team_name, m_away_team_name, m_home_team_score, m_away_team_score)" +
            "VALUES(:startTime, :homeTeamName, :awayTeamName, :homeTeamScore, :awayTeamScore)";

    public static final String SELECT_MATCH_BY_ID = "SELECT * FROM public.match_details where m_id = :id";
    public static final String UPDATE_MATCH_DETAIL = "UPDATE public.match_details " +
            "SET  m_match_start_time= :mmatchstarttime, m_home_team_name= :mhometeamname, " +
            "m_away_team_name= :mawayteamname, m_home_team_score= :mhometeamscore, m_away_team_score= :mawayteamscore " +
            "WHERE m_id = :mid";
    public static final String DELETE_UPCOMING_MATCHES = "DELETE FROM public.match_details WHERE m_match_start_time > :currentTimeStamp";
    public static final String ALL_WINNERS = "SELECT * FROM public.match_details WHERE m_home_team_score is not null and m_away_team_score is not null and m_home_team_score <> m_away_team_score";

    public static final String MATCHES_BY_TEAM_ID = "select * from match_details md inner join team t" +
            " on md.m_home_team_name = t.t_name" +
            " where t.t_id = :id";

    public static final String UPCOMING_MATCHES_BY_TEAM_ID = "select * from match_details md inner join team t" +
            " on md.m_home_team_name = t.t_name" +
            "  where t.t_id = :id" +
            " and m_match_start_time > CURRENT_TIMESTAMP";
}
