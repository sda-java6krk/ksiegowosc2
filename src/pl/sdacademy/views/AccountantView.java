package pl.sdacademy.views;

import pl.sdacademy.models.Accountant;


import java.util.List;
import java.util.Set;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantView {

public  static void printAccountant(Set<Accountant> accountantList){

    for(Accountant accountant : accountantList){
        System.out.println("login: " + accountant.getLogin());
    }
}
}
