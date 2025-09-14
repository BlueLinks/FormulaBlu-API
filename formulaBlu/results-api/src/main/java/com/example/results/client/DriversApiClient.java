package com.example.results.client;

import com.example.results.dto.Driver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "drivers-api", url = "http://drivers-api-service")
public interface DriversApiClient {

    @GetMapping("/api/drivers/{id}")
    Driver getDriver(@PathVariable("id") Long id);

    @GetMapping("/api/drivers/{id}/career-stats")
    DriverStats getDriverStats(@PathVariable("id") Long id);
}
