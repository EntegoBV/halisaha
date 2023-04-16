package com.entego.halisaha.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, scope=MatchEvent.class)
public class MatchEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "team_member_id")
    private TeamMember teamMember;

    @ManyToOne
    @JoinColumn(name = "matches_id")
    private Match match;

    public enum EventType {
        GOAL, YELLOW_CARD, RED_CARD, SUBSTITUTION_IN, SUBSTITUTION_OUT
    }
}

