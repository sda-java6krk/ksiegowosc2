package pl.sdacademy.controllers;

import pl.sdacademy.exceptions.AccountantAlreadyExistException;
import pl.sdacademy.exceptions.AccountantPasswordIsToShort;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.views.AccountantView;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantController {

    public static void createAccountant(String login, String password) throws AccountantAlreadyExistException,AccountantPasswordIsToShort{
        AccountantRegistry.getInstance().addAccountant(new Accountant(login,password));
    }

    public static void removeAccountant(String login){
        AccountantRegistry.getInstance().removeAccountant(login);
    }

    public static void listAccountant(){
        AccountantView.printAccountant(AccountantRegistry.getInstance().getAccountants());
    }


}
