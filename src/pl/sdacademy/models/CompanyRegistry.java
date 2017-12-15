package pl.sdacademy.models;

import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyRegistry {
    private static CompanyRegistry instance = null;

    public static CompanyRegistry getInstance() {
        if(instance == null) {
            instance = new CompanyRegistry();
        }
        return instance;
    }


    private ArrayList<Company> companies;

    public CompanyRegistry() {
        this.companies = new ArrayList<>();

        this.companies.add(new Company("1236547896" ,"Ziutex sp. z o.o.", 1990));
        this.companies.add(new Company("5987643258","Krakbud s.j.", 1995));
    }

    public boolean findCompanyForNip(String nip) {

        for (Company company : companies) {
            if (company.getNip().equals(nip)) {
                return true;
            }
        }

        return false;
    }



    public List<Company> getCompanies() {
        return this.companies;
    }


    public void add(Company company) {
        this.companies.add(company);
    }
}
