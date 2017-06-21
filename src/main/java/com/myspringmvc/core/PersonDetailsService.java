package com.myspringmvc.core;

import com.myspringmvc.entity.Person;

/**
 * Created by sanek9 on 16.06.17.
 */
public interface PersonDetailsService {

    Person findById(String id);



}
