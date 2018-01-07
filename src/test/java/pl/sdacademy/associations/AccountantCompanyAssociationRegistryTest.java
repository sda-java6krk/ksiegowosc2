package pl.sdacademy.associations;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.sdacademy.exceptions.AccountantCompanyAssociationAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;

import static org.junit.Assert.assertTrue;

public class AccountantCompanyAssociationRegistryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        AccountantCompanyAssociationRegistry.getInstance().getAccountantCompanyAssociations().clear();
        CompanyRegistry.getInstance().getCompanies().clear();
    }

    @After
    public void after() {
        AccountantCompanyAssociationRegistry.getInstance().getAccountantCompanyAssociations().clear();
        CompanyRegistry.getInstance().getCompanies().clear();
    }

    @Test
    public void shouldNotAddAccountantCompanyAssociationByWrongLogin() throws Exception {
        thrown.expect(AccountantNotFoundException.class);
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(new AccountantCompanyAssociation("Tadeusz", "3623981230"));
    }

    @Test
    public void shouldNotAddAccountantCompanyAssociationByWrongNip() throws Exception {
        thrown.expect(CompanyNotFoundException.class);
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(new AccountantCompanyAssociation("tomasz", "5252674798"));
    }

    @Test
    public void shouldNotAddAccountantCompanyAssociationByDuplicate() throws Exception {
        thrown.expect(AccountantCompanyAssociationAlreadyExistException.class);
        CompanyRegistry.getInstance().add(new Company("3623981231", "companyName", 1999));
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(new AccountantCompanyAssociation("tomasz", "3623981231"));
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(new AccountantCompanyAssociation("tomasz", "3623981231"));
    }

    @Test
    public void shouldAddAccountantCompanyAssociation() throws Exception {
        CompanyRegistry.getInstance().add(new Company("3623981231", "companyName", 1999));
        AccountantCompanyAssociation association = new AccountantCompanyAssociation("tomasz", "3623981231");
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(association);
        assertTrue(AccountantCompanyAssociationRegistry.getInstance().getAccountantCompanyAssociations().contains(association));
    }

}