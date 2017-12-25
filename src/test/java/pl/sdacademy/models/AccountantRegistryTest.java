package pl.sdacademy.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.sdacademy.exceptions.AccountantAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.AccountantPasswordIsToShort;
import pl.sdacademy.exceptions.AccountantWrongLogin;

import static org.junit.Assert.*;

public class AccountantRegistryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void after() {
        AccountantRegistry.getInstance().getAccountants().clear();
    }

    @Before
    public void before() {
        AccountantRegistry.getInstance().getAccountants().clear();
    }

    @Test
    public void shouldAddAccountant() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort {
        //given
        Accountant accountant = new Accountant("Anna", "321");
        //when
        AccountantRegistry.getInstance().addAccountant(accountant);
        //then
        assertTrue(AccountantRegistry.getInstance().getAccountants().contains(accountant));
    }

    @Test
    public void shouldRemoveAccountant() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
        Accountant accountant = new Accountant("Anna", "321");
        //when
        AccountantRegistry.getInstance().addAccountant(accountant);
        AccountantRegistry.getInstance().removeAccountant("Anna");
        //then
        assertFalse(AccountantRegistry.getInstance().getAccountants().contains(accountant));
    }

    @Test
    public void shouldFindAccountant() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
        Accountant accountant = new Accountant("Anna", "321");
        AccountantRegistry.getInstance().addAccountant(accountant);
        assertNotNull(AccountantRegistry.getInstance().findAccountant("Anna", "321"));
    }

    @Test
    public void shouldNotAddAccountantBadLogin() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
        thrown.expect(AccountantWrongLogin.class);
        Accountant accountant = new Accountant("   ", "321");
        AccountantRegistry.getInstance().addAccountant(accountant);
    }

    @Test(expected = AccountantAlreadyExistException.class)
    public void shouldNotAddAccountantWhenExist() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
        Accountant accountant = new Accountant("Anna", "123");
        AccountantRegistry.getInstance().addAccountant(accountant);
        AccountantRegistry.getInstance().addAccountant(accountant);
    }

    @Test(expected = AccountantPasswordIsToShort.class)
    public void shouldNotAddAccountantPasswordToShort() throws AccountantAlreadyExistException, AccountantWrongLogin, AccountantPasswordIsToShort, AccountantNotFoundException {
        Accountant accountant = new Accountant("Anna", " 32");
        AccountantRegistry.getInstance().addAccountant(accountant);
    }
}

