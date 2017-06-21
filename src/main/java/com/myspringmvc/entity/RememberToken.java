package com.myspringmvc.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "remember_token")
public class RememberToken {


//    private String username;
    @Id
    private String series;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private String tokenValue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
