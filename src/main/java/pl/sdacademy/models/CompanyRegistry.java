package pl.sdacademy.models;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.CompanyWrongYearFoundException;
import pl.sdacademy.exceptions.ValidateNipException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    }

    public void remove(String nip) throws CompanyNotFoundException, IOException {
        boolean removed = false;
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getNip().equals(nip)) {
                companies.remove(companies.get(i));
                CompanyController.saveCompanies();
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

    public void changeNipForCompany(Company company, String newNip) throws ValidateNipException, IOException {
        validateNIP(newNip);
        company.setNip(newNip);
        CompanyController.saveCompanies();
    }


    public void changeNameForCompany(Company company, String newName) throws IOException {
        company.setName(newName);
        CompanyController.saveCompanies();
    }

    public ArrayList<Company> getCompanies() {
        return this.companies;
    }

    public boolean searchIfCompanyYearOfFoundIsGood(int companyYear) throws CompanyWrongYearFoundException {
        if (companyYear >= 1800 && companyYear <= 2018) {
            return true;
        }
        throw new CompanyWrongYearFoundException("Zly rok zalozenia firmy");
    }

    public void addCompany(Company company) throws IOException {
        this.companies.add(company);
        CompanyController.saveCompanies();
    }


    public boolean validateNIP(String nip) throws ValidateNipException {
        int nipResult = 0;

        if (nip.length() != 10) {
            throw new ValidateNipException("Bledny nip");
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
            throw new ValidateNipException("Bledny nip");
        }

        return true;
    }


    public void uiForChangingNip(Company company, String nip) throws ValidateNipException, IOException {
        if (validateNIP(nip)) {
            changeNipForCompany(company, nip);
        } else {
            throw new ValidateNipException("bledny nip");
        }
    }

    public void uiForChangingName(Company company, String name) throws IOException {
        changeNameForCompany(company, name);
    }

    public static void saveCompanyToFile(ArrayList<Company> companies) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/myCompanyRegistry.bin"));

        objectOutputStream.writeObject(companies);
        objectOutputStream.close();
    }

    public static void readCompanyFromFile(ArrayList<Company> companies) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/myCompanyRegistry.bin"));

        ArrayList<Company> list = (ArrayList<Company>) objectInputStream.readObject();

        getInstance().removeAllCompanies();

        for (Company a : list) {
            getInstance().addCompany(a);
        }
        objectInputStream.close();
    }

    public void removeAllCompanies() {
        companies = new ArrayList<>();
    }
}