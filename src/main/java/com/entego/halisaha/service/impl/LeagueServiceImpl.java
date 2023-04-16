package com.entego.halisaha.service.impl;

import com.entego.halisaha.entity.League;
import com.entego.halisaha.exception.NotFoundException;
import com.entego.halisaha.repository.LeagueRepository;
import com.entego.halisaha.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    public League createLeague(League league) {
        return leagueRepository.save(league);
    }

    @Override
    public League getLeague(Long id) {
        return leagueRepository.findById(id).orElseThrow(() -> new NotFoundException("League not found with id: " + id));
    }

    @Override
    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public League updateLeague(League league) {
        return leagueRepository.save(league);
    }

    public void deleteLeague(Long id) {
        Optional<League> league = leagueRepository.findById(id);
        if (league.isPresent()) {
            leagueRepository.delete(league.get());
        } else {
            throw new NotFoundException("League not found with id: " + id);
        }
    }
}