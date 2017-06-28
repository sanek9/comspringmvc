package com.myspringmvc.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sanek9 on 16.06.17.
 */
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @PrimaryKeyJoinColumn
    @Column(name = "person_id", nullable = false)
    private long personId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 32)
    @NotEmpty
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 32)
    @NotEmpty
    private String lastName;

    @Basic
    @Column(name = "middle_name", nullable = true, length = 32)
    private String middleName;

    @Basic
    @Column(name = "birthday", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar birthday;

    @Basic
    @Column(name = "gender", nullable = false, length = 1)
    @NotEmpty
    private String gender;

    @Basic
    @Column(name = "phone", unique = true, nullable = false, length = 10)
    @NotEmpty
    private String phone;

    @Basic
    @Column(name = "email", unique = true, nullable = false, length = 32)
    @NotEmpty
    @Email
    private String email;

    @Column(name = "photo_id")
    private String photoId;


    @Transient
//    @NotEmpty
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    @OrderBy(value = "date desc")
    private List<Message> messages = new ArrayList<Message>();

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }
}


