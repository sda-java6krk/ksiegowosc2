package pl.sdacademy;

import pl.sdacademy.associations.AccountantCompanyAssociation;
import pl.sdacademy.controllers.AccountantCompanyAssociationController;
import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.models.Admin;
import pl.sdacademy.models.AdminRegistry;

import java.util.Scanner;

public class Main {

    public enum State {
        INIT,
        LOGGING_IN_AS_ADMIN,
        LOGGING_IN_AS_ACCOUNTANT,
        LOGGED_IN_AS_ACCOUNTANT,
        LOGGED_IN_AS_ADMIN,
        CREATING_COMPANY,
        CREATING_ADMIN,
        DELETING_ADMIN,
        CREATING_ACCOUNTANT,
        DELETING_ACCOUNTANT,
        DELETING_COMPANY,
        CREATING_ACCOUNTANT_COMPANY_ASSOCIATION,
        EXIT,
    }

    public static void main(String[] args) {
        State state = State.INIT;
        Scanner scanner = new Scanner(System.in);

        Admin currentAdmin = null;
        Accountant currentAccountant = null;

        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
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
                    break;
                }

                case LOGGING_IN_AS_ACCOUNTANT: {
                    System.out.println("Podaj login:");
                    String login = scanner.nextLine();

                    System.out.println("Podaj hasło:");
                    String password = scanner.nextLine();

                    try {
                        currentAccountant = AccountantRegistry.getInstance().findAccountant(login, password);
                        System.out.println("Dzień dobry " + currentAccountant.getLogin());
                        state = State.LOGGED_IN_AS_ACCOUNTANT;

                    } catch (AccountantNotFoundException e) {
                        System.out.println(e.getMessage());
                        state = State.INIT;
                    }
                    break;
                }

                case LOGGING_IN_AS_ADMIN: {
                    System.out.println("Podaj login:");
                    String login = scanner.nextLine();

                    System.out.println("Podaj hasło:");
                    String password = scanner.nextLine();

                    try {
                        currentAdmin = AdminRegistry.getInstance().findAdmin(login, password);
                        System.out.println("Dzień dobry " + currentAdmin.getLogin());
                        state = State.LOGGED_IN_AS_ADMIN;

                    } catch (AdminNotFoundException e) {
                        System.out.println("Zły login lub hasło");
                        state = State.INIT;
                    }
                    break;
                }

                case LOGGED_IN_AS_ACCOUNTANT: {
                    System.out.println("Co chcesz zrobić?");
                    //System.out.println(" 1 - wypisać wszystkie firmy");
                    //System.out.println(" 2 - dodać firmę");
                    System.out.println(" 0 - wyjść z programu");

                    switch (scanner.nextInt()) {

                    /*    case 1:
                            CompanyController.listCompanies();
                            state = State.LOGGED_IN_AS_ACCOUNTANT;
                            scanner.nextLine();
                            break;

                        case 2:
                            state = State.CREATING_COMPANY;
                            scanner.nextLine();
                            break;
                    */
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
                    break;
                }

                case LOGGED_IN_AS_ADMIN: {
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
                    System.out.println(" 10 - przypisz ksiegowego do firmy");
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
                            break;

                        case 5:
                            state = State.DELETING_ADMIN;
                            scanner.nextLine();
                            break;

                        case 6:
                            state = State.CREATING_ACCOUNTANT;
                            scanner.nextLine();
                            break;

                        case 7:
                            state = State.DELETING_ACCOUNTANT;
                            scanner.nextLine();
                            break;

                        case 8:
                            AccountantController.listAccountant();
                            state = State.LOGGED_IN_AS_ADMIN;
                            scanner.nextLine();
                            break;
                        case 9:
                            state = State.DELETING_COMPANY;
                            scanner.nextLine();
                            break;

                        case 10:
                            state = State.CREATING_ACCOUNTANT_COMPANY_ASSOCIATION;
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
                    break;
                }

                case CREATING_COMPANY: {
                    System.out.println("Podaj nazwę nowej firmy:");
                    String name = scanner.nextLine();

                    System.out.println("Podaj rok założenia nowej firmy:");
                    int yearFound = scanner.nextInt();
                    scanner.nextLine();


                    System.out.println("Podaj nip firmy ");
                    String nip = scanner.nextLine();
                    CompanyController.createCompany(nip,name, yearFound);


                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case CREATING_ADMIN: {
                    System.out.println("Podaj login nowego admina:");
                    String login = scanner.nextLine();

                    System.out.println("Podaj haslo nowego admina:");
                    String password = scanner.nextLine();

                    AdminController.createAdmin(login, password);

                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case DELETING_ADMIN: {
                    System.out.println("Podaj login admina do usunięcia:");
                    String login = scanner.nextLine();

                    AdminController.removeAdmin(login);

                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case DELETING_COMPANY: {
                    System.out.println("Podaj NIP firmy do usunięcia:");
                    String nip = scanner.nextLine();

                    CompanyController.removeCompany(nip);

                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case CREATING_ACCOUNTANT: {
                    System.out.println("Podaj login nowego ksiegowego:");
                    String login = scanner.nextLine();
                    System.out.println("Podaj haslo: ");
                    String password = scanner.nextLine();
                    try {
                        AccountantController.createAccountant(login,password);

                    } catch (AccountantAlreadyExistException | AccountantPasswordIsToShort e) {
                        System.out.println(e.getMessage());

                    }
                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case CREATING_ACCOUNTANT_COMPANY_ASSOCIATION: {
                    System.out.println("Podaj login ksiegowego:");
                    String login = scanner.nextLine();
                    System.out.println("Podaj nip firmy:");
                    String nip = scanner.nextLine();

                    try {
                        AccountantCompanyAssociationController.createAccountantCompanyAssociation(login,nip);
                    } catch (AccountantCompanyAssociationAlreadyExistException | AccountantNotFoundException | CompanyNotFoundException e)  {
                        System.out.println(e.getMessage());
                    }
                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case DELETING_ACCOUNTANT: {
                    System.out.println("Podaj login ksiegowego do usuniecia: ");
                    String login = scanner.nextLine();
                    AccountantController.removeAccountant(login);
                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }
            }
        }
        // write your code here
    }
}
