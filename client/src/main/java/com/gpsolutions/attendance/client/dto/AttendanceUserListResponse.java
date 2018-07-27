package com.gpsolutions.attendance.client.dto;

import java.io.Serializable;
import java.util.List;

public class AttendanceUserListResponse implements Serializable {

    private List<String> users;

    public AttendanceUserListResponse() {
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

}
