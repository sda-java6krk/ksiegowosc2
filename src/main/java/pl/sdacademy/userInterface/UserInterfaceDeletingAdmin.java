package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.enums.State;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceDeletingAdmin {

    public static State getStateDeletingAdmin(Scanner scanner) throws IOException {
        System.out.println("Podaj login admina do usunięcia:");
        String login = scanner.nextLine();

        AdminController.removeAdmin(login);
        AdminController.saveAdmin();

        return State.LOGGED_IN_AS_ADMIN;
    }
}
