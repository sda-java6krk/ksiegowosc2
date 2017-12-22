package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantAlreadyExistException;
import pl.sdacademy.exceptions.AccountantPasswordIsToShort;
import pl.sdacademy.exceptions.AccountantWrongLogin;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceCreatingAccountant {

    public static State getStateCreatingAccountant(Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.println("Podaj login nowego ksiegowego:");
        String login = scanner.nextLine();
        System.out.println("Podaj haslo: ");
        String password = scanner.nextLine();

        try {
            AccountantController.createAccountant(login, password);
            AccountantController.saveAccountant();

        } catch (AccountantAlreadyExistException | AccountantPasswordIsToShort | AccountantWrongLogin e) {
            System.out.println(e.getMessage());

        }
        return State.LOGGED_IN_AS_ADMIN;
    }
}
