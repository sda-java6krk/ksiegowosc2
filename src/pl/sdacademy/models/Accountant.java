package pl.sdacademy.models;

import java.io.Serializable;
import java.util.Objects;

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
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Accountant that = (Accountant) object;
        return Objects.equals(login.toLowerCase(), that.login.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(login.toLowerCase());
    }
}
