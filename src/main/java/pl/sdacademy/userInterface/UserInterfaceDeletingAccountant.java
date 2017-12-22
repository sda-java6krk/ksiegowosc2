package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceDeletingAccountant {

    public static State getStateDeletingAccountant(Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.println("Podaj login ksiegowego do usuniecia: ");
        String login = scanner.nextLine();
        try {
            AccountantController.removeAccountant(login);
            AccountantController.saveAccountant();
        } catch (AccountantNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return State.LOGGED_IN_AS_ADMIN;
    }
}
