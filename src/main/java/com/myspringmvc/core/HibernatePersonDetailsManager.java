package com.myspringmvc.core;

import com.myspringmvc.MyUser;
import com.myspringmvc.entity.Message;
import com.myspringmvc.entity.Person;
import com.myspringmvc.entity.Shadow;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by sanek9 on 16.06.17.
 */
@EnableTransactionManagement
//@EnableAutoConfiguration
@Repository
public class HibernatePersonDetailsManager implements PersonDetailsManager, UserDetailsService {




    @PersistenceContext
    private EntityManager entityManager;
    @Transactional(readOnly = true)
    public Person findById(Long id) {
        Person person;
        Query query = entityManager.createQuery("SELECT p from Person p " +
                "where p.personId = :id");
        query.setParameter("id", id);
        try {
            person = (Person) query.getSingleResult();

        }catch (PersistenceException e){
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
        return person;
    }

    @Transactional(readOnly = true)
    public Shadow findShadowByEmailOrPhone(String s) {
        System.out.println("findShadowByEmailOrPhone");

        Query query = entityManager.createQuery("SELECT s FROM Shadow s " +
                "where s.person.email like :s or s.person.phone like :s");

        query.setParameter("s", s);
        Shadow shadow = null;
        try {
            shadow = (Shadow) query.getSingleResult();

        }catch (PersistenceException e){
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
        return shadow;
    }

    @Transactional
    public void registerPerson(Person person) {
        Shadow shadow = new Shadow();
        shadow.setPassword(person.getPassword());
        shadow.setPerson(person);
        entityManager.persist(shadow);

    }
    @Transactional
    public void addMessage(Long id, Message message) {
        Person person = findById(id);
        message.setPerson(person);
        System.out.println("message: "+message.getMessage());
        entityManager.persist(message);
//        message.setPerson(person);
//        person.getMessages().add(message);

    }

    @Transactional
    public void delMessage(Long pid, Long mid) {
        Query query = entityManager.createQuery("select m FROM Message m " +
                "where m.messageId = :m_id and m.person.personId = :p_id");
        query.setParameter("m_id", mid);
        query.setParameter("p_id", pid);
        Message result = (Message) query.getSingleResult();
        entityManager.remove(result);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Shadow shadow = findShadowByEmailOrPhone(username);

        return new MyUser(shadow.getPersonId(),
                shadow.getPassword(), new ArrayList<GrantedAuthority>());
    }
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
}
