package com.gpsolutions.attendance.next.service.impl;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.next.model.User;
import com.gpsolutions.attendance.next.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AttendanceClient attendanceClient;

    @Override
    public List<String> getAttendanceUsernames() {
        return attendanceClient.getUsers().getUsers();
    }

    @Override
    public User updateAttendanceUsername(User user, String username) {
        return null;
    }

}
