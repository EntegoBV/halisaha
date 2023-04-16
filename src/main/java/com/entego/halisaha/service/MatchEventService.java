package com.entego.halisaha.service;

import com.entego.halisaha.entity.MatchEvent;

import java.util.List;

public interface MatchEventService {
    MatchEvent createMatchEvent(MatchEvent matchEvent);
    MatchEvent getMatchEvent(Long id);
    List<MatchEvent> getAllMatchEvents();
    List<MatchEvent> getMatchEventsByMatch(Long matchId);
    MatchEvent updateMatchEvent(MatchEvent matchEvent);
    void deleteMatchEvent(Long id);
}
