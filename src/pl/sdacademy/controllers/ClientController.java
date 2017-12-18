package pl.sdacademy.controllers;

import pl.sdacademy.models.Client;
import pl.sdacademy.models.ClientRegistry;

/**
 * Created by marcin on 13.12.2017.
 */
public class ClientController {

    public static void createClient(String name, String surname, String nip){
        ClientRegistry.getInstance().addClient(new Client(name,surname,nip));
    }
}
