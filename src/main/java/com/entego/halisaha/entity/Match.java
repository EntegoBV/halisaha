package com.entego.halisaha.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "matches")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, scope=Match.class)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private Set<MatchEvent> matchEvents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Stadium stadium;
}