package pl.sdacademy;

import pl.sdacademy.associations.AccountantCompanyAssociation;
import pl.sdacademy.associations.AccountantCompanyAssociationRegistry;
import pl.sdacademy.controllers.*;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.*;
import pl.sdacademy.views.CompanyView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pl.sdacademy.Main.State.CREATING_INVOICE;
import static pl.sdacademy.associations.AccountantCompanyAssociationRegistry.readAccountantCompanyAssiociation;

public class Main {

    public enum State {
        INIT,
        LOGGING_IN_AS_ADMIN,
        LOGGING_IN_AS_ACCOUNTANT,
        LOGGED_IN_AS_ACCOUNTANT,
        LOGGED_IN_AS_ADMIN,
        CREATING_COMPANY,
        CHANGE_COMPANY,
        CREATING_ADMIN,
        DELETING_ADMIN,
        CREATING_ACCOUNTANT,
        DELETING_ACCOUNTANT,
        CREATING_INVOICE,
        DELETING_COMPANY,
        CREATING_ACCOUNTANT_COMPANY_ASSOCIATION,
        EXIT,
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort {
        State state = State.INIT;
        Scanner scanner = new Scanner(System.in);


        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
                    state = printInit(scanner, state);
                    break;
                }

                case LOGGING_IN_AS_ACCOUNTANT: {
                    state = printLoggingInAsAccountant(scanner);
                    break;
                }

                case LOGGING_IN_AS_ADMIN: {
                    state = printLoggingInAsAdmin(scanner);
                    break;
                }

                case LOGGED_IN_AS_ACCOUNTANT: {
                    state = printLoggedInAsAccountant(scanner, state);
                    break;
                }

                case LOGGED_IN_AS_ADMIN: {
                    state = printLoggedInAsAdmin(scanner, state);
                    break;
                }

                case CREATING_COMPANY: {
                    state = printCreatingCompany(scanner);
                    break;
                }

                case CREATING_ADMIN: {
                    state = printCreatingAdmin(scanner);
                    break;
                }

                case DELETING_ADMIN: {
                    state = printDeletingAdmin(scanner);
                    break;
                }

                case DELETING_COMPANY: {
                    state = printDeletingCompany(scanner);
                    break;
                }

                case CREATING_ACCOUNTANT: {
                    state = printCreatingAccountant(scanner);
                    break;
                }

                case DELETING_ACCOUNTANT: {
                    state = printDeletingAccountant(scanner);
                    break;
                }

                case CREATING_ACCOUNTANT_COMPANY_ASSOCIATION: {
                    state = printCreatingAccountantCompanyAssociation(scanner);
                    break;
                }

                case CHANGE_COMPANY: {
                    state = printChangeCompany(scanner, state);
                    break;
                }

