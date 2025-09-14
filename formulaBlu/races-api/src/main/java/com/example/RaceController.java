package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/race")
public class RaceController {

    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    // Get all races
    @GetMapping
    public List<Race> getAllRaces() {
        return raceService.getAllRaces();
    }

    // Get race by ID
    @GetMapping("/{id}")
    public Race getRaceById(@PathVariable Long id) {
        return raceService.getRaceById(id);
    }

    // Create a new race
    @PostMapping
    public Race createRace(@RequestBody Race race) {
        return raceService.createRace(race);
    }

    // Get races by circuit name
    @GetMapping("/circuit/{circuit}")
    public List<Race> getRacesByCircuit(@PathVariable String circuit) {
        return raceService.getRacesByCircuit(circuit);
    }

    @GetMapping("/year/{year}")
    public List<Race> getSeason(@PathVariable Integer year){
        return raceService.getSeason(year);
    }
}