package com.gpsolutions.attendance.client;

import com.gpsolutions.attendance.client.dto.AttendanceDayResponse;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.client.dto.AttendanceUserListResponse;

public interface AttendanceClient {

    AttendanceDayResponse getDayResponse(final AttendanceRequest request);

    AttendanceUserListResponse getUsers();

}
