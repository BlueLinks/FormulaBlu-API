package com.example.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {
    
    @Autowired
    private TrackRepository trackRepository;
    
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }
    
    public Optional<Track> getTrackById(Long id) {
        return trackRepository.findById(id);
    }
    
    public List<Track> getTracksByCountry(String country) {
        return trackRepository.findByCountry(country);
    }
    
    public List<Track> searchTracksByName(String name) {
        return trackRepository.findByNameContainingIgnoreCase(name);
    }
    
    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }
    
    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }
}
