package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "*")
public class TeamController {
    
    @Autowired
    private TeamService teamService;
    
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Optional<Team> team = teamService.getTeamById(id);
        return team.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nationality/{nationality}")
    public List<Team> getTeamsByNationality(@PathVariable String nationality) {
        return teamService.getTeamsByNationality(nationality);
    }
    
    @GetMapping("/search")
    public List<Team> searchTeamsByName(@RequestParam String name) {
        return teamService.searchTeamsByName(name);
    }
    
    @GetMapping("/founded/{year}")
    public List<Team> getTeamsByFoundedYear(@PathVariable Integer year) {
        return teamService.getTeamsByFoundedYear(year);
    }
    
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        if (teamService.getTeamById(id).isPresent()) {
            team.setId(id);
            return ResponseEntity.ok(teamService.saveTeam(team));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        if (teamService.getTeamById(id).isPresent()) {
            teamService.deleteTeam(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
