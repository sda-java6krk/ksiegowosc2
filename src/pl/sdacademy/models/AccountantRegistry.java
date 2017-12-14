package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.AdminNotFoundException;

import java.util.ArrayList;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantRegistry {
    private static AccountantRegistry instance = null;

    public static AccountantRegistry getInstance() {
        if(instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }


    private ArrayList<Accountant> accountants;

    public AccountantRegistry() {
        this.accountants = new ArrayList<>();

        this.accountants.add(new Accountant("andrzej", "andrzejpass"));
        this.accountants.add(new Accountant("janusz", "januszpass"));
    }


    public Accountant findAccountant(String login, String password) throws AccountantNotFoundException {
        for(Accountant accountant : accountants) {
            if(accountant.getLogin().equals(login) && accountant.getPassword().equals(password)) {
                return accountant;
            }
        }

        throw new AccountantNotFoundException();
    }
}
