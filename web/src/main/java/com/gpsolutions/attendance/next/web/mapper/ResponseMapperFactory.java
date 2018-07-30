package com.gpsolutions.attendance.next.web.mapper;

import com.gpsolutions.attendance.next.model.FloorAttendance;
import com.gpsolutions.attendance.next.web.dto.BaseDayResponse;
import com.gpsolutions.attendance.next.web.dto.BaseMonthResponse;
import com.gpsolutions.attendance.next.web.dto.DayResponse;
import com.gpsolutions.attendance.next.web.util.DateTimeUtil;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseMapperFactory {

    public static DayResponseMapper baseDayResponseMapper() {
        return report -> {
            final Duration total = report.getFloorAttendances().stream()
                    .map(FloorAttendance::getDuration)
                    .reduce(Duration.ZERO, Duration::plus);
            final Map<String, String> perFloor = report.getFloorAttendances().stream()
                    .collect(Collectors.toMap(
                            fa -> fa.getFloor().name(),
                            fa -> DateTimeUtil.durationToString(fa.getDuration())));
            return new BaseDayResponse(report.getDate(), DateTimeUtil.durationToString(total), perFloor);
        };
    }

    public static MonthResponseMapper baseMonthResponseMapper() {
        return report -> {
            final Duration workDay = Duration.ofHours(8);

            final List<DayResponse> days = report.getDailyReports().stream()
                    .filter(r -> !r.getFloorAttendances().isEmpty())
                    .map(baseDayResponseMapper()::map)
                    .collect(Collectors.toList());
            final Duration total = report.getDailyReports().stream()
                    .filter(r -> !r.getFloorAttendances().isEmpty())
                    .flatMap(dr -> dr.getFloorAttendances().stream())
                    .map(FloorAttendance::getDuration)
                    .reduce(Duration::plus).orElse(Duration.ZERO);
            final Duration difference = report.getDailyReports().stream()
                    .filter(r -> !r.getFloorAttendances().isEmpty())
                    .map(dr -> dr.getFloorAttendances()
                            .stream()
                            .map(FloorAttendance::getDuration)
                            .reduce(Duration::plus).orElse(Duration.ZERO)
                    )
                    .map(d -> d.minus(workDay))
                    .reduce(Duration::plus)
                    .orElse(Duration.ZERO);
            return new BaseMonthResponse(
                    days,
                    DateTimeUtil.durationToString(difference),
                    DateTimeUtil.durationToString(total));
        };
    }

}
