package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.AccountantCompanyAssociationController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantCompanyAssociationAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;

import java.util.Scanner;

public class UserInterfaceCreatingAccountantCompanyAssociation {

    public static State getStateCreatingAccountantCompanyAssociation(Scanner scanner) {
        System.out.println("Podaj login ksiegowego:");
        String login = scanner.nextLine();
        System.out.println("Podaj nip firmy:");
        String nip = scanner.nextLine();

        try {
            AccountantCompanyAssociationController.createAccountantCompanyAssociation(login, nip);
        } catch (AccountantCompanyAssociationAlreadyExistException | AccountantNotFoundException | CompanyNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return State.LOGGED_IN_AS_ADMIN;
    }
}
