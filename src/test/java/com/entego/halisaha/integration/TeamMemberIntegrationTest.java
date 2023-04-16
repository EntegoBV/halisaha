package com.entego.halisaha.integration;

import com.entego.halisaha.entity.Team;
import com.entego.halisaha.entity.TeamMember;
import com.entego.halisaha.repository.TeamMemberRepository;
import com.entego.halisaha.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamMemberIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testCreateAndGetTeamMember() {
        // create a new team
        Team team = new Team();
        team.setName("Team A");
        team = teamRepository.save(team);

        // create a new team member
        TeamMember teamMember = new TeamMember();
        teamMember.setName("John Doe");
        teamMember.setPosition("Forward");
        teamMember.setTeam(new HashSet<>(Collections.singletonList(team)));

        // save the team member to the database
        teamMemberRepository.save(teamMember);

        // make a GET request to retrieve the team member
        ResponseEntity<TeamMember> response = restTemplate.getForEntity(
                "/api/team-member/{id}", TeamMember.class, teamMember.getId());

        // verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
        assertEquals("Forward", response.getBody().getPosition());
        Team finalTeam = team;
        assertTrue(response.getBody().getTeam().stream().anyMatch(t -> t.getId().equals(finalTeam.getId())));
    }
}
