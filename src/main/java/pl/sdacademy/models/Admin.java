package pl.sdacademy.models;

import java.io.Serializable;

public class Admin implements Serializable {
    private String login;
    private String password;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Admin() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "Login: " + login;
    }
}
