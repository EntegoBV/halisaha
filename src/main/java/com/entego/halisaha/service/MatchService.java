package com.entego.halisaha.service;

import com.entego.halisaha.entity.Match;

import java.util.List;

public interface MatchService {
    Match createMatch(Match match);
    Match getMatch(Long id);
    List<Match> getAllMatches();
    List<Match> getMatchesByTeam(Long teamId);
    Match updateMatch(Match match);
    void deleteMatch(Long id);
}