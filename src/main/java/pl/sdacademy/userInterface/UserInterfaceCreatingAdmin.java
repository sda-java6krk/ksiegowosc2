package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.enums.State;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceCreatingAdmin {

    public static State getStateCreatingAdmin(Scanner scanner) throws IOException {
        System.out.println("Podaj login nowego admina:");
        String login = scanner.nextLine();

        System.out.println("Podaj haslo nowego admina:");
        String password = scanner.nextLine();

        AdminController.createAdmin(login, password);
        AdminController.saveAdmin();

        return State.LOGGED_IN_AS_ADMIN;
    }
}
