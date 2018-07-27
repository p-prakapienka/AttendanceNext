package com.gpsolutions.attendance.next.util;

import com.gpsolutions.attendance.client.dto.AttendanceDayResponse;
import com.gpsolutions.attendance.client.dto.Period;
import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.Floor;
import com.gpsolutions.attendance.next.model.FloorAttendance;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ReportConverter {

    public static DailyReport convert(final AttendanceDayResponse dayResponse) {
        final DailyReport report = new DailyReport();

        report.setDate(dayResponse.getRequest().getDate());

        List<FloorAttendance> attendances = toFloorAttendances(dayResponse.getPeriods());
        report.setFloorAttendances(attendances);

        return report;
    }

    private static List<FloorAttendance> toFloorAttendances(final List<Period> periods) {
        return periods
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Period::getFloor,
                                Collectors.summingLong(p -> Duration.between(p.getTimeIn(), p.getTimeOut())
                                        .getSeconds())
                        )
                )
                .entrySet()
                .stream()
                .map(entry -> {
                    final FloorAttendance fa = new FloorAttendance();
                    fa.setFloor(Floor.fromValue(entry.getKey()));
                    fa.setDuration(Duration.ofSeconds(entry.getValue()));
                    return fa;
                })
                .collect(Collectors.toList());
    }

}
