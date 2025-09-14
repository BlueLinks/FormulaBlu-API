package com.example.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
@CrossOrigin(origins = "*")
public class TrackController {
    
    @Autowired
    private TrackService trackService;
    
    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long id) {
        Optional<Track> track = trackService.getTrackById(id);
        return track.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/country/{country}")
    public List<Track> getTracksByCountry(@PathVariable String country) {
        return trackService.getTracksByCountry(country);
    }
    
    @GetMapping("/search")
    public List<Track> searchTracksByName(@RequestParam String name) {
        return trackService.searchTracksByName(name);
    }
    
    @PostMapping
    public Track createTrack(@RequestBody Track track) {
        return trackService.saveTrack(track);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable Long id, @RequestBody Track track) {
        if (trackService.getTrackById(id).isPresent()) {
            track.setId(id);
            return ResponseEntity.ok(trackService.saveTrack(track));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long id) {
        if (trackService.getTrackById(id).isPresent()) {
            trackService.deleteTrack(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
