package com.gpsolutions.attendance.client.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.Charset;
import java.util.Base64;

public class AuthUtil {

    private static final String PREFIX = "Basic ";
    private static final String DELIMITER = ":";

    private static final Charset ASCII = Charset.forName("US-ASCII");

    public static String basic(final String username, final String password) {
        final String credentials = username + DELIMITER + password;

        byte[] bytes = Base64.getEncoder().encode(credentials.getBytes(ASCII));

        return PREFIX + new String(bytes);
    }

    public static String fromRequestHeader(final String header) {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getHeader(header);
    }

}
