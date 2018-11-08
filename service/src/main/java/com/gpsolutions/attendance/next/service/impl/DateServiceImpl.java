package com.gpsolutions.attendance.next.service.impl;

import com.gpsolutions.attendance.next.service.DateService;
import com.gpsolutions.attendance.next.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Service
public class DateServiceImpl implements DateService {

    private static final Duration REGULAR_WORKDAY = Duration.ofHours(8L);

    private final Map<LocalDate, Duration> irregularDates = new HashMap<LocalDate, Duration>() {{
        put(LocalDate.of(2018, Month.NOVEMBER, 6), Duration.ofHours(7L));
        put(LocalDate.of(2018, Month.NOVEMBER, 7), Duration.ZERO);
    }};

    @Override
    public Duration getTargetHours(LocalDate date) {
        if (irregularDates.containsKey(date)) {
            return irregularDates.get(date);
        }
        return DateTimeUtil.isWeekDay(date) ? REGULAR_WORKDAY : Duration.ZERO;
    }

}
