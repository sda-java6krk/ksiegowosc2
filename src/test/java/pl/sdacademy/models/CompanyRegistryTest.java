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
    public void shouldFindCompanyForNip() throws ValidateNip, CompanyNotFoundException {
        Company created = new Company("1234567890", "", 1);
        CompanyRegistry.getInstance().add(created);

        Company result = CompanyRegistry.getInstance().findCompanyByNip("1234567890");

        Assert.assertEquals(created, result);
    }

    @Test
    public void shouldNotFindCompanyForMissingNip() throws ValidateNip, CompanyNotFoundException {
        Company created = new Company("1234567890", "", 1);
        CompanyRegistry.getInstance().add(created);

        Company result = CompanyRegistry.getInstance().findCompanyByNip("00000000");

        Assert.assertNull(result);
    }

    @Test
    public void shouldChangeNipForCompany() throws ValidateNip, CompanyNotFoundException {
        Company create = new Company("1245789632", "", 1234);
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
        List<Company> companies = new ArrayList<>();
        CompanyRegistry.getInstance().add(create);
        for (Company company : companies) {
            if (company.getNip().equals(create.getNip())) {
                assertNotNull(create);
            } else {
                assertNotNull(null);
            }
        }
    }
    @Test
    public void shouldRemoveCompanyForNip() throws ValidateNip, CompanyNotFoundException {
        Company create = new Company("77322869821", "AAA", 2002);
        List<Company> companies = new ArrayList<>();
        CompanyRegistry.getInstance().add(create);
        CompanyRegistry.getInstance().remove(create.getNip());
        for (Company company : companies) {
            if (company.getNip().equals(create.getNip())) {
                assertNull(create);
            } else {
                assertNull(null);
            }
        }
    }
    @Test
    public void shouldValidateNipByLength(){
        String create = "77322869821";
        int LengthCreate = create.length();
        assertEquals(10,LengthCreate);
    }
    @Test
    public void shouldValidateNipBySpice(){
        String create = "77322869821";
        int LengthCreate = create.length();
        assertEquals(10,LengthCreate);
    }
    @Test
    public void shouldValidateNipBy(){
        String create = "77322869821";
        int LengthCreate = create.length();
        assertEquals(10,LengthCreate);
    }


}