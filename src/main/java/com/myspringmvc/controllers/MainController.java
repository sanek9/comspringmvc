package com.myspringmvc.controllers;


import javax.validation.Valid;

import com.myspringmvc.entity.Message;
import com.myspringmvc.entity.Person;
import com.myspringmvc.core.PersonDetailsManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by sanek9 on 14.06.17.
 */

@Controller
public class MainController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private PersonDetailsManager personDetailsManager;

//    @Autowired
//    SessionFactory sessionFactory;
    @RequestMapping(value = "/feed")
    public ModelAndView hello(){

        UserDetails userDetails =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = personDetailsManager.findById(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profilePage");
        modelAndView.addObject("person", person);
        modelAndView.addObject("message", new Message());
        System.out.println("person_length: "+person.getMessages().size());
        return modelAndView;
    }

    @RequestMapping(value = "/id{id}")
    public ModelAndView getPersonPage(@PathVariable String id, Principal p){
        System.out.println("req_id: "+id);

        try {
            Person person = personDetailsManager.findById(id);
            return new ModelAndView("person_page", "person", person);
        }catch (UsernameNotFoundException e){
            return null;
        }

    }
    @RequestMapping(value = "/")
    public String toFeed(){


        return "redirect:/feed";
    }
    private Person getPerson(){
        Person person = new Person();
        person.setFirstName("first"+new Random().nextInt());
        person.setLastName("last"+new Random().nextInt());
        person.setMiddleName("middle"+new Random().nextInt());
        person.setGender("M");
        person.setPhone(String.valueOf(new Random().nextInt(100000)));
        person.setEmail(new Random().nextInt()+"@"+new Random().nextInt());
        person.setBirthday(new GregorianCalendar());
        person.setPassword("123");
        return person;
    }
    @RequestMapping(value = "/add_message")
    private String addMessage(@ModelAttribute("message") Message message, Principal p){
//        Message m = new Message();
//        m.setMessage("lalala");
        System.out.println("pricipal_name:" + p.getName());

        message.setDate(Calendar.getInstance());
        personDetailsManager.addMessage(p.getName(), message);
        return "redirect:/feed";
    }
    @RequestMapping(value = "/del_message/{id}")
    private String dellMessage(@PathVariable String id, Principal p){
//        Message m = new Message();
//        m.setMessage("lalala");
        System.out.println("pricipal_name:" + p.getName());

        personDetailsManager.delMessage(p.getName(), id);
        return "redirect:/feed";
    }




}
