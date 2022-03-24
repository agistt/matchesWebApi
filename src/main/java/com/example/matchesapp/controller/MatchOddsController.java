package com.example.matchesapp.controller;

import com.example.matchesapp.model.Match;
import com.example.matchesapp.model.MatchOdds;
import com.example.matchesapp.repository.MatchOddsRepository;
import com.example.matchesapp.repository.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MatchOddsController {

    final MatchOddsRepository matchOddsRepository;
    final MatchRepository matchRepository;

    public MatchOddsController(MatchOddsRepository matchOddsRepository, MatchRepository matchRepository) {
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
    }

    @PostMapping("/matchOdds")
    public ResponseEntity<MatchOdds> createMatchOdds(@RequestParam(value = "match_id") String match_id,
                                                     @RequestParam(value = "specifier") String specifier,
                                                     @RequestParam(value = "odd") BigDecimal odd) {
        try {
            Optional<Match> matchData = matchRepository.findById(Long.valueOf(match_id));
            if (matchData.isPresent()) {
                MatchOdds _matchOdds = matchOddsRepository.save(new MatchOdds(matchData.get(), specifier, odd));
                return new ResponseEntity<MatchOdds>(_matchOdds, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAllMatchOdds")
    public ResponseEntity<List<MatchOdds>> getAllMatchOdds() {
        try {
            return new ResponseEntity<List<MatchOdds>>(matchOddsRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMatchOdd/{id}")
    public ResponseEntity<MatchOdds> getMatchOddById(@PathVariable("id") long id) {
        Optional<MatchOdds> matchOddsData = matchOddsRepository.findById(id);
        if (matchOddsData.isPresent()) {
            return new ResponseEntity<>(matchOddsData.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateMatchOdds")
    public ResponseEntity<MatchOdds> updateMatchOdd(@RequestParam("id") Long id,
                                                    @RequestParam(value = "specifier") String specifier,
                                                    @RequestParam(value = "odd") BigDecimal odd) {
        Optional<MatchOdds> matchOddData = matchOddsRepository.findById(id);
        if (matchOddData.isPresent()) {
            MatchOdds matchOdd = matchOddData.get();
            matchOdd.setSpecifier(specifier);
            matchOdd.setOdd(odd);
            return new ResponseEntity<>(matchOddsRepository.save(matchOdd), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/matchOdds/{id}")
    public ResponseEntity<HttpStatus> deleteMatchOdd(@PathVariable("id") long id) {
        try {
            matchOddsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

