package com.example.matchesapp.repository;

import com.example.matchesapp.util.Sport;
import com.example.matchesapp.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findBySport(Sport sport);
}
