package com.gpsolutions.attendance.client.converter;

import com.gpsolutions.attendance.client.dto.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceResponseMessageConverter {

    public static AttendanceUserListResponse convert(final String responseBody) {
        final AttendanceUserListResponse response = new AttendanceUserListResponse();

        response.setUsers(readUsers(responseBody));

        return response;
    }

    public static AttendanceDayResponse convert(final AttendanceRequest request, final String responseBody) {
        final AttendanceDayResponse response = new AttendanceDayResponse();
        response.setRequest(request);

        response.setPeriods(readPeriods(responseBody));

        return response;
    }

    private static List<String> readUsers(final String responseBody) {
        Document document = Jsoup.parse(responseBody);
        Element selectUsers = document.getElementById("user");

        return selectUsers.children().stream().map(Element::val).collect(Collectors.toList());
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
                    period.setFloor(e.child(4).text());
                    return period;
                })
                .collect(Collectors.toList());
    }

}
