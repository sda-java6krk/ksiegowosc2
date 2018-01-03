package pl.sdacademy.controllers;

import pl.sdacademy.exceptions.AccountantAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.AccountantPasswordIsToShort;
import pl.sdacademy.exceptions.AccountantWrongLogin;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.views.AccountantView;

import java.io.IOException;
import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantController {

    private static final AccountantRegistry accountantRegistry = AccountantRegistry.getInstance();

    public static void createAccountant(String login, String password) throws AccountantAlreadyExistException, AccountantPasswordIsToShort, AccountantWrongLogin, IOException, ClassNotFoundException {
       accountantRegistry.addAccountant(new Accountant(login,password));
    }

    public static void removeAccountant(String login) throws AccountantNotFoundException, IOException, ClassNotFoundException {

        accountantRegistry.removeAccountant(login);
        }

    public static void listAccountant(){
        AccountantView.printAccountant(AccountantRegistry.getInstance().getAccountants());
    }

    public static void saveAccountant() throws IOException, ClassNotFoundException {
        AccountantRegistry.saveAccountantToFile(accountantRegistry.getAccountants());
    }
    public static void readAccountant() throws ClassNotFoundException, AccountantPasswordIsToShort, AccountantAlreadyExistException, IOException, AccountantWrongLogin {
        AccountantRegistry.readAccountantsFromFile(accountantRegistry.getAccountants());
    }

}
