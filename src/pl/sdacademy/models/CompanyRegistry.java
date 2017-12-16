package pl.sdacademy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyRegistry {
    private static CompanyRegistry instance = null;

    public static CompanyRegistry getInstance() {
        if (instance == null) {
            instance = new CompanyRegistry();
        }
        return instance;
    }


    private ArrayList<Company> companies;

    private CompanyRegistry() {
        this.companies = new ArrayList<>();

        this.companies.add(new Company("1236547896", "Ziutex sp. z o.o.", 1990));
        this.companies.add(new Company("5987643258", "Krakbud s.j.", 1995));
    }

    public Company findCompanyForNip(String nip) {
        for (Company company : companies) {
            if (company.getNip().equals(nip)) {
                return company;
            }
        }
        return null;
    }

    public void changeNipForCompany(Company company, String newNip) {
        company.setNip(newNip);
    }

    public void changeNameForCompany(String nip, String newName) {
        for (Company company : companies) {
            if (company.getNip().equals(nip)) {
                company.setName(newName);
            } else {
                System.out.println("Błedny NIP ");
            }
        }
    }

    public List<Company> getCompanies() {
        return this.companies;
    }


    public void add(Company company) {
        this.companies.add(company);
    }

    public void uiForChangingNip() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Podaj stary nip firmy: ");
        String oldNip = scanner.nextLine();
        Company found = findCompanyForNip(oldNip);
        if(found == null){
            System.out.println("Nie znaleziono firmy z takim numerem NIP.");
        }else {
            System.out.println("Podaj nowy nip firmy : ");
            String newNip = scanner.nextLine();
            changeNipForCompany(found, newNip);
        }
    }
}
