package com.gpsolutions.attendance.next.web.controller;

import com.gpsolutions.attendance.next.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    @ResponseBody
    public List<String> users() {
        return userService.getAttendanceUsernames();
    }

}
