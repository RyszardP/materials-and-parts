package com.ryszard.materialsandparts.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
    private String userSurname;

    @Column(name = "user_email")
    private String email;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "login")
    private String login;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(long id, String userName, String userSurname, String email, @NotNull(message = "is required")
    @Size(min = 1, message = "is required") String login, @NotNull(message = "is required")
    @Size(min = 1, message = "is required") String password) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
