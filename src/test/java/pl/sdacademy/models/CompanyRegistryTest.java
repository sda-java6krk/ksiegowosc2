package pl.sdacademy.models;

import org.junit.Assert;
import org.junit.Test;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.ValidateNip;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompanyRegistryTest {
    @Test
    public void shouldFindCompanyForNip() {
        Company created = new Company("1234567890", "", 1);
        try {
            CompanyRegistry.getInstance().add(created);
        } catch (ValidateNip validateNip) {
            validateNip.printStackTrace();
        }

        Company result = null;
        try {
            result = CompanyRegistry.getInstance().findCompanyByNip("1234567890");
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(created, result);
    }

    @Test
    public void shouldNotFindCompanyForMissingNip() {
        Company created = new Company("1234567890", "", 1);
        try {
            CompanyRegistry.getInstance().add(created);
        } catch (ValidateNip validateNip) {
            validateNip.printStackTrace();
        }

        Company result = null;
        try {
            result = CompanyRegistry.getInstance().findCompanyByNip("00000000");
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(result);
    }

    @Test
    public void shouldChangeNipForCompany() {
        Company create = new Company("1245789632", "", 1234);
        String newNip = "5649871236";
        try {
            CompanyRegistry.getInstance().add(create);
        } catch (ValidateNip validateNip) {
            validateNip.printStackTrace();
        }
        CompanyRegistry.getInstance().changeNipForCompany(create, newNip);
        Company companyWithNewNip = null;
        try {
            companyWithNewNip = CompanyRegistry.getInstance().findCompanyByNip(newNip);
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(companyWithNewNip);
    }

    @Test
    public void shouldChangeNameForCompany() {
        Company create = new Company("1234567896", "AAA", 1234);
        String newName = "BBB";
        try {
            CompanyRegistry.getInstance().add(create);
        } catch (ValidateNip validateNip) {
            validateNip.printStackTrace();
        }

        CompanyRegistry.getInstance().changeNipForCompany(create, newName);

        Company companyWithNewName = null;
        try {
            companyWithNewName = CompanyRegistry.getInstance().findCompanyByNip(newName);
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(companyWithNewName);
    }

    @Test
    public void shouldAddCompany() {
        Company create = new Company("1234567896", "AAA", 1236);
        List<Company> companies = new ArrayList<>();
        try {
            CompanyRegistry.getInstance().add(create);
        } catch (ValidateNip validateNip) {
            validateNip.printStackTrace();
        }
        for (Company company : companies) {
            if (company.getNip().equals(create.getNip())) {
                assertNotNull(create);
            } else {
                assertNotNull(null);
            }
        }
    }

    @Test
    public void shouldRemoveCompanyForNip() {
        Company create = new Company("1234567896", "AAA", 1236);
        List<Company> companies = new ArrayList<>();
        try {
            CompanyRegistry.getInstance().add(create);
        } catch (ValidateNip validateNip) {
            validateNip.printStackTrace();
        }
        try {
            CompanyRegistry.getInstance().remove(create.getNip());
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
        for (Company company : companies) {
            if (company.getNip().equals(create.getNip())) {
                assertNull(create);
            } else {
                assertNull(null);
            }
        }
    }

    @Test
    public void shouldValidateNipByLength() {
        String create = "1234567896";
        int LengthCreate = create.length();
        assertEquals(10, LengthCreate);
    }

    @Test
    public void shouldValidateNipBySpice() {
        String create = "1234567896";
        int LengthCreate = create.length();
        assertEquals(10, LengthCreate);
    }

    @Test
    public void shouldValidateNipBy() {
        String create = "1234567896";
        int LengthCreate = create.length();
        assertEquals(10, LengthCreate);
    }
}