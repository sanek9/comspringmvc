package com.myspringmvc.controllers;


import javax.validation.Valid;

import com.myspringmvc.MyUser;
import com.myspringmvc.entity.Message;
import com.myspringmvc.entity.Person;
import com.myspringmvc.core.PersonDetailsManager;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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

        MyUser user =
                (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = personDetailsManager.findById(user.getPersonId());
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

    @RequestMapping(value = "/peoples")
    private ModelAndView peoples(){
        ModelAndView modelAndView = new ModelAndView("peoples");
        modelAndView.addObject("persons", personDetailsManager.getPersons());

        return modelAndView;
    }
    @RequestMapping("id{id}")
    public ModelAndView person(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        Person person = personDetailsManager.findById(id);
        modelAndView.addObject("person", person);
        modelAndView.addObject("message", new Message());
        return modelAndView;
    }
    @ModelAttribute("persons")
    public List<Person> messages() {
        return personDetailsManager.getPersons();
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
    private String addMessage(@ModelAttribute("message") Message message){

        message.setDate(Calendar.getInstance());
        MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        personDetailsManager.addMessage(user.getPersonId(), message);
        return "redirect:/feed";
    }
    @RequestMapping(value = "/del_message/{id}")
    private String dellMessage(@PathVariable Long id){
        MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        personDetailsManager.delMessage(user.getPersonId(), id);
        return "redirect:/feed";
    }


}
