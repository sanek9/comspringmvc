package com.myspringmvc.core;

import com.myspringmvc.entity.Person;
import com.myspringmvc.entity.RememberToken;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Repository
public class HibernateTokenRepository implements PersistentTokenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
//        getJdbcTemplate().update(insertTokenSql, token.getUsername(), token.getSeries(),
//                token.getTokenValue(), token.getDate());

        System.out.println("createNewToken.series "+ token.getSeries());
        RememberToken rememberToken = new RememberToken();
        rememberToken.setDate(token.getDate());
        rememberToken.setSeries(token.getSeries());
        rememberToken.setTokenValue(token.getTokenValue());
        rememberToken.setPerson(entityManager.find(Person.class, Long.valueOf(token.getUsername())));
        entityManager.persist(rememberToken);

    }
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        System.out.println("updateToken.series "+ series);
        RememberToken rememberToken = entityManager.find(RememberToken.class, series);
        if(rememberToken!=null){
            rememberToken.setTokenValue(tokenValue);
            rememberToken.setDate(lastUsed);
        }

    }

    @Transactional(readOnly = true)
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
//        System.out.println("getTokenForSeries.seriesId "+ seriesId);
        RememberToken rememberToken = entityManager.find(RememberToken.class, seriesId);
        if(rememberToken!=null){
            return new PersistentRememberMeToken(String.valueOf(rememberToken.getPerson().getPersonId()),
                    rememberToken.getSeries(), rememberToken.getTokenValue(), rememberToken.getDate());
        }

        return null;
    }
    @Transactional
    public void removeUserTokens(String username) {
        System.out.println("removeUserTokens.username: "+ username);
        Query query = entityManager.createQuery("DELETE FROM RememberToken r where r.person.personId = :id");
        query.setParameter("id", Long.valueOf(username));
        query.executeUpdate();
    }
}
