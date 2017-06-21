package com.myspringmvc.controllers;


import javax.validation.Valid;

import com.myspringmvc.entity.Message;
import com.myspringmvc.entity.Person;
import com.myspringmvc.entity.PersonDetailsManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by sanek9 on 14.06.17.
 */

@Controller
public class MainController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PersonDetailsManager personDetailsManager;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    SessionFactory sessionFactory;
    @RequestMapping(value = "/feed")
    public ModelAndView hello(){

        UserDetails userDetails =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = personDetailsManager.findById(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("person", person);
        modelAndView.addObject("message", new Message());
        System.out.println("person_length: "+person.getMessages().size());
        return modelAndView;
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

    @RequestMapping(value = "/login")
    public String login(){

        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        logger.info(sessionFactory);
        logger.info(userDetailsService);
//        System.out.println(sessionFactory);
        System.out.println(userDetailsService);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
        return "redirect:/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){

        return new ModelAndView("reg", "person", new Person());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid  @ModelAttribute("person") Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "reg";
        }
        System.out.println("register");
        personDetailsManager.registerPerson(person);
        Authentication request = new UsernamePasswordAuthenticationToken(person.getPhone(), person.getPassword());
        Authentication res = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(res);
        return "redirect:/feed";
    }


}
