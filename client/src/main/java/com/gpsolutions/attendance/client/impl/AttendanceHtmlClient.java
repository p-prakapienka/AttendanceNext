package com.gpsolutions.attendance.client.impl;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.config.DynamicClientConfiguration;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.client.dto.AttendanceDayResponse;
import com.gpsolutions.attendance.client.dto.AttendanceUserListResponse;
import com.gpsolutions.attendance.client.enumeration.Mode;
import com.gpsolutions.attendance.client.converter.AttendanceResponseMessageConverter;
import com.gpsolutions.attendance.client.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

public class AttendanceHtmlClient implements AttendanceClient {

    private static final String DATE_KEY = "calendar";
    private static final String USER_KEY = "user";

    private static final String AUTH_HEADER = "Authorization";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DynamicClientConfiguration configuration;

    @Override
    public AttendanceDayResponse getDayResponse(final AttendanceRequest request) {
        final ResponseEntity<String> response = restTemplate
                .postForEntity(configuration.getUrl(), createEntity(request), String.class);

        return AttendanceResponseMessageConverter.convert(request, response.getBody());
    }

    @Override
    public AttendanceUserListResponse getUsers() {
        final ResponseEntity<String> response = restTemplate
                .exchange(configuration.getUrl(), HttpMethod.GET, createEntity(), String.class);

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
        final String authHeader = configuration.getMode() == Mode.LOCAL
                ? AuthUtil.fromRequestHeader(AUTH_HEADER)
                : AuthUtil.basic("prokopenko", "pieradus1m");
        return new HttpHeaders() {{
            set(AUTH_HEADER, authHeader);
            setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }};
    }

}
