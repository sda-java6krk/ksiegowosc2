package pl.sdacademy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by marcin on 13.12.2017.
 */
public class Company {

    private String name;
    private int yearFound;
    private String nip;
    private Set<Accountant> accountants;
    private List<Invoice> invoices = new ArrayList<>();
    public Company(String nip,String name, int yearFound) {
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

    public String getNip() {
        return nip;
    }


    public List<Invoice> getInvoices() {
        return invoices;
    }


}
