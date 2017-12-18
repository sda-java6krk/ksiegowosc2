package pl.sdacademy.models;

import java.util.Set;

/**
 * Created by marcin on 13.12.2017.
 */
public class Company {
    private String name;
    private String nip;
    private int yearFound;
    private Set<Accountant> accountants;

    public Company(String nip, String name, int yearFound) {
        this.name = name;
        this.yearFound = yearFound;
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public int getYearFound() {
        return yearFound;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearFound(int yearFound) {
        this.yearFound = yearFound;
    }

    public String getNip() {
        return nip;
    }
}
