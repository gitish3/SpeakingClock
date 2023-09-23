package com.clock.time.controllers;

import com.clock.time.services.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
@Slf4j
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping(value = "/convert")
    @Operation(summary = "Converting Input time into words", description = "Retrieve the input time in words as per the 24-hour clock format")
    public String getTime(@RequestParam(name = "time") String time) {
        String res = timeService.solve(time);
        log.info("Request Param: {} :: Converted Time response: {}", time, res);
        return res;
    }
}
