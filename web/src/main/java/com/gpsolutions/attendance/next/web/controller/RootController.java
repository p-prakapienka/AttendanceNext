package com.gpsolutions.attendance.next.web.controller;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.client.dto.AttendanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class RootController {

    @Autowired
    private AttendanceClient client;

    @GetMapping
    @ResponseBody
    public AttendanceResponse test(final Authentication authentication) {
        return client.getForDate(new AttendanceRequest(){{
            setUsername("prokopen");
            setDate(LocalDate.now());
        }});
    }

}
