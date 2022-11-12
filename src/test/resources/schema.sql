CREATE TABLE IF NOT EXISTS public.match_details
(
    m_id SERIAL PRIMARY KEY,
    m_match_start_time timestamp,
    m_home_team_name varchar(255) NOT NULL,
    m_away_team_name varchar(255) NOT NULL,
    m_home_team_score integer,
    m_away_team_score integer
);
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name)
VALUES (PARSEDATETIME('22 March 2023 10pm', 'dd MMMM yyyy hha'), 'FC Chelsea', 'Brighton & Hove Albion FC');
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name)
VALUES (PARSEDATETIME('23 March 2023 10pm', 'dd MMMM yyyy hha'), 'Aston Villa FC', 'Leeds United');
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name)
VALUES (PARSEDATETIME('24 March 2023 5pm', 'dd MMMM yyyy ha'), 'Southampton FC', 'Manchester United FC');
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name, m_home_team_score, m_away_team_score)
VALUES (PARSEDATETIME('25 March 2022 11pm', 'dd MMMM yyyy hha'), 'Chelsea FC', 'Aston Villa FC', 0, 3);
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name)
VALUES (PARSEDATETIME('25 March 2023 11pm', 'dd MMMM yyyy hha'), 'Southampton FC', 'Aston Villa FC');
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name, m_home_team_score, m_away_team_score)
VALUES (PARSEDATETIME('29 July 2022 11pm', 'dd MMMM yyyy hha'), 'Southampton FC', 'Aston Villa FC', 0, 0);
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name, m_home_team_score, m_away_team_score)
VALUES (PARSEDATETIME('25 March 2022 11pm', 'dd MMMM yyyy hha'), 'Brighton & Hove Albion FC', 'West Ham United FC', 1, 0);
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name)
VALUES (PARSEDATETIME('29 August 2023 7pm', 'dd MMMM yyyy ha'), 'Brighton & Hove Albion', '');
INSERT INTO public.match_details(
    m_match_start_time, m_home_team_name, m_away_team_name, m_home_team_score, m_away_team_score)
VALUES (PARSEDATETIME('24 April 2022 5pm', 'dd MMMM yyyy ha'), 'Southampton FC', 'Manchester United FC', 2, 0);


CREATE TABLE IF NOT EXISTS public.team
(
    t_id SERIAL PRIMARY KEY,
    t_name varchar(255) unique not null
);

INSERT INTO public.team(t_name) VALUES ('FC Chelsea');
INSERT INTO public.team(t_name) VALUES ('Brighton & Hove Albion FC');
INSERT INTO public.team(t_name) VALUES ('Manchester United FC');
INSERT INTO public.team(t_name) VALUES ('Aston Villa FC');
INSERT INTO public.team(t_name) VALUES ('Leeds United');
INSERT INTO public.team(t_name) VALUES ('Southampton FC');
INSERT INTO public.team(t_name) VALUES ('Chelsea FC');
INSERT INTO public.team(t_name) VALUES ('West Ham United FC');