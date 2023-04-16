package com.entego.halisaha.service.impl;

import com.entego.halisaha.entity.Stadium;
import com.entego.halisaha.exception.NotFoundException;
import com.entego.halisaha.repository.StadiumRepository;
import com.entego.halisaha.service.StadiumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;

    public StadiumServiceImpl(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    @Override
    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }

    @Override
    public Stadium getStadiumById(Long id) {
        return stadiumRepository.findById(id).orElseThrow(() -> new NotFoundException("Stadium not found with id: " + id));
    }

    @Override
    public Stadium createStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    @Override
    public Stadium updateStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    @Override
    public void deleteStadium(Long id) {
        stadiumRepository.deleteById(id);
    }
}