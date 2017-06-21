package com.myspringmvc.entity;

import javax.persistence.*;

/**
 * Created by sanek9 on 16.06.17.
 */
@Entity
@Table(name = "shadow")
public class Shadow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @PrimaryKeyJoinColumn
    @Column(name = "person_id", nullable = false)
    private long personId;


    @Column(name = "password", nullable = false)
    private String password;

    @MapsId
    @OneToOne
    @JoinColumn(name = "person_id")
    @PrimaryKeyJoinColumn
    private Person person;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
