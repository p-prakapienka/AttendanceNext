package com.gpsolutions.attendance.client.impl;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.client.dto.AttendanceDayResponse;
import com.gpsolutions.attendance.client.dto.AttendanceUserListResponse;
import com.gpsolutions.attendance.client.util.AttendanceResponseMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.format.DateTimeFormatter;

public class AttendanceHtmlClient implements AttendanceClient {

    private static final String DATE_KEY = "calendar";
    private static final String USER_KEY = "user";

    private static final String AUTH_HEADER = "Authorization";

    @Value("${attendance.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AttendanceDayResponse getDayResponse(final AttendanceRequest request) {
        final ResponseEntity<String> response = restTemplate.postForEntity(url, createEntity(request), String.class);

        return AttendanceResponseMessageConverter.convert(request, response.getBody());
    }

    @Override
    public AttendanceUserListResponse getUsers() {
        final ResponseEntity<String> response = restTemplate
                .exchange(url, HttpMethod.GET, createEntity(), String.class);

        return AttendanceResponseMessageConverter.convert(response.getBody());
    }

    private HttpEntity createEntity() {
        return new HttpEntity(createHeaders());
    }

    private HttpEntity<MultiValueMap<String, String>> createEntity(final AttendanceRequest request) {
        return new HttpEntity<>(createBody(request), createHeaders());
    }

    private MultiValueMap<String, String> createBody(final AttendanceRequest request) {
        return new LinkedMultiValueMap<String, String>() {{
            add(DATE_KEY, request.getDate().format(DateTimeFormatter.ISO_DATE));
            add(USER_KEY, request.getUsername());
        }};
    }

    private HttpHeaders createHeaders(){
        final String authHeader = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getHeader(AUTH_HEADER);
        return new HttpHeaders() {{
            set(AUTH_HEADER, authHeader);
            setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }};
    }

}
