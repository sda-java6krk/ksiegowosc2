package pl.sdacademy.models;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRegistry {

    private static InvoiceRegistry instance = null;
    public static InvoiceRegistry getInstance() {
        if (instance == null) {
            instance = new InvoiceRegistry();
        }
        return instance;
    }
    List <Invoice> invoices = new ArrayList<>();

    public void addFacture(Invoice invoice){

    }
}
