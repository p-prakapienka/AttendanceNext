package com.gpsolutions.attendance.next.service;

import java.time.Duration;
import java.time.LocalDate;

public interface DateService {

    Duration getTargetHours(final LocalDate date);

}
