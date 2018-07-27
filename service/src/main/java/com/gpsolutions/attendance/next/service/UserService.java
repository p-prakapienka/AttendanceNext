package com.gpsolutions.attendance.next.service;

import com.gpsolutions.attendance.next.model.User;

import java.util.List;

public interface UserService {

    List<String> getAttendanceUsernames();

    User updateAttendanceName(final User user, final String username);

}
