package com.entego.halisaha.integration;


import com.entego.halisaha.entity.Match;
import com.entego.halisaha.entity.Stadium;
import com.entego.halisaha.entity.Team;
import com.entego.halisaha.repository.MatchRepository;
import com.entego.halisaha.repository.StadiumRepository;
import com.entego.halisaha.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Test
    public void testCreateAndGetSoccerMatch() {
        // create a new soccer match
        Team homeTeam = Team.builder().name("Ayasofya FC").build();
        Team awayTeam = Team.builder().name("Diestanbul").build();
        Stadium stadium = Stadium.builder().name("Den Uyt").build();
        LocalDateTime startDate = LocalDateTime.of(2021, Month.DECEMBER,10,1,1);
        LocalDateTime endDate = startDate.plusHours(2);

        homeTeam = teamRepository.save(homeTeam);
        awayTeam = teamRepository.save(awayTeam);
        stadium = stadiumRepository.save(stadium);

        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setStadium(stadium);
        match.setStartDate(startDate);
        match.setEndDate(endDate);

        // save the soccer match to the database
        matchRepository.save(match);

        // make a GET request to retrieve the soccer match
        ResponseEntity<Match> response = restTemplate.getForEntity(
                "/api/matches/{id}", Match.class, match.getId());

        // verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(homeTeam.getId(), response.getBody().getHomeTeam().getId());
        assertEquals(awayTeam.getId(), response.getBody().getAwayTeam().getId());
        assertEquals(stadium.getId(), response.getBody().getStadium().getId());
        assertEquals(startDate, response.getBody().getStartDate());
        assertEquals(endDate, response.getBody().getEndDate());
    }
}