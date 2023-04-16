package com.entego.halisaha.integration;

import com.entego.halisaha.entity.League;
import com.entego.halisaha.entity.Team;
import com.entego.halisaha.repository.LeagueRepository;
import com.entego.halisaha.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Test
    public void testCreateAndGetTeam() {
        // create a new league
        League league = new League();
        league.setName("League A");
        league = leagueRepository.save(league);

        // create a new team
        Team team = new Team();
        team.setName("Team A");
        team.setLeague(league);

        // save the team to the database
        teamRepository.save(team);

        // make a GET request to retrieve the team
        ResponseEntity<Team> response = restTemplate.getForEntity(
                "/api/teams/{id}", Team.class, team.getId());

        // verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Team A", response.getBody().getName());
        assertEquals(league.getId(), response.getBody().getLeague().getId());
    }
}