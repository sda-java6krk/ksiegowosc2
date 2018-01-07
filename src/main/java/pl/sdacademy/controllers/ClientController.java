package pl.sdacademy.controllers;

import pl.sdacademy.models.Client;
import pl.sdacademy.models.ClientRegistry;

public class ClientController {

    public static void createClient(String name, String surname, String nip){
        ClientRegistry.getInstance().addClient(new Client(name,surname,nip));
    }
}
