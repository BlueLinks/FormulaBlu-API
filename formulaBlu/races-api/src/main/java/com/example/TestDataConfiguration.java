package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@Profile("test")
public class TestDataConfiguration {

    // We need access to our repo to save the races
    private final RaceRepository raceRepository;


    public TestDataConfiguration(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Bean
    public CommandLineRunner loadData(RaceRepository raceRepository) {
        return args -> {
            System.out.println("Loading test data into Race...");

            // 2025 Season
            Race race1 = new Race("Grand Prix of Monaco", 2025, 8, LocalDate.parse("2025-05-30"));
            raceRepository.save(race1);

            Race race2 = new Race("British Grand Prix", 2025, 12, LocalDate.parse("2025-07-05"));
            raceRepository.save(race2);

            // 2024 Season
            Race race3 = new Race("Bahrain Grand Prix", 2024, 1, LocalDate.parse("2024-03-02"));
            raceRepository.save(race3);

            Race race4 = new Race("Saudi Arabian Grand Prix", 2024, 2, LocalDate.parse("2024-03-09"));
            raceRepository.save(race4);

            Race race5 = new Race("Australian Grand Prix", 2024, 3, LocalDate.parse("2024-03-24"));
            raceRepository.save(race5);

            Race race6 = new Race("Japanese Grand Prix", 2024, 4, LocalDate.parse("2024-04-07"));
            raceRepository.save(race6);

            Race race7 = new Race("Miami Grand Prix", 2024, 5, LocalDate.parse("2024-05-05"));
            raceRepository.save(race7);

            Race race8 = new Race("Spanish Grand Prix", 2024, 6, LocalDate.parse("2024-06-23"));
            raceRepository.save(race8);

            Race race9 = new Race("British Grand Prix", 2024, 7, LocalDate.parse("2024-07-07"));
            raceRepository.save(race9);

            Race race10 = new Race("Belgian Grand Prix", 2024, 8, LocalDate.parse("2024-07-28"));
            raceRepository.save(race10);

            Race race11 = new Race("Italian Grand Prix", 2024, 9, LocalDate.parse("2024-09-01"));
            raceRepository.save(race11);

            Race race12 = new Race("Singapore Grand Prix", 2024, 10, LocalDate.parse("2024-09-22"));
            raceRepository.save(race12);

            Race race13 = new Race("United States Grand Prix", 2024, 11, LocalDate.parse("2024-10-20"));
            raceRepository.save(race13);

            Race race14 = new Race("Las Vegas Grand Prix", 2024, 12, LocalDate.parse("2024-11-23"));
            raceRepository.save(race14);

            Race race15 = new Race("Abu Dhabi Grand Prix", 2024, 13, LocalDate.parse("2024-12-08"));
            raceRepository.save(race15);

            // 2023 Season
            Race race16 = new Race("Bahrain Grand Prix", 2023, 1, LocalDate.parse("2023-03-05"));
            raceRepository.save(race16);

            Race race17 = new Race("Saudi Arabian Grand Prix", 2023, 2, LocalDate.parse("2023-03-19"));
            raceRepository.save(race17);

            Race race18 = new Race("Australian Grand Prix", 2023, 3, LocalDate.parse("2023-04-02"));
            raceRepository.save(race18);

            Race race19 = new Race("Azerbaijan Grand Prix", 2023, 4, LocalDate.parse("2023-04-30"));
            raceRepository.save(race19);

            Race race20 = new Race("Miami Grand Prix", 2023, 5, LocalDate.parse("2023-05-07"));
            raceRepository.save(race20);

            Race race21 = new Race("Spanish Grand Prix", 2023, 6, LocalDate.parse("2023-06-04"));
            raceRepository.save(race21);

            Race race22 = new Race("British Grand Prix", 2023, 7, LocalDate.parse("2023-07-09"));
            raceRepository.save(race22);

            Race race23 = new Race("Hungarian Grand Prix", 2023, 8, LocalDate.parse("2023-07-23"));
            raceRepository.save(race23);

            Race race24 = new Race("Belgian Grand Prix", 2023, 9, LocalDate.parse("2023-07-30"));
            raceRepository.save(race24);

            Race race25 = new Race("Italian Grand Prix", 2023, 10, LocalDate.parse("2023-09-03"));
            raceRepository.save(race25);

            Race race26 = new Race("Singapore Grand Prix", 2023, 11, LocalDate.parse("2023-09-17"));
            raceRepository.save(race26);

            Race race27 = new Race("United States Grand Prix", 2023, 12, LocalDate.parse("2023-10-22"));
            raceRepository.save(race27);

            Race race28 = new Race("Las Vegas Grand Prix", 2023, 13, LocalDate.parse("2023-11-19"));
            raceRepository.save(race28);

            Race race29 = new Race("Abu Dhabi Grand Prix", 2023, 14, LocalDate.parse("2023-11-26"));
            raceRepository.save(race29);
        };
    }
}