                case CREATING_INVOICE: {
                    state = printCreatingInvoice(scanner);
                    break;
                }
            }
        }

    }

    private static State printInit(Scanner scanner, State state) throws ClassNotFoundException, AccountantPasswordIsToShort, AccountantWrongLogin, AccountantAlreadyExistException, IOException {
        try {
            AccountantController.readAccountant();
        } catch (ClassNotFoundException | AccountantPasswordIsToShort | AccountantAlreadyExistException | IOException | AccountantWrongLogin e) {
            e.getMessage();
        }
        AdminController.readAdmin();
        CompanyController.readCompany();

        try {
            AccountantCompanyAssociationController.readAccountantCompanyAssociation();
        } catch (AccountantCompanyAssociationAlreadyExistException | AccountantNotFoundException | CompanyNotFoundException e) {
            System.out.println(e.getMessage());
        }

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

    // zalogowany ksiegowy
    private static Accountant [] accountants1 = new Accountant [1];
    private static State printLoggingInAsAccountant(Scanner scanner) {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        try {
            Accountant currentAccountant = AccountantRegistry.getInstance().findAccountant(login, password);
            System.out.println("Dzień dobry " + currentAccountant.getLogin());
            accountants1[0] = currentAccountant;
            return State.LOGGED_IN_AS_ACCOUNTANT;

        } catch (AccountantNotFoundException e) {
            System.out.println(e.getMessage());
            return State.INIT;
        }
    }

    private static State printLoggingInAsAdmin(Scanner scanner) {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        try {
            Admin currentAdmin = AdminRegistry.getInstance().findAdmin(login, password);
            System.out.println("Dzień dobry " + currentAdmin.getLogin());
            return State.LOGGED_IN_AS_ADMIN;

        } catch (AdminNotFoundException e) {
            System.out.println("Zły login lub hasło");
            return State.INIT;
        }
    }

    private static State printLoggedInAsAccountant(Scanner scanner, State state) {

        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkie firmy");
        System.out.println(" 2 - wypisać Twoje firmy");
        System.out.println(" 3 - dodac fakture");
        System.out.println(" 4 - menu");
        System.out.println(" 0 - wyjść z programu");

        switch (scanner.nextInt()) {

            case 1:
                CompanyController.listCompanies();
                state = State.LOGGED_IN_AS_ACCOUNTANT;
                scanner.nextLine();
                break;

            case 2:
                List<Company> companyList = new ArrayList<>();
                for (AccountantCompanyAssociation accountantCompanyAssociation : AccountantCompanyAssociationRegistry.getInstance().getAccountantCompanyAssociations()) {
                    if (accountantCompanyAssociation.getAccountantLogin().equals(accountants1[0].getLogin())) {
                        try {
                            companyList.add(CompanyRegistry.getInstance().findCompanyByNip(accountantCompanyAssociation.getNip()));
                        } catch (CompanyNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                CompanyView.printCompanies(companyList);
                state = State.LOGGED_IN_AS_ACCOUNTANT;
                scanner.nextLine();
                break;

            case 3:
                state = CREATING_INVOICE;
                scanner.nextLine();
                break;

            case 4:
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

    private static State printLoggedInAsAdmin(Scanner scanner, State state) throws IOException, ClassNotFoundException {

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
                state = State.CHANGE_COMPANY;
                break;

            case 11:
                state = State.CREATING_ACCOUNTANT_COMPANY_ASSOCIATION;
                scanner.nextLine();
                break;

            case 12:
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

    private static State printCreatingCompany(Scanner scanner) {
        System.out.println("Podaj nazwę nowej firmy:");
        String name = scanner.nextLine();
        System.out.println("Podaj rok założenia nowej firmy:");
        int yearFound = scanner.nextInt();
        scanner.nextLine();
        boolean goodYearFoun = false;
        while (!goodYearFoun) {
            try {
                if (CompanyRegistry.getInstance().searchIfCompanyYearOfFoundIsGood(yearFound)) {
                    goodYearFoun = true;
                }

            } catch (CompanyWrongYearFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Podaj rok założenia nowej firmy:");
                yearFound = scanner.nextInt();
                scanner.nextLine();
            }
        }
        boolean isAccurate = false;

        System.out.println("Podaj nip: ");

        String nip = scanner.nextLine();

        while (!isAccurate) {


            try {
                if (CompanyRegistry.getInstance().validateNIP(nip)) {
                    CompanyController.createCompany(nip, name, yearFound);
                    isAccurate = true;
                }
            } catch (ValidateNipException e) {
                System.out.println(e.getMessage());
                System.out.println("Podaj nip: ");
                nip = scanner.nextLine();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return State.LOGGED_IN_AS_ADMIN;

    }

    private static State printCreatingAdmin(Scanner scanner) throws IOException {
        System.out.println("Podaj login nowego admina:");
        String login = scanner.nextLine();
        System.out.println("Podaj haslo nowego admina:");
        String password = scanner.nextLine();
        AdminController.createAdmin(login, password);
        return State.LOGGED_IN_AS_ADMIN;
    }

    private static State printDeletingAdmin(Scanner scanner) throws IOException {
        System.out.println("Podaj login admina do usunięcia:");
        String login = scanner.nextLine();
        AdminController.removeAdmin(login);
        return State.LOGGED_IN_AS_ADMIN;
    }

    private static State printDeletingCompany(Scanner scanner) throws IOException {
        System.out.println("Podaj NIP firmy do usunięcia:");
        String nip = scanner.nextLine();

        try {
            CompanyController.removeCompany(nip);
        } catch (CompanyNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return State.LOGGED_IN_AS_ADMIN;
    }

    private static State printCreatingAccountant(Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.println("Podaj login nowego ksiegowego:");
        String login = scanner.nextLine();
        System.out.println("Podaj haslo: ");
        String password = scanner.nextLine();

        try {
            AccountantController.createAccountant(login, password);

        } catch (AccountantAlreadyExistException | AccountantPasswordIsToShort | AccountantWrongLogin e) {
            System.out.println(e.getMessage());

        }
        return State.LOGGED_IN_AS_ADMIN;
    }

    private static State printDeletingAccountant(Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.println("Podaj login ksiegowego do usuniecia: ");
        String login = scanner.nextLine();
        try {
            AccountantController.removeAccountant(login);
        } catch (AccountantNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return State.LOGGED_IN_AS_ADMIN;
    }

    private static State printCreatingAccountantCompanyAssociation(Scanner scanner) {
        System.out.println("Podaj login ksiegowego:");
        String login = scanner.nextLine();
        System.out.println("Podaj nip firmy:");
        String nip = scanner.nextLine();

        try {
            AccountantCompanyAssociationController.createAccountantCompanyAssociation(login, nip);
        } catch (AccountantCompanyAssociationAlreadyExistException | AccountantNotFoundException | CompanyNotFoundException | IOException | AccountantPasswordIsToShort | AccountantWrongLogin | ClassNotFoundException | AccountantAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
        return State.LOGGED_IN_AS_ADMIN;
    }

    private static State printChangeCompany(Scanner scanner, State state) throws IOException {
        System.out.println("Co chcesz zmienic w firmie ? ");
        System.out.println("1 -   zmienic NIP firmy ");
        System.out.println("2 -   zmienic nazwe firmy ");

        switch (scanner.nextInt()) {
            case 1:
                scanner.nextLine();
                System.out.println("Podaj stary nip firmy");
                String oldNip = scanner.nextLine();

                try {
                    Company company = CompanyRegistry.getInstance().findCompanyByNip(oldNip);
                    System.out.println("Podaj nowy nip firmy");
                    String newNip = scanner.nextLine();
                    CompanyRegistry.getInstance().uiForChangingNip(company, newNip);

                } catch (CompanyNotFoundException | ValidateNipException e) {
                    System.out.println(e.getMessage());
                }

                state = State.LOGGED_IN_AS_ADMIN;
                break;


            case 2:
                scanner.nextLine();
                System.out.println("Podaj nazwe firmy ktora chcesz zmienic");
                String name = scanner.nextLine();
                try {
                    Company company = CompanyRegistry.getInstance().findCompanyByName(name);
                    System.out.println("Podaj nowa nazwe firmy");
                    String newName = scanner.nextLine();
                    CompanyRegistry.getInstance().uiForChangingName(company, newName);
                } catch (CompanyNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                state = State.LOGGED_IN_AS_ADMIN;
                break;

        }

        return state;
    }

    private static State printCreatingInvoice(Scanner scanner) {

        boolean choice = false;
        String type = "";

        while (!choice) {
            System.out.println("1 - Sprzedaz / 2 - Zakup");
            int type1 = scanner.nextInt();
            scanner.nextLine();
            if (type1 == 1) {
                type = "Sprzedaz";
                choice = true;
            } else if (type1 == 2) {
                type = "Zakup";
                choice = true;
            } else {
                System.out.println("Zly wybor");
            }
        }

        System.out.println("Podaj kwote netto");

        BigDecimal howMuch = BigDecimal.valueOf(scanner.nextInt());
        scanner.nextLine();
        double vat = 0;

        while (choice) {
            System.out.println("Wybierz stawke vat: 1 - 8% / 2 - 23%");
            int whichVat = scanner.nextInt();
            scanner.nextLine();
            if (whichVat == 1) {
                vat = 0.08;
                choice = false;
            } else if (whichVat == 2) {
                vat = 0.23;
                choice = false;
            } else {
                System.out.println("Zly wybor");
            }
        }

        boolean paid = false;

        while (!choice) {
            System.out.println("1 - Zaplacono / 2- Niezaplacono");
            int paidOrNot = scanner.nextInt();
            scanner.nextLine();

            if (paidOrNot == 1) {
                paid = true;
                choice = true;
            } else if (paidOrNot == 2) {
                choice = true;
            } else {
                System.out.println("Zly wybor");
            }
        }

        while (choice) {

            System.out.println("Czy chcesz zrobic fakture dla firmy - 1 czy dla klienta - 2");
            int choose = scanner.nextInt();
            scanner.nextLine();
            if (choose == 1) {
                System.out.println("Podaj nazwe firmy: ");
                String name = scanner.nextLine();
                boolean exist = false;
                CompanyRegistry companyRegistry = CompanyRegistry.getInstance();

                try {
                    if (CompanyRegistry.getInstance().findCompanyByNip(name) != null) {
                        Company company = CompanyRegistry.getInstance().findCompanyByNip(name);
                        InvoiceController.createInvoiceForComapny(type, howMuch, vat, paid, company);
                        InvoiceController.invoiceForCompanyList(company);
                    }

                } catch (CompanyNotFoundException e) {
                    e.getMessage();
                }
                choice = false;

            } else if (choose == 2) {
                System.out.println("Podaj swoj Nip");
                String nip = scanner.nextLine();
                if (ClientRegistry.getInstance().findClientByNip(nip) == null) {
                    System.out.println("Podaj swoje imie");
                    String name = scanner.nextLine();
                    System.out.println("Podaj swoje nazwisko");
                    String surname = scanner.nextLine();
                    ClientController.createClient(name, surname, nip);
                    Client client = ClientRegistry.getInstance().findClientByNip(nip);
                    InvoiceController.createInvoiceForClient(type, howMuch, vat, paid, client);

                } else {
                    boolean correctClient = false;
                    while (!correctClient) {
                        System.out.println("Jesli to wlasciwy client wybierz 1 jesli nie wybierz 2 " + ClientRegistry.getInstance().findClientByNip(nip));
                        Client client = ClientRegistry.getInstance().findClientByNip(nip);
                        int correctOrNot = scanner.nextInt();
                        scanner.nextLine();
                        if (correctOrNot == 2) {
                            break;
                        } else if (correctOrNot == 1) {
                            InvoiceController.createInvoiceForClient(type, howMuch, vat, paid, client);
                            correctClient = true;

                        } else {
                            System.out.println("Zly wybor");
                        }
                    }
                }
                choice = false;
            }
        }

        return State.LOGGED_IN_AS_ACCOUNTANT;
    }

}
