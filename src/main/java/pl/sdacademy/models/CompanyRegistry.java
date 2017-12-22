package pl.sdacademy.models;

import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.ValidateNip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyRegistry extends Company implements Serializable {
    private static CompanyRegistry instance = null;

    public static CompanyRegistry getInstance() {
        if (instance == null) {
            instance = new CompanyRegistry();
        }
        return instance;
    }


    private ArrayList<Company> companies;
    private List<Invoice> invoicesForCompany;

    public CompanyRegistry() {
        this.companies = new ArrayList<>();

        this.companies.add(new Company("4835444141", "Ziutex sp. z o.o.", 1990));
        this.companies.add(new Company("3592184997", "Krakbud s.j.", 1995));
    }

    public void remove(String nip) throws CompanyNotFoundException {
        boolean removed = false;
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getNip().equals(nip)) {
                companies.remove(companies.get(i));
                removed = true;
            }

        }
        if (!removed) {
            throw new CompanyNotFoundException("Nie ma takiej firmy");
        }

    }

    public Company findCompanyByNip(String nip) throws CompanyNotFoundException {

        for (Company company : companies) {
            if (company.getNip().equals(nip)) {
                return company;
            }
        }
        throw new CompanyNotFoundException("Nie ma takiej firmy z tym nipem");
    }

    public Company findCompanyByName(String name) throws CompanyNotFoundException {

        for (Company company : companies) {
            if (company.getName().equals(name)) {
                return company;
            }
        }
        throw new CompanyNotFoundException("Nie ma firmy o tej nazwie");
    }

    public void changeNipForCompany(Company company, String newNip) {
        company.setNip(newNip);
    }


    public void changeNameForCompany(Company company, String newName) {
        company.setName(newName);
    }

    public List<Company> getCompanies() {
        return this.companies;
    }


    public void add(Company company) throws  ValidateNip {
        this.companies.add(company);
    }


    public boolean validateNIP(String nip) throws ValidateNip {
        int nipResult = 0;

        if (nip.length() != 10) {
            throw new ValidateNip("Bledny nip");
        }

        for (int i = 0; i < nip.length() - 1; i++) {

            if (i == 0 || i == 7) {
                nipResult += (nip.charAt(i) - 48) * 6;
            } else if (i == 1 || i == 6) {
                nipResult += (nip.charAt(i) - 48) * 5;
            } else if (i == 2 || i == 8) {
                nipResult += (nip.charAt(i) - 48) * 7;
            } else if (i == 3) {
                nipResult += (nip.charAt(i) - 48) * 2;
            } else if (i == 4) {
                nipResult += (nip.charAt(i) - 48) * 3;
            } else if (i == 5) {
                nipResult += (nip.charAt(i) - 48) * 4;
            }

        }

        if (nipResult % 11 != (nip.charAt(9) - 48)) {
            throw new ValidateNip("Bledny nip");
        }

        return true;
    }


    public void uiForChangingNip(Company company, String nip) throws ValidateNip {
        if (validateNIP(nip)) {
            changeNipForCompany(company, nip);
        } else {
            throw new ValidateNip("bledny nip");
        }
    }

    public void uiForChangingName(Company company, String name) {
        changeNameForCompany(company, name);
    }
}