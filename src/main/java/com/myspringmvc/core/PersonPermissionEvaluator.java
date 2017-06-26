package com.myspringmvc.core;

import com.myspringmvc.MyUser;
import com.myspringmvc.entity.Person;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

/**
 * Created by sanek9 on 23.06.17.
 */
public class PersonPermissionEvaluator implements PermissionEvaluator {
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("PersonPermissionEvaluator.3");
        if ((authentication == null)
                || !(targetDomainObject instanceof Person)
                || !(permission instanceof String)){
            return false;
        }
        Person person = (Person) targetDomainObject;
        MyUser user = (MyUser) authentication.getPrincipal();

        if(permission.equals("read"))
            return true;
        if(permission.equals("write")){
            if(user.getPersonId().equals(person.getPersonId()))
                return true;
            else return false;
        }


        return false;
    }

    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println("PersonPermissionEvaluator.4");
        return false;
    }
}
