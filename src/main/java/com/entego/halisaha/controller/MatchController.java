package com.entego.halisaha.controller;

import com.entego.halisaha.entity.Match;
import com.entego.halisaha.entity.Stadium;
import com.entego.halisaha.service.MatchService;
import com.entego.halisaha.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private StadiumService stadiumService;
    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match, @RequestParam("stadiumId") Long stadiumId) {
        Stadium stadium = stadiumService.getStadiumById(stadiumId);
        match.setStadium(stadium);
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(match));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatch(id));
    }
    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/by-team/{teamId}")
    public ResponseEntity<List<Match>> getMatchesByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(matchService.getMatchesByTeam(teamId));
    }

    @PutMapping
    public ResponseEntity<Match> updateMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.updateMatch(match));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok().build();
    }
}


