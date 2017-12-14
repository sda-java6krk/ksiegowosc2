package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.util.ArrayList;

public class AccountantRegistry {
    private static AccountantRegistry instance = null;
    private ArrayList<Accountant> accountants;

    public AccountantRegistry() {
        this.accountants = new ArrayList<>();

        this.accountants.add(new Accountant("Tomasz", "123"));
        this.accountants.add(new Accountant("Marek", "123"));
    }

    public static AccountantRegistry getInstance() {
        if (instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }

    public void addAccountant(Accountant accountant) {
        accountants.add(accountant);
    }

    public void removeAccountant(String login) {
        boolean removed = false;

        for (int i = 0; i < accountants.size(); i++) {
            if (accountants.get(i).getLogin().equals(login)) {
                accountants.remove(i);
                removed = true;
            }
        }
        if (removed == false) {
            System.out.println("Nie ma takiego ksiegowego");
        }
    }

    public Accountant findAccountant(String login, String password) throws AccountantNotFoundException {
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login) && accountant.getPassword().equals(password)) {
                return accountant;
            }
        }

        throw new AccountantNotFoundException();

    }

    public ArrayList<Accountant> getAccountants() {
        return accountants;
    }
}
