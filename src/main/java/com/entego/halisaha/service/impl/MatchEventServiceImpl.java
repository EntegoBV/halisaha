package com.entego.halisaha.service.impl;

import com.entego.halisaha.entity.MatchEvent;
import com.entego.halisaha.repository.MatchEventRepository;
import com.entego.halisaha.service.MatchEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchEventServiceImpl implements MatchEventService {

    @Autowired
    private MatchEventRepository matchEventRepository;

    @Override
    public MatchEvent createMatchEvent(MatchEvent matchEvent) {
        return matchEventRepository.save(matchEvent);
    }

    @Override
    public MatchEvent getMatchEvent(Long id) {
        return matchEventRepository.findById(id).orElse(null);
    }

    @Override
    public List<MatchEvent> getAllMatchEvents() {
        return matchEventRepository.findAll();
    }

    @Override
    public List<MatchEvent> getMatchEventsByMatch(Long matchId) {
        return matchEventRepository.findByMatchId(matchId);
    }

    @Override
    public MatchEvent updateMatchEvent(MatchEvent matchEvent) {
        return matchEventRepository.save(matchEvent);
    }

    @Override
    public void deleteMatchEvent(Long id) {
        matchEventRepository.deleteById(id);
    }
}