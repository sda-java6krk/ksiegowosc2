package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.AccountantPasswordIsToShort;
import pl.sdacademy.exceptions.AccountantWrongLogin;

import java.util.HashSet;
import java.util.Set;

public class AccountantRegistry {
    private static AccountantRegistry instance = null;
    private Set<Accountant> accountants;

    public AccountantRegistry() {
        this.accountants = new HashSet<>();

        this.accountants.add(new Accountant("tomasz", "123"));
        this.accountants.add(new Accountant("marek", "123"));
    }

    public static AccountantRegistry getInstance() {
        if (instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }

    public void addAccountant(Accountant accountant) throws AccountantAlreadyExistException,AccountantPasswordIsToShort,AccountantWrongLogin {

            if (accountant.getLogin().trim().length() < 1) {
                throw new AccountantWrongLogin("Nie moze byc pusty login! ");

            }
            if (accountants.contains(accountant)) {
                throw new AccountantAlreadyExistException("podany login jest zajety");
            } else {
                if (accountant.getPassword().trim().length() < 3) {
                    throw new AccountantPasswordIsToShort("Podane haslo jest za krotkie, musi sie skladac z przynajmniej 3 znakow");
                } else {
                    String login = accountant.getLogin().split(" ")[ 0 ];
                    String password = accountant.getPassword();
                    accountant = new Accountant(login, password);
                    accountants.add(accountant);

                }
            }
        }

    public void removeAccountant(String login) {
        boolean removed = false;

      for(Accountant accountant : accountants) {
          if (accountant.getLogin().equals(login)) {
              accountants.remove(accountant);
              removed = true;
              break;
          }
      }
        if (!removed) {
            System.out.println("Nie ma takiego ksiegowego");
        }
    }

    public Accountant findAccountant(String login, String password) throws AccountantNotFoundException {
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login) && accountant.getPassword().equals(password)) {
                return accountant;
            }
        }

        throw new AccountantNotFoundException("Zły login lub hasło");

    }

    public Set<Accountant> getAccountants() {
        return accountants;
    }


}