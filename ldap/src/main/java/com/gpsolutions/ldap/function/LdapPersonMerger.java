package com.gpsolutions.ldap.function;

import com.gpsolutions.ldap.LdapPerson;

@FunctionalInterface
public interface LdapPersonMerger<T> {

    void merge(final LdapPerson ldapPerson, final T t);

}
