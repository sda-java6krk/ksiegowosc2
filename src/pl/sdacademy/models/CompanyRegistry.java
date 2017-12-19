package pl.sdacademy.models;

import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    public void changeNipForCompany(Company company, String newNip) {
        company.setNip(newNip);
    }


    public void changeNameForCompany(Company company, String newName) {
        company.setName(newName);
    }

    public List<Company> getCompanies() {
        return this.companies;
    }


        public void add(Company company) {
        this.companies.add(company);
    }



    public boolean validateNIP(String nip) {
        int nipResult = 0;

        if (nip.length() != 10) {
            return false;
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
            return false;
        }

        return true;
    }



    public void uiForChangingNip() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj stary nip firmy: ");
        String oldNip = scanner.nextLine();
        Company found = findCompanyByNip(oldNip);
        if(found == null){
            System.out.println("Nie znaleziono firmy z takim numerem NIP.");
        }else {
            System.out.println("Podaj nowy nip firmy : ");
            String newNip = scanner.nextLine();
            changeNipForCompany(found, newNip);
        }
    }
    public  void  uiForChangingName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj  nip firmy: ");
        String nip = scanner.nextLine();
        Company found = findCompanyByNip(nip);
        if(found == null){
            System.out.println("Nie znaleziono firmy z takim numerem NIP.");
        }else {
            System.out.println("Podaj nową nazwę firmy : ");
            String newName = scanner.nextLine();
            changeNameForCompany(found, newName);
        }
    }
}
