package pl.sdacademy.models;

import org.junit.Assert;
import org.junit.Test;
import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.exceptions.ClientNotFoundException;

import static org.junit.Assert.*;

public class ClientRegistryTest {
    @Test
    public void shouldFindClientByNip() throws Exception {
        Client client = new Client("Marcin", "Murek", "5272525995");
        ClientRegistry.getInstance().addClient(client);

        Client result = null;
        result = ClientRegistry.getInstance().findClientByNip("5272525995");

        Assert.assertEquals(client, result);
    }


}