package com.gpsolutions.attendance.next.web.mapper;

import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.web.dto.DayResponse;

@FunctionalInterface
public interface DayResponseMapper {

    DayResponse map(final DailyReport report);

}
