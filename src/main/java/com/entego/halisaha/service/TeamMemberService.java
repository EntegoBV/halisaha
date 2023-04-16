package com.entego.halisaha.service;

import com.entego.halisaha.entity.TeamMember;

import java.util.List;

public interface TeamMemberService {
    TeamMember createTeamMember(TeamMember teamMember);
    TeamMember getTeamMember(Long id);
    List<TeamMember> getAllTeamMembers();
    List<TeamMember> getTeamMembersByTeam(Long teamId);
    TeamMember updateTeamMember(TeamMember teamMember);
    void deleteTeamMember(Long id);
}
