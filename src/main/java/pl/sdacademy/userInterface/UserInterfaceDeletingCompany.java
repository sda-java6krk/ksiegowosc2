package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.CompanyNotFoundException;

import java.util.Scanner;

public class UserInterfaceDeletingCompany {

    public static State getStateDeletingCompany(Scanner scanner) {
        System.out.println("Podaj NIP firmy do usunięcia:");
        String nip = scanner.nextLine();

        try {
            CompanyController.removeCompany(nip);
        } catch (CompanyNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return State.LOGGED_IN_AS_ADMIN;
    }
}
