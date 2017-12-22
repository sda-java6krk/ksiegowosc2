package pl.sdacademy.userInterface;

import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.ValidateNip;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;

import java.util.Scanner;

public class UserInterfaceChangeCompany {

    public static State getStateChangeCompany(Scanner scanner, State state) {
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

        return state;
    }
}
