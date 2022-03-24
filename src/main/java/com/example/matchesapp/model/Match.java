package com.example.matchesapp.model;

import com.example.matchesapp.util.Sport;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "match")
public class Match extends BaseModel {

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "match_date")
    private LocalDate match_date;

    @NonNull
    @Column(name = "match_time")
    private Time match_time;

    @NonNull
    @Column(name = "team_a")
    private String team_a;

    @NonNull
    @Column(name = "team_b")
    private String team_b;

    @NonNull
    @Column(name = "sport")
    private Sport sport;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match", cascade = CascadeType.REMOVE)
    List<MatchOdds> matchOdds = new ArrayList<>();
}
