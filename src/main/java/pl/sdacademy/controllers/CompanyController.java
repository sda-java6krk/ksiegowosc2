package pl.sdacademy.controllers;


import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.ValidateNipException;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;
import pl.sdacademy.views.CompanyView;

import java.io.IOException;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyController {


    public static void createCompany(String nip, String name, int yearFound) throws ValidateNipException, IOException {
        CompanyRegistry.getInstance().addCompany(new Company(nip, name, yearFound));
    }

    public static void removeCompany(String nip) throws CompanyNotFoundException, IOException {
        CompanyRegistry.getInstance().remove(nip);
    }

    public static void listCompanies() {
        CompanyView.printCompanies(CompanyRegistry.getInstance().getCompanies());

    }

    public static void saveCompanies() throws IOException {
        CompanyRegistry.saveCompanyToFile(CompanyRegistry.getInstance().getCompanies());
    }

    public static void readCompany() throws IOException, ClassNotFoundException {
        CompanyRegistry.readCompanyFromFile(CompanyRegistry.getInstance().getCompanies());
    }
}
