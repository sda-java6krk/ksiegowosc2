package pl.sdacademy.userInterface;

import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.AccountantRegistry;

import java.util.Scanner;

public class UserInterfaceLoggingInAsAccountant {

    public static State getStateLoggingInAsAccountant(Scanner scanner) {
        Accountant currentAccountant;
        System.out.println("Podaj login:");
        String login = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        try {
            currentAccountant = AccountantRegistry.getInstance().findAccountant(login, password);
            System.out.println("Dzień dobry " + currentAccountant.getLogin());
            return State.LOGGED_IN_AS_ACCOUNTANT;

        } catch (AccountantNotFoundException e) {
            System.out.println(e.getMessage());
            return State.INIT;
        }
    }

}
