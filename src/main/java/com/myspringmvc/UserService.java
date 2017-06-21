package com.myspringmvc;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by sanek9 on 15.06.17.
 */
public class UserService implements UserDetailsService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("UserService.loadUserByUsername:"+ username);
        throw new UsernameNotFoundException("not found");
//        return null;
    }
}
