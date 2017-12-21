package pl.sdacademy.models;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.sdacademy.exceptions.AdminNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.rmi.registry.Registry;

import static org.junit.Assert.*;

public class AdminRegistryTest {
    @Test
    public void shouldAddAdmin() {
        Admin created = new Admin("Anna", "321");
        AdminRegistry.getInstance().addAdmin(created);

        Admin result = null;
        try {
            result = AdminRegistry.getInstance().findAdmin("Anna", "321");
        } catch (AdminNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(created, result);
    }

    @Test(expected = AdminNotFoundException.class)
    public void shouldRemoveAdmin() throws AdminNotFoundException {
        Admin created = new Admin("Anna", "321");
        AdminRegistry.getInstance().addAdmin(created);

        AdminRegistry.getInstance().remove("Anna");
        AdminRegistry.getInstance().findAdmin("Anna", "321");
    }

    @Test
    public void shouldNotAddAdminBadLogin() {
        Admin created = new Admin("  ", "321");
        AdminRegistry.getInstance().addAdmin(created);

        Admin result = null;
        try {
            result = AdminRegistry.getInstance().findAdmin("  ", "321");
        } catch (AdminNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNotEquals(created, result);
    }

    @Test
    public void shouldNotAddAdminBadPassword() {
        Admin created = new Admin("Anna", " 12");
        AdminRegistry.getInstance().addAdmin(created);

        Admin result = null;
        try {
            result = AdminRegistry.getInstance().findAdmin("Anna", " 12");
        } catch (AdminNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNotEquals(created, result);
    }
    @Test
    public void shouldNotAddCopyAdmin() {
        Admin created = new Admin("Anna", "123");
        AdminRegistry.getInstance().addAdmin(created);

        Admin result = null;
        try {
         AdminRegistry.getInstance().addAdmin(created);
         AdminRegistry.getInstance().findAdmin("Anna", "123");
        } catch (AdminNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNotEquals(created, result);
    }


}