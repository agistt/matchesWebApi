package com.example.matchesapp.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "match_odds")
public class MatchOdds extends BaseModel {

    @NonNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @NonNull
    @Column(name = "specifier")
    private String specifier;

    @NonNull
    @Column(name = "odd")
    private BigDecimal odd;

}
