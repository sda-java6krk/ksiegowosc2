package pl.sdacademy.models;

import java.util.Set;

/**
 * Created by marcin on 13.12.2017.
 */
public class Company {

    private String name;
    private int yearFound;
    private String nip;
    private Set<Accountant> accountants;

    public Company(String nip,String name, int yearFound) {
        this.name = name;
        this.yearFound = yearFound;
        this.nip = nip;
    }
    public String getNip() {
        return nip ;
    }

    public String getName() {
        return name;
    }

    public int getYearFound() {
        return yearFound;
    }


}
