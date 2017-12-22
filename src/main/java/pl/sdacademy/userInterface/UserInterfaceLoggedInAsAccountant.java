package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;

import java.util.Scanner;

import static pl.sdacademy.enums.State.CREATING_INVOICE;

public class UserInterfaceLoggedInAsAccountant {

    public static State getStateLoggedInAsAccountant(Scanner scanner) {
        State state;
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkie firmy");
        System.out.println(" 2 - dodac fakture");
        System.out.println(" 3 - menu");
        System.out.println(" 0 - wyjść z programu");

        switch (scanner.nextInt()) {

            case 1:
                CompanyController.listCompanies();
                state = State.LOGGED_IN_AS_ACCOUNTANT;
                scanner.nextLine();
                break;

            case 2:
                state = CREATING_INVOICE;
                scanner.nextLine();
                break;

            case 3:
                state = State.INIT;
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
