package pl.sdacademy.userInterface;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceLoggedInAsAdmin {

    public static State getStateLoggedInAsAdmin(Scanner scanner) throws IOException, ClassNotFoundException {
        State state;
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkie firmy");
        System.out.println(" 2 - dodać firmę");
        System.out.println(" 3 - wypisać wszystkich adminów");
        System.out.println(" 4 - dodać admina");
        System.out.println(" 5 - usunąć admina");
        System.out.println(" 6 - dodać konto ksiegowego");
        System.out.println(" 7 - usunac konto ksiegoweo");
        System.out.println(" 8 - wypisac wszystkich ksiegowych");
        System.out.println(" 9 - usunac firme");
        System.out.println(" 10 - zmienić nazwe lub nip firmy");
        System.out.println(" 11 - przypisz ksiegowego do firmy");
        System.out.println(" 12 - menu");
        System.out.println(" 0 - wyjść z programu");

        switch (scanner.nextInt()) {
            case 1:
                CompanyController.listCompanies();
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            case 2:
                state = State.CREATING_COMPANY;
                scanner.nextLine();
                break;

            case 3:
                AdminController.listAdmins();
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            case 4:
                state = State.CREATING_ADMIN;
                scanner.nextLine();
                AdminController.saveAdmin();
                break;

            case 5:
                state = State.DELETING_ADMIN;
                scanner.nextLine();
                AdminController.saveAdmin();
                break;

            case 6:
                state = State.CREATING_ACCOUNTANT;
                scanner.nextLine();
                try {
                    AccountantController.saveAccountant();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case 7:
                state = State.DELETING_ACCOUNTANT;
                try {
                    AccountantController.saveAccountant();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                scanner.nextLine();
                break;

            case 8:
                AccountantController.listAccountant();
                //AccountantView.printAccountant(AccountantRegistry.readAccountantsFromFile());
                //AccountantRegistry.showAccounutants();
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            case 9:
                state = State.DELETING_COMPANY;
                scanner.nextLine();
                break;

            case 10:
                state = State.CHANGE_COMPANY;
                break;

            case 11:
                state = State.CREATING_ACCOUNTANT_COMPANY_ASSOCIATION;
                scanner.nextLine();
                break;

            case 12:
                state = State.INIT;
                scanner.nextLine();
                AdminController.readAdmin();
                break;

            case 0:
                try {
                    AccountantController.saveAccountant();
                } catch (IOException | ClassNotFoundException e) {
                    e.getMessage();
                }
                AdminController.saveAdmin();
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
