package com.myspringmvc.core;

import com.myspringmvc.entity.Message;
import com.myspringmvc.entity.Person;
import com.myspringmvc.entity.Shadow;

/**
 * Created by sanek9 on 16.06.17.
 */
public interface PersonDetailsManager extends PersonDetailsService {
//    public Person findByEmailOrPhone(String s);
    Shadow findShadowByEmailOrPhone(String s);
    void registerPerson(Person person);

    void addMessage(String id, Message message);

    void delMessage(String name, String id);
}
