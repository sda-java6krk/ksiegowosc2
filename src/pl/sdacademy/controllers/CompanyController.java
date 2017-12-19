package pl.sdacademy.controllers;


import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;
import pl.sdacademy.views.CompanyView;

import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyController {


    public static void createCompany(String nip, String name, int yearFound) {

            CompanyRegistry.getInstance().add(new Company(nip, name, yearFound));
    }

    public static void removeCompany(String nip) {
        CompanyRegistry.getInstance().remove(nip);
    }

    public static void listCompanies() {
        CompanyView.printCompanies(CompanyRegistry.getInstance().getCompanies());

    }
}
