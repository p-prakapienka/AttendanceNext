package com.gpsolutions.attendance.next.web.mapper;

import com.gpsolutions.attendance.next.model.MonthlyReport;
import com.gpsolutions.attendance.next.web.dto.MonthResponse;

@FunctionalInterface
public interface MonthResponseMapper {

    MonthResponse map(final MonthlyReport report);

}
