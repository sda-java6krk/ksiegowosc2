package pl.sdacademy.models;

import org.junit.Assert;
import org.junit.Test;
import pl.sdacademy.exceptions.AdminNotFoundException;

import java.rmi.registry.Registry;

import static org.junit.Assert.*;

public class AdminRegistryTest {
    @Test
    public void shouldAddAdmin() {
        Admin created = new Admin("Anna", "321");
        AdminRegistry.getInstance().addAdmin(created);

        Admin result = null;
        try {
            result = AdminRegistry.getInstance().findAdmin("Anna","321");
        } catch (AdminNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(created, result);
    }


}