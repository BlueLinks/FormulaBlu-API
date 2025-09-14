package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    // Custom query methods can be added here

    List<Race> findByCircuit(String circuit);

    List<Race> findByYear(Integer year);

    Optional<Race> findByYearAndRound(Integer year, Integer round);
}
