package pl.sdacademy.models;

import org.junit.Test;
import pl.sdacademy.exceptions.AccountantAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.AccountantPasswordIsToShort;
import pl.sdacademy.exceptions.AccountantWrongLogin;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountantRegistryTest {

    Accountant accountant;
    List<Accountant> accountants;
    @Test
    public void shouldAddAccountant() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort {
        accountant = new Accountant("Anna", "321");
         accountants = new ArrayList<>();
        AccountantRegistry.getInstance().addAccountant(accountant);
        for (Accountant accountantes : accountants) {
            if (accountantes.getLogin().equals(accountant.getLogin())) {
                assertNotNull(accountant);
            } else {
                assertNotNull(null);
            }
        } AccountantRegistry.getInstance().getAccountants().clear();
    }

    @Test
    public void shouldRemoveAccountant() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort {
         accountant = new Accountant("Anna", "321");
         accountants = new ArrayList<>();
        AccountantRegistry.getInstance().addAccountant(accountant);
        for (Accountant accountantes : accountants) {
            if (accountantes.getLogin().equals(accountant.getLogin())) {
                assertNull(null);
            } else {
                assertNull(accountant);
            }
        } AccountantRegistry.getInstance().getAccountants().clear();
    }

    @Test
    public void shouldFindAccountant() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
         accountant = new Accountant("Anna", "321");
         accountants = new ArrayList<>();
        AccountantRegistry.getInstance().addAccountant(accountant);
        Accountant rezult = AccountantRegistry.getInstance().findAccountant("Anna", "321");
        assertNotNull(rezult);
        AccountantRegistry.getInstance().getAccountants().clear();
    }
     @Test(expected = AccountantWrongLogin.class)
    public void shouldNotFindAccountantBadLogin() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
         accountant = new Accountant("Anna", "321");
         accountants = new ArrayList<>();
        AccountantRegistry.getInstance().addAccountant(accountant);
        AccountantRegistry.getInstance().findAccountant("An_a", "321");
        AccountantRegistry.getInstance().getAccountants().clear();
    }
    @Test(expected = AccountantAlreadyExistException.class)
    public void shouldNotFindAccountantBadPassword() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
         accountant = new Accountant("Anna", "321");
         accountants = new ArrayList<>();
        AccountantRegistry.getInstance().addAccountant(accountant);
        AccountantRegistry.getInstance().findAccountant("Anna", "123");
        AccountantRegistry.getInstance().getAccountants().clear();
    }
    @Test(expected = AccountantPasswordIsToShort.class)
    public void shouldNotFindAccountantPasswordToShort() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
         accountant = new Accountant("Anna", "321");
         accountants = new ArrayList<>();
        AccountantRegistry.getInstance().addAccountant(accountant);
        AccountantRegistry.getInstance().findAccountant("Anna", "32 ");
        AccountantRegistry.getInstance().getAccountants().clear();
    }
}

