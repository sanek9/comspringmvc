package com.myspringmvc.core;

import com.myspringmvc.entity.Message;
import com.myspringmvc.entity.Person;
import com.myspringmvc.entity.Shadow;

import java.util.List;

/**
 * Created by sanek9 on 16.06.17.
 */
public interface PersonDetailsManager extends PersonDetailsService {
//    public Person findByEmailOrPhone(String s);
    Shadow findShadowByEmailOrPhone(String s);
    void registerPerson(Person person);

    void addMessage(Long id, Message message);

    void delMessage(Long pid, Long mid);
    List<Person> getPersons();
}
