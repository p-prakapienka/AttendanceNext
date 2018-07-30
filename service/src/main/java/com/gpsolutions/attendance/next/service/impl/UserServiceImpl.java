package com.gpsolutions.attendance.next.service.impl;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.next.model.User;
import com.gpsolutions.attendance.next.service.UserService;
import com.gpsolutions.ldap.LdapService;
import com.gpsolutions.ldap.function.LdapPersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ldapUserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final LdapPersonMapper<User> mapper = (lp) -> {
        final User user = new User();
        user.setUid(lp.getUid());
        user.setEmail(lp.getEmail());
        return user;
    };

    @Autowired
    private AttendanceClient attendanceClient;

    @Autowired
    private LdapService<User> ldapService;

    @Override
    public List<String> getAttendanceUsernames() {
        return attendanceClient.getUsers().getUsers();
    }

    @Override
    public User updateAttendanceName(final User user, final String username) {
        user.setAttendanceName(username);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(final String ldapUid) throws UsernameNotFoundException {
        final User user = ldapService.getByUid(ldapUid, mapper);
        findAndSetAttendanceName(user);
        return user;
    }

    private void findAndSetAttendanceName(final User user) {
        Optional<String> name = getAttendanceUsernames()
                .stream()
                .map(String::toLowerCase)
                .filter(user.getUid().toLowerCase()::contains)
                .findFirst();
        user.setAttendanceName(name.orElse(null));
    }

}
