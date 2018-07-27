package com.gpsolutions.attendance.next.model;

import java.io.Serializable;

public enum Floor implements Serializable {

    FLOOR_1("Floor 1"),
    FLOOR_2("Floor 2"),
    FLOOR_3("Floor 3");

    private String value;

    Floor(final String value) {
        this.value = value;
    }

    public static Floor fromValue(final String value) {
        for (Floor floor : Floor.values()) {
            if (floor.value.equals(value)) {
                return floor;
            }
        }
        throw new RuntimeException("Unknown floor value");
    }
}
