package pl.sdacademy;

import pl.sdacademy.controllers.*;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.io.IOException;

import static pl.sdacademy.Main.State.CREATING_INVOICE;
import static pl.sdacademy.controllers.AccountantController.saveAccountant;

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
                    try {
                        AccountantController.readAccountant();
                    } catch (ClassNotFoundException | AccountantPasswordIsToShort | AccountantAlreadyExistException | IOException | AccountantWrongLogin e) {
                        e.getMessage();
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
                    System.out.println(" 1 - wypisać wszystkie firmy");
                    System.out.println(" 2 - dodac fakture");
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
                    System.out.println(" 10 - zmienić nazwe lub nip firmy");
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
                            try {
                                saveAccountant();
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            break;

                        case 7:
                            state = State.DELETING_ACCOUNTANT;
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
                        case 0:
                            try {
                                saveAccountant();
                            } catch (IOException | ClassNotFoundException e) {
                                e.getMessage();
                            }
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

                    try {
                        CompanyController.removeCompany(nip);
                    } catch (CompanyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case CREATING_ACCOUNTANT: {
                    System.out.println("Podaj login nowego ksiegowego:");
                    String login = scanner.nextLine();
                    System.out.println("Podaj haslo: ");
                    String password = scanner.nextLine();

                    try {
                        AccountantController.createAccountant(login, password);

                    } catch (AccountantAlreadyExistException | AccountantPasswordIsToShort | AccountantWrongLogin e) {
                        System.out.println(e.getMessage());

                    }
                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case DELETING_ACCOUNTANT: {
                    System.out.println("Podaj login ksiegowego do usuniecia: ");
                    String login = scanner.nextLine();
                    try {
                        AccountantController.removeAccountant(login);
                    } catch (AccountantNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }

                case CHANGE_COMPANY: {
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

                            } catch (CompanyNotFoundException | ValidateNip e) {
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
                    break;
                }


                // write your code here

                case CREATING_INVOICE: {

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

                                } else {
                                    System.out.println("Nie ma takiej firmy");
                                }
                            } catch (CompanyNotFoundException e) {
                                e.printStackTrace();
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

                    state = State.LOGGED_IN_AS_ACCOUNTANT;
                    break;
                }
            }
        }

    }
}
