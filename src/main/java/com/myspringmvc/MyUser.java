package com.myspringmvc;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by sanek9 on 15.06.17.
 */
public class MyUser extends User {
    protected Long personId;
    public MyUser(Long personId,  String password, Collection<? extends GrantedAuthority> authorities) {
        this(personId.toString(), personId, password, authorities);
    }
    public MyUser(String username,Long personId,  String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.personId = personId;
    }
    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Long getPersonId() {
        return personId;
    }
}
