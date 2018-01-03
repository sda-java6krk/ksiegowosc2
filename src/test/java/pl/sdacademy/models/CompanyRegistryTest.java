package pl.sdacademy.models;

import org.junit.Assert;
import org.junit.Test;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.ValidateNipException;

import static org.junit.Assert.*;

public class CompanyRegistryTest {

    @Test
    public void shouldFindCompanyForNip() throws ValidateNipException, CompanyNotFoundException {
        Company create = new Company("1234567890", "", 1);
        CompanyRegistry.getInstance().add(create);

        Company result = CompanyRegistry.getInstance().findCompanyByNip("1234567890");

        Assert.assertEquals(create, result);
    }

    @Test(expected = CompanyNotFoundException.class)
    public void shouldNotFindCompanyForMissingNip() throws ValidateNipException, CompanyNotFoundException {
        Company create = new Company("77322869821", "", 1);
        CompanyRegistry.getInstance().add(create);

        Company result = CompanyRegistry.getInstance().findCompanyByNip("00000000");

        Assert.assertNull(result);
    }

    @Test
    public void shouldChangeNipForCompany() throws ValidateNipException, CompanyNotFoundException {
        Company create = new Company("1234563218", "", 1900);
        String newNip = "9734876545";
        CompanyRegistry.getInstance().add(create);
        CompanyRegistry.getInstance().changeNipForCompany(create, newNip);
        Company companyWithNewNip = CompanyRegistry.getInstance().findCompanyByNip(newNip);
        assertNotNull(companyWithNewNip);
        Assert.assertEquals(companyWithNewNip,create);
    }

    @Test
    public void shouldChangeNameForCompany() throws CompanyNotFoundException {
        Company create = new Company("1234563218", "AAA", 2001);
        String newName = "BBB";
        CompanyRegistry.getInstance().add(create);
        CompanyRegistry.getInstance().changeNameForCompany(create, newName);
        Company companyWithNewName = CompanyRegistry.getInstance().findCompanyByName(newName);
        assertNotNull(companyWithNewName);
        Assert.assertEquals(companyWithNewName,create);
    }

    @Test
    public void shouldAddCompany() throws ValidateNipException {
        Company create = new Company("77322869821", "AAA", 2000);
        CompanyRegistry.getInstance().add(create);
        assertTrue(CompanyRegistry.getInstance().getCompanies().contains(create));
        CompanyRegistry.getInstance().getCompanies().clear();
    }
    @Test
    public void shouldRemoveCompanyForNip() throws ValidateNipException, CompanyNotFoundException {
        Company create = new Company("77322869821", "AAA", 2002);
        CompanyRegistry.getInstance().add(create);
        CompanyRegistry.getInstance().remove(create.getNip());
        assertFalse(CompanyRegistry.getInstance().getCompanies().contains(create));
        CompanyRegistry.getInstance().getCompanies().clear();
    }
    @Test(expected = ValidateNipException.class)
    public void shouldValidateNipByLengthLongest() throws ValidateNipException {
        String create = "773228698211";
        CompanyRegistry.getInstance().validateNIP(create);
    }
    @Test(expected = ValidateNipException.class)
    public void shouldValidateNipByLengthShorts() throws ValidateNipException {
        String create = "7732286982";
        CompanyRegistry.getInstance().validateNIP(create);
    }
    @Test(expected = ValidateNipException.class)
    public void shouldNotContainSpaces() throws ValidateNipException {
        String create = "77322869 21";
        CompanyRegistry.getInstance().validateNIP(create);
    }
    @Test(expected = ValidateNipException.class)
    public void shouldValidateByChecksum() throws ValidateNipException {
        String create = "77322869822";
        CompanyRegistry.getInstance().validateNIP(create);
    }


}