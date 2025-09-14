package com.example.results.client;

import com.example.results.dto.RaceWeekend;
import com.example.results.dto.Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "events-api", url = "http://events-api-service")
public interface EventsApiClient {

    @GetMapping("/api/events/race-weekend/{weekendId}")
    RaceWeekend getRaceWeekend(@PathVariable("weekendId") Long weekendId);

    @GetMapping("/api/events/race/{raceId}/sessions")
    List<Session> getRaceSessions(@PathVariable("raceId") Long raceId);

    @GetMapping("/api/events/session/{sessionId}")
    Session getSession(@PathVariable("sessionId") Long sessionId);
}
