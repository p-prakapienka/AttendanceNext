package com.gpsolutions.attendance.next.web.util;

import java.time.Duration;

public class DateTimeUtil {

    public static String durationToString(final Duration duration) {
        final long hours = duration.toHours();
        final long minutes = duration.minusHours(hours).toMinutes();
        final long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, Math.abs(minutes), Math.abs(seconds));
    }
}
