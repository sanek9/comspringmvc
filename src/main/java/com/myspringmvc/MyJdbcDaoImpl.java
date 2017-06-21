package com.myspringmvc;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.util.List;

/**
 * Created by sanek9 on 15.06.17.
 */
public class MyJdbcDaoImpl extends JdbcDaoImpl {
    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
        User user = (User) super.createUserDetails(username, userFromUserQuery, combinedAuthorities);
        System.out.println("create user");
        return new MyUser(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, combinedAuthorities);


//        return
    }
}
