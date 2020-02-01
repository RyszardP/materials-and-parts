package com.ryszard.materialsandparts.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String login;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String password;

    public User() {
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
}
