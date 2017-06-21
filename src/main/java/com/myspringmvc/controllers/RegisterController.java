package com.myspringmvc.controllers;

import com.myspringmvc.core.PersonDetailsManager;
import com.myspringmvc.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by sanek9 on 21.06.17.
 */
@Controller
public class RegisterController {
    @Autowired
    private PersonDetailsManager personDetailsManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login")
    public String login(){

//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        logger.info(sessionFactory);
//        logger.info(userDetailsService);
////        System.out.println(sessionFactory);
//        System.out.println(userDetailsService);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
        return "redirect:/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){

        return new ModelAndView("reg", "person", new Person());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult){
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
