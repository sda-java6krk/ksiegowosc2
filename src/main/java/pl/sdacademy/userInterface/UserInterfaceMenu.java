package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantAlreadyExistException;
import pl.sdacademy.exceptions.AccountantPasswordIsToShort;
import pl.sdacademy.exceptions.AccountantWrongLogin;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceMenu {

    public static State getStateMenu(Scanner scanner) throws IOException, ClassNotFoundException {
        State state;
        try {
            AccountantController.readAccountant();
        } catch (ClassNotFoundException | AccountantPasswordIsToShort | AccountantAlreadyExistException | IOException | AccountantWrongLogin e) {
            e.getMessage();
        }
        AdminController.readAdmin();
        System.out.println("Dzień dobry, co chcesz zrobić?");
        System.out.println(" 1 - zalogować się jako accountant");
        System.out.println(" 2 - zalogować się jako admin");
        System.out.println(" 0 - wyjść z programu");

        switch (scanner.nextInt()) {
            case 1:
                state = State.LOGGING_IN_AS_ACCOUNTANT;
                scanner.nextLine();
                break;

            case 2:
                state = State.LOGGING_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            case 0:
                state = State.EXIT;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.INIT;
                scanner.nextLine();
                break;
        }
        return state;
    }
}