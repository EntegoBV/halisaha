package com.entego.halisaha.service.impl;

import com.entego.halisaha.entity.TeamMember;
import com.entego.halisaha.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements TeamMemberService {

    @Autowired
    private com.entego.halisaha.repository.TeamMemberRepository playerRepository;

    @Override
    public TeamMember createTeamMember(TeamMember teamMember) {
        return playerRepository.save(teamMember);
    }

    @Override
    public TeamMember getTeamMember(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public List<TeamMember> getAllTeamMembers() {
        return playerRepository.findAll();
    }

    @Override
    public List<TeamMember> getTeamMembersByTeam(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    @Override
    public TeamMember updateTeamMember(TeamMember teamMember) {
        return playerRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(Long id) {
        playerRepository.deleteById(id);
    }
}