package com.gpsolutions.attendance.client.util;

import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.client.dto.AttendanceResponse;
import com.gpsolutions.attendance.client.dto.Floor;
import com.gpsolutions.attendance.client.dto.Period;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceResponseMessageConverter {

    public static AttendanceResponse convert(final AttendanceRequest request, final String responseBody) {
        final AttendanceResponse response = new AttendanceResponse();
        response.setRequest(request);

        response.setPeriods(readPeriods(responseBody));

        return response;
    }

    private static List<Period> readPeriods(final String responseBody) {
        Document document = Jsoup.parseBodyFragment(responseBody);
        Elements elements = document.getElementsByTag("tr");

        return elements.stream()
                .filter(e -> !e.child(0).tagName().equals("th"))
                .filter(e -> !e.hasClass("total"))
                .filter(e -> e.child(1).hasText())
                .map(e -> {
                    Period period = new Period();
                    period.setTimeIn(LocalTime.parse(e.child(1).text()));
                    period.setTimeOut(LocalTime.parse(e.child(2).text()));
                    period.setFloor(Floor.fromValue(e.child(4).text()));
                    return period;
                })
                .collect(Collectors.toList());
    }

}
