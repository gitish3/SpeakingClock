package com.clock.time.controllers;

import com.clock.time.services.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/current-time")
@Slf4j
public class CurrentTimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping(value = "/convert")
    @Operation(summary = "Get Current Time in Words", description = "Retrieve the current time in words as per the 24-hour clock format")
    public String getTime() {
        Date currentTime = new Date();
        String res = timeService.convertCurrentTime(currentTime);
        log.info("Converted Current Time: {}  into words: {}", currentTime, res);
        return res;
    }

}
