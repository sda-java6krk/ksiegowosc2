package pl.sdacademy.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Company implements Serializable {
    private String name;
    private int yearFound;
    private String nip;
    private Set<Accountant> accountants;
    private List<Invoice> invoices = new ArrayList<>();

    public Company(String nip, String name, int yearFound) {
        this.name = name;
        this.yearFound = yearFound;
        this.nip = nip;
    }

    public Company() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setYearFound(int yearFound) {
        this.yearFound = yearFound;
    }

    public void setNip(String nip) {
        this.nip = nip; //tu dodac validacje nipu
    }

    public void setAccountants(Set<Accountant> accountants) {
        this.accountants = accountants;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

}
