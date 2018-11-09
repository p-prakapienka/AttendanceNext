package com.gpsolutions.attendance.next.repository;

import com.gpsolutions.attendance.next.model.DailyReport;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class DailyReportRepository {

    private final Map<String, Set<DailyReport>> userDailyReports = new ConcurrentHashMap<>();

    public void save(final String uid, final DailyReport dailyReport) {
        userDailyReports.computeIfAbsent(uid, k -> new HashSet<>());
        userDailyReports.computeIfPresent(uid, (key, set) -> {
            set.remove(dailyReport);
            set.add(dailyReport);
            return set;
        });
    }

    public List<DailyReport> getByMonth(final String uid, final Month month) {
        Set<DailyReport> reports = userDailyReports.get(uid);
        return reports == null ? new ArrayList<>() : reports
                .stream()
                .filter(dr -> month.equals(dr.getDate().getMonth()))
                .sorted(DailyReport::compareTo)
                .collect(Collectors.toList());
    }
}
