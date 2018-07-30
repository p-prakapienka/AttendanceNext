package com.gpsolutions.attendance.next.util;

import com.gpsolutions.attendance.client.dto.AttendanceRequest;

import java.time.LocalDate;

public class AttendanceRequestFactory {

    public static AttendanceRequest create(final LocalDate date, final String username) {
        final AttendanceRequest request = new AttendanceRequest();
        request.setDate(date);
        request.setUsername(username);
        return request;
    }

}
