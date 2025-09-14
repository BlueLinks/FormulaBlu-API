package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "*")
public class DriverController {
    
    @Autowired
    private DriverService driverService;
    
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        return driver.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nationality/{nationality}")
    public List<Driver> getDriversByNationality(@PathVariable String nationality) {
        return driverService.getDriversByNationality(nationality);
    }
    
    @GetMapping("/team/{teamId}")
    public List<Driver> getDriversByTeam(@PathVariable Long teamId) {
        return driverService.getDriversByTeam(teamId);
    }
    
    @GetMapping("/search")
    public List<Driver> searchDriversByName(@RequestParam String name) {
        return driverService.searchDriversByName(name);
    }
    
    @PostMapping
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        if (driverService.getDriverById(id).isPresent()) {
            driver.setId(id);
            return ResponseEntity.ok(driverService.saveDriver(driver));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        if (driverService.getDriverById(id).isPresent()) {
            driverService.deleteDriver(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
