package com.gpsolutions.ldap.function;

import com.gpsolutions.ldap.LdapPerson;

@FunctionalInterface
public interface LdapPersonMapper<T> {

    T map(final LdapPerson ldapPerson);

}
