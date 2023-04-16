package com.entego.halisaha.integration;



import com.entego.halisaha.entity.*;
import com.entego.halisaha.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchEventIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MatchEventRepository matchEventRepository;

    @Autowired
    private TeamMemberRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Test
    public void testCreateAndGetMatchEvent() {
        // create a new match event
        TeamMember teamMember = TeamMember.builder().name("Emre").build();

        teamMember = playerRepository.save(teamMember);

        MatchEvent matchEvent = new MatchEvent();
        matchEvent.setEventType(MatchEvent.EventType.GOAL);
        matchEvent.setTeamMember(teamMember);

        // save the match event to the database
        matchEventRepository.save(matchEvent);

        // make a GET request to retrieve the match event
        ResponseEntity<MatchEvent> response = restTemplate.getForEntity(
                "/api/match-events/{id}", MatchEvent.class, matchEvent.getId());
        // verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(MatchEvent.EventType.GOAL, response.getBody().getEventType());
        assertEquals(teamMember.getId(), response.getBody().getTeamMember().getId());
    }
}