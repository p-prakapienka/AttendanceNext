package com.gpsolutions.attendance.next.web.controller;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.client.dto.AttendanceDayResponse;
import com.gpsolutions.attendance.client.dto.AttendanceUserListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class RootController {

    @Autowired
    private AttendanceClient client;

    @GetMapping("day")
    @ResponseBody
    public AttendanceDayResponse day() {
        return client.getDayResponse(new AttendanceRequest(){{
            setUsername("prokopen");
            setDate(LocalDate.now());
        }});
    }

    @GetMapping("users")
    @ResponseBody
    public AttendanceUserListResponse users() {
        return client.getUsers();
    }

}
