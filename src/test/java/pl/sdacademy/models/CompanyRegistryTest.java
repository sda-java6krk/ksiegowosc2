package pl.sdacademy.models;

import org.junit.Assert;
import org.junit.Test;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.ValidateNip;

import static org.junit.Assert.*;

public class CompanyRegistryTest {

    @Test
    public void shouldFindCompanyForNip() throws ValidateNip, CompanyNotFoundException {
        Company create = new Company("1234567890", "", 1);
        CompanyRegistry.getInstance().add(create);

        Company result = CompanyRegistry.getInstance().findCompanyByNip("1234567890");

        Assert.assertEquals(create, result);
    }

    @Test(expected = CompanyNotFoundException.class)
    public void shouldNotFindCompanyForMissingNip() throws ValidateNip, CompanyNotFoundException {
        Company create = new Company("77322869821", "", 1);
        CompanyRegistry.getInstance().add(create);

        Company result = CompanyRegistry.getInstance().findCompanyByNip("00000000");

        Assert.assertNull(result);
    }

    @Test
    public void shouldChangeNipForCompany() throws ValidateNip, CompanyNotFoundException {
        Company create = new Company("77322869821", "", 1234);
        String newNip = "5649871236";
        CompanyRegistry.getInstance().add(create);
        CompanyRegistry.getInstance().changeNipForCompany(create, newNip);
        Company companyWithNewNip = CompanyRegistry.getInstance().findCompanyByNip(newNip);
        assertNotNull(companyWithNewNip);
    }

    @Test
    public void shouldChangeNameForCompany() throws ValidateNip, CompanyNotFoundException {
        Company create = new Company("77322869821", "AAA", 2001);
        String newName = "BBB";
        CompanyRegistry.getInstance().add(create);

        CompanyRegistry.getInstance().changeNipForCompany(create, newName);

        Company companyWithNewName = CompanyRegistry.getInstance().findCompanyByNip(newName);
        assertNotNull(companyWithNewName);
    }

    @Test
    public void shouldAddCompany() throws ValidateNip {
        Company create = new Company("77322869821", "AAA", 2000);
        CompanyRegistry.getInstance().add(create);
        assertTrue(CompanyRegistry.getInstance().getCompanies().contains(create));
         CompanyRegistry.getInstance().getCompanies().clear();
    }
    @Test
    public void shouldRemoveCompanyForNip() throws ValidateNip, CompanyNotFoundException {
        Company create = new Company("77322869821", "AAA", 2002);
        CompanyRegistry.getInstance().add(create);
        CompanyRegistry.getInstance().remove(create.getNip());
        assertFalse(CompanyRegistry.getInstance().getCompanies().contains(create));

        CompanyRegistry.getInstance().getCompanies().clear();
    }
    @Test(expected = ValidateNip.class)
    public void shouldValidateNipByLengthLongest() throws ValidateNip {
        String create = "773228698211";
        CompanyRegistry.getInstance().validateNIP(create);
    }
    @Test(expected = ValidateNip.class)
    public void shouldValidateNipByLengthShorts() throws ValidateNip {
        String create = "7732286982";
        CompanyRegistry.getInstance().validateNIP(create);
    }
    @Test(expected = ValidateNip.class)
    public void shouldNotContainSpaces() throws ValidateNip {
        String create = "77322869 21";
        CompanyRegistry.getInstance().validateNIP(create);
    }
    @Test(expected = ValidateNip.class)
    public void shouldValidateByChecksum() throws ValidateNip {
        String create = "77322869822";
        CompanyRegistry.getInstance().validateNIP(create);
    }


}