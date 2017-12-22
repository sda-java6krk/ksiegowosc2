package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.ValidateNip;
import pl.sdacademy.models.CompanyRegistry;

import java.util.Scanner;

public class UserInterfaceCreatingCompany {

    public static State getStateCreatingCompany(Scanner scanner) {
        System.out.println("Podaj nazwę nowej firmy:");
        String name = scanner.nextLine();

        System.out.println("Podaj rok założenia nowej firmy:");
        int yearFound = scanner.nextInt();
        scanner.nextLine();

        boolean isAccurate = false;

        while (!isAccurate) {
            System.out.println("Podaj nip: ");
            String nip = scanner.nextLine();
            try {
                if (CompanyRegistry.getInstance().validateNIP(nip)) {
                    CompanyController.createCompany(nip, name, yearFound);
                    isAccurate = true;
                }
            } catch (ValidateNip e) {
                System.out.println(e.getMessage());
            }

        }

        return State.LOGGED_IN_AS_ADMIN;
    }
}
