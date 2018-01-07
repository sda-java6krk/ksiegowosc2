package pl.sdacademy.views;

import pl.sdacademy.models.Accountant;

import java.util.Set;

public class AccountantView {


    public static void printAccountant(Set<Accountant> accountantList) {


        for (Accountant accountant : accountantList) {
            System.out.println("login: " + accountant.getLogin());
        }
    }


}
