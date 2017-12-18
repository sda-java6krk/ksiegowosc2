package pl.sdacademy.models;

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
    private List<Invoice> invoicesForCompany;

    public CompanyRegistry() {
        this.companies = new ArrayList<>();

        this.companies.add(new Company("1236547896" ,"Ziutex sp. z o.o.", 1990));
        this.companies.add(new Company("5987643258","Krakbud s.j.", 1995));
    }

    public void remove(String nip) {
        boolean removed = false;
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getNip().equals(nip)) {
                companies.remove(companies.get(i));
                removed = true;
            }

        }
        if (removed == false) {
            System.out.println("Brak firmy o podanym nipie");
        }

    }

    public Company findCompanyByNip(String nip) {

        for (Company company : companies) {
            if (company.getNip().equals(nip)) {
                return company;
            }
        }

        return null;
    }



    public List<Company> getCompanies() {
        return this.companies;
    }


        public void add(Company company) {
        this.companies.add(company);
    }


}
