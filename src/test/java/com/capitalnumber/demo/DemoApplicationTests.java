package com.capitalnumber.demo;

import com.capitalnumber.demo.mappers.MatchDetailMapper;
import com.capitalnumber.demo.model.MatchDetail;
import com.capitalnumber.demo.utils.MatchQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetMatchDetail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/match?id=1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	public void testGetAllWinnerList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/match/allwinners")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[2].homeTeamName").value("Southampton FC"));
	}

	@Test
	public void testUpdateMatchDetails() throws Exception {
		String patches = "[" +
				"  {" +
				"    \"op\": \"replace\"," +
				"    \"path\": \"/homeTeamName\"," +
				"    \"value\": \"Aston Villa FC\"" +
				"  }" +
				"]";
		mockMvc.perform(MockMvcRequestBuilders
				.patch("/api/match/4")
						.contentType(MediaType.APPLICATION_JSON)
				.content(patches))
				.andExpect(status().isOk())
				.andExpect(result -> Assert.hasText("resource updated successfully"));

		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/match?id=4")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.homeTeamName").value("Aston Villa FC"));

	}


	@Test
	public void testGetMatchesByTeamId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/match/team/6")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[1].awayTeamName").value("Aston Villa FC"));
	}

	@Test
	public void testGetUpcomingMatchesByTeam() throws Exception {
		//List<MatchDetail> mds = jdbcTemplate.query("select * from match_details", new MatchDetailMapper());
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/match/upcomingmatches?id=1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].homeTeamName").value("FC Chelsea"));
	}
	@Test
	public void testGetTeamDetails() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/team")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", hasSize(8)))
				.andExpect(jsonPath("$[4].name").value("Leeds United"));
	}

	@Test
	public void testSaveMatchDetails() throws Exception {
		MatchDetail md = new MatchDetail();
		md.setMatchStartTime(LocalDateTime.now());
		md.setHomeTeamName("Home Team Name");
		md.setAwayTeamName("Away team name");
		String json = mapper.writeValueAsString(md);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/match")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.homeTeamName").value("Home Team Name"));
	}

}
