package com.entego.halisaha.service;

import com.entego.halisaha.entity.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);
    Team getTeam(Long id);
    List<Team> getAllTeams();
    List<Team> getTeamsByLeague(Long leagueId);
    Team updateTeam(Team team);
    void deleteTeam(Long id);
}