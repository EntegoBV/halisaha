package com.entego.halisaha.service;

import com.entego.halisaha.entity.Stadium;

import java.util.List;
public interface StadiumService {
    List<Stadium> getAllStadiums();
    Stadium getStadiumById(Long id);
    Stadium createStadium(Stadium stadium);
    Stadium updateStadium(Stadium stadium);
    void deleteStadium(Long id);
}