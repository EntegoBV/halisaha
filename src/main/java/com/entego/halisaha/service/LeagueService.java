package com.entego.halisaha.service;


import com.entego.halisaha.entity.League;

import java.util.List;

public interface LeagueService {
    League createLeague(League league);
    League getLeague(Long id);
    List<League> getAllLeagues();
    League updateLeague(League league);
    void deleteLeague(Long id);
}