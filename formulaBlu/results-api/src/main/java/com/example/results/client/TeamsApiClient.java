package com.example.results.client;

import com.example.results.dto.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "teams-api", url = "http://teams-api-service")
public interface TeamsApiClient {

    @GetMapping("/api/teams/{id}")
    Team getTeam(@PathVariable("id") Long id);

    @GetMapping("/api/teams/{id}/drivers")
    java.util.List<Driver> getTeamDrivers(@PathVariable("id") Long id);
}
