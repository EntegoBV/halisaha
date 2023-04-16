package com.entego.halisaha.controller;

import com.entego.halisaha.entity.TeamMember;
import com.entego.halisaha.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-member")
public class TeamMemberController {

    @Autowired
    private TeamMemberService playerService;

    @PostMapping
    public ResponseEntity<TeamMember> createTeamMember(@RequestBody TeamMember teamMember) {
        return ResponseEntity.ok(playerService.createTeamMember(teamMember));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamMember> getTeamMember(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getTeamMember(id));
    }

    @GetMapping
    public ResponseEntity<List<TeamMember>> getAllTeamMembers() {
        return ResponseEntity.ok(playerService.getAllTeamMembers());
    }

    @GetMapping("/by-team/{teamId}")
    public ResponseEntity<List<TeamMember>> getTeamMembersByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(playerService.getTeamMembersByTeam(teamId));
    }

    @PutMapping
    public ResponseEntity<TeamMember> updateTeamMember(@RequestBody TeamMember teamMember) {
        return ResponseEntity.ok(playerService.updateTeamMember(teamMember));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable Long id) {
        playerService.deleteTeamMember(id);
        return ResponseEntity.ok().build();
    }
}