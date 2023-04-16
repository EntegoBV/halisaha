package com.entego.halisaha.controller;

import com.entego.halisaha.entity.MatchEvent;
import com.entego.halisaha.service.MatchEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-events")
public class MatchEventController {

    @Autowired
    private MatchEventService matchEventService;

    @PostMapping
    public ResponseEntity<MatchEvent> createMatchEvent(@RequestBody MatchEvent matchEvent) {
        return ResponseEntity.ok(matchEventService.createMatchEvent(matchEvent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchEvent> getMatchEvent(@PathVariable Long id) {
        return ResponseEntity.ok(matchEventService.getMatchEvent(id));
    }

    @GetMapping
    public ResponseEntity<List<MatchEvent>> getAllMatchEvents() {
        return ResponseEntity.ok(matchEventService.getAllMatchEvents());
    }

    @GetMapping("/by-match/{matchId}")
    public ResponseEntity<List<MatchEvent>> getMatchEventsByMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchEventService.getMatchEventsByMatch(matchId));
    }

    @PutMapping
    public ResponseEntity<MatchEvent> updateMatchEvent(@RequestBody MatchEvent matchEvent) {
        return ResponseEntity.ok(matchEventService.updateMatchEvent(matchEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchEvent(@PathVariable Long id) {
        matchEventService.deleteMatchEvent(id);
        return ResponseEntity.ok().build();
    }
}
