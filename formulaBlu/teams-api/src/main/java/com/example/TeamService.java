package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }
    
    public List<Team> getTeamsByNationality(String nationality) {
        return teamRepository.findByNationality(nationality);
    }
    
    public List<Team> searchTeamsByName(String name) {
        return teamRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Team> getTeamsByFoundedYear(Integer foundedYear) {
        return teamRepository.findByFoundedYear(foundedYear);
    }
    
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
    
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
