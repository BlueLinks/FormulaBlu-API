package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    // Get all races
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    // Get race by ID
    public Race getRaceById(Long id) {
        Optional<Race> race = raceRepository.findById(id);
        return race.orElse(null);
    }

    // Create a new race
    public Race createRace(Race race) {
        if (raceRepository.findByYearAndRound(race.getYear(), race.getRound()).isEmpty()){
            return raceRepository.save(race);
        }
        throw new IllegalStateException("Race already exists");
    }

    // Get races by circuit name (example custom query)
    public List<Race> getRacesByCircuit(String circuit) {
        return raceRepository.findByCircuit(circuit);
    }

    public void addRace(Race race) {
        raceRepository.save(race);
    }

    public List<Race> getSeason(Integer year) {
        return raceRepository.findByYear(year);
    }
}