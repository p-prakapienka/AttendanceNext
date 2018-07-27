package com.gpsolutions.ldap.function;

import com.gpsolutions.ldap.LdapPerson;

@FunctionalInterface
public interface LdapPersonFilter {

    boolean filter(final LdapPerson ldapPerson);

}
