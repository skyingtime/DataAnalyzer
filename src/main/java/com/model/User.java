package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by yangliu on 28/09/2016.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String email;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    private String password;

    public User() { }

    public User(long id) {
        this.setId(id);
    }

    public User(String email, String name) {
        this.setEmail(email);
        this.setUserName(name);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
