package pl.sdacademy.models;

import java.io.Serializable;

/**
 * Created by marcin on 13.12.2017.
 */
public class Admin implements Serializable {
    private String login;
    private String password;

    public Admin(String login) {
        this.login = login;
    }

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
