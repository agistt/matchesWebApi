package com.example.matchesapp.controller;

import com.example.matchesapp.util.Sport;
import com.example.matchesapp.model.Match;
import com.example.matchesapp.repository.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MatchController {

    final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @PostMapping("/createMatch")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        try {
            return new ResponseEntity<>(matchRepository.save(match), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllMatches")
    public ResponseEntity<List<Match>> getAllMatches() {
        try {
            return new ResponseEntity<List<Match>>(matchRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMatch/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") long id) {
        Optional<Match> matchData = matchRepository.findById(id);
        if (matchData.isPresent()) {
            return new ResponseEntity<>(matchData.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateMatch")
    public ResponseEntity<Match> updateMatch(@RequestBody Match match) {
        Optional<Match> matchData = matchRepository.findById(match.getId());
        if (matchData.isPresent()) {
            return new ResponseEntity<>(matchRepository.save(match), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/match/{id}")
    public ResponseEntity<HttpStatus> deleteMatch(@PathVariable("id") long id) {
        try {
            matchRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matches/{sport}")
    public ResponseEntity<List<Match>> findBySport(@PathVariable("sport") String sport) {
        try {
            List<Match> matches = matchRepository.findBySport(Sport.valueOf(sport));
            if (matches.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(matches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
