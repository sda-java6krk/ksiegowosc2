package pl.sdacademy.userInterface;

import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.models.Admin;
import pl.sdacademy.models.AdminRegistry;

import java.util.Scanner;

public class UserInterfaceLoggingInAsAdmin {
    public static State getStateLoggingInAsAdmin(Scanner scanner) {
        Admin currentAdmin;
        System.out.println("Podaj login:");
        String login = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        try {
            currentAdmin = AdminRegistry.getInstance().findAdmin(login, password);
            System.out.println("Dzień dobry " + currentAdmin.getLogin());
            return State.LOGGED_IN_AS_ADMIN;

        } catch (AdminNotFoundException e) {
            System.out.println("Zły login lub hasło");
            return State.INIT;
        }
    }
}
