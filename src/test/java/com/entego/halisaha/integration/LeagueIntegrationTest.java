package com.entego.halisaha.integration;

import com.entego.halisaha.entity.League;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeagueIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private String baseUrl;
    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/api/leagues";
    }

    @Test
    public void createAndGetLeague() {
        // Create a league
        League league = new League();
        league.setName("Test League");
        ResponseEntity<League> createResponse = restTemplate.postForEntity(baseUrl, league, League.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());

        // Get the created league
        ResponseEntity<League> getResponse = restTemplate.getForEntity(baseUrl + "/" + createResponse.getBody().getId(), League.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Test League", requireNonNull(getResponse.getBody()).getName());
    }
    @Test
    public void updateLeague() {
        // Create a league
        League league = new League();
        league.setName("Test League");
        ResponseEntity<League> createResponse = restTemplate.postForEntity(baseUrl, league, League.class);

        // Update the league
        league.setId(createResponse.getBody().getId());
        league.setName("Updated League");
        restTemplate.put(baseUrl, league);

        // Get the updated league
        ResponseEntity<League> getResponse = restTemplate.getForEntity(baseUrl + "/" + league.getId(), League.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Updated League", requireNonNull(getResponse.getBody()).getName());
    }

    @Test
    public void deleteLeague() {
        // Create a league
        League league = new League();
        league.setName("Test League");
        ResponseEntity<League> createResponse = restTemplate.postForEntity(baseUrl, league, League.class);

        // Delete the league
        restTemplate.delete(baseUrl + "/" + requireNonNull(createResponse.getBody()).getId());

        // Verify that the league is deleted
        ResponseEntity<League> getResponse = restTemplate.getForEntity(baseUrl + "/" + createResponse.getBody().getId(), League.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}