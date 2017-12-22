package pl.sdacademy;

import pl.sdacademy.controllers.*;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.*;
import pl.sdacademy.userInterface.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException, ClassNotFoundException, AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort {
        State state = State.INIT;
        Scanner scanner = new Scanner(System.in);

        Admin currentAdmin = null;
        Accountant currentAccountant = null;

        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
                    state = printInit(scanner, state);
                    break;
                }

                case LOGGING_IN_AS_ACCOUNTANT: {
                    state = printLoggingInAsAccountant(scanner, currentAccountant);
                    break;
                }

                case LOGGING_IN_AS_ADMIN: {
                    state = printLoggingInAsAdmin(scanner, currentAdmin);
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
        return UserInterfaceMenu.getStateMenu(scanner);
    }

    private static State printLoggingInAsAccountant(Scanner scanner, Accountant currentAccountant) {
        return UserInterfaceLoggingInAsAccountant.getStateLoggingInAsAccountant(scanner);
    }

    private static State printLoggingInAsAdmin(Scanner scanner, Admin currentAdmin) {
        return UserInterfaceLoggingInAsAdmin.getStateLoggingInAsAdmin(scanner);
    }



    private static State printLoggedInAsAccountant(Scanner scanner, State state) {

        return UserInterfaceLoggedInAsAccountant.getStateLoggedInAsAccountant(scanner);
    }



    private static State printLoggedInAsAdmin(Scanner scanner, State state) throws IOException, ClassNotFoundException {

        return UserInterfaceLoggedInAsAdmin.getStateLoggedInAsAdmin(scanner);
    }



    private static State printCreatingCompany(Scanner scanner) {
        return UserInterfaceCreatingCompany.getStateCreatingCompany(scanner);
    }



    private static State printCreatingAdmin(Scanner scanner) throws IOException {
        return UserInterfaceCreatingAdmin.getStateCreatingAdmin(scanner);
    }



    private static State printDeletingAdmin(Scanner scanner) throws IOException {
        return UserInterfaceDeletingAdmin.getStateDeletingAdmin(scanner);
    }



    private static State printDeletingCompany(Scanner scanner) {
        return UserInterfaceDeletingCompany.getStateDeletingCompany(scanner);
    }



    private static State printCreatingAccountant(Scanner scanner) throws IOException, ClassNotFoundException {
        return UserInterfaceCreatingAccountant.getStateCreatingAccountant(scanner);
    }



    private static State printDeletingAccountant(Scanner scanner) throws IOException, ClassNotFoundException {
        return UserInterfaceDeletingAccountant.getStateDeletingAccountant(scanner);
    }



    private static State printCreatingAccountantCompanyAssociation(Scanner scanner) {
        return UserInterfaceCreatingAccountantCompanyAssociation.getStateCreatingAccountantCompanyAssociation(scanner);

    }



    private static State printChangeCompany(Scanner scanner, State state) {
        return UserInterfaceChangeCompany.getStateChangeCompany(scanner, state);
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

        return State.LOGGED_IN_AS_ACCOUNTANT;
    }

}
