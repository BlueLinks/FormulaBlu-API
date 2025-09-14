package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    
    @Autowired
    private DriverRepository driverRepository;
    
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }
    
    public List<Driver> getDriversByNationality(String nationality) {
        return driverRepository.findByNationality(nationality);
    }
    
    public List<Driver> getDriversByTeam(Long teamId) {
        return driverRepository.findByTeamId(teamId);
    }
    
    public List<Driver> searchDriversByName(String name) {
        return driverRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }
    
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
