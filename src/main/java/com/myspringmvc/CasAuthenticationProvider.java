package com.myspringmvc;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by sanek9 on 15.06.17.
 */
public class CasAuthenticationProvider implements AuthenticationProvider {


    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        System.out.println("CasAuthenticationProvider.authenticate: "+ authentication);
        System.out.println("CasAuthenticationProvider.authenticate: "+ authentication);


        return null;
    }

    public boolean supports(Class<?> authentication) {

        System.out.println("CasAuthenticationProvider.supports: "+ authentication);
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
