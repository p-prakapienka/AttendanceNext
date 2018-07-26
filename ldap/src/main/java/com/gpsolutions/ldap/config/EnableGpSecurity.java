package com.gpsolutions.ldap.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({LdapSecurityConfiguration.class, LdapServiceConfiguration.class})
public @interface EnableGpSecurity {
}
