package pl.sdacademy.models;

import java.io.Serializable;

/**
 * Created by marcin on 13.12.2017.
 */
public class Accountant implements Serializable {
    protected String login;
    protected String password;


    public Accountant(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Accountant() {
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
