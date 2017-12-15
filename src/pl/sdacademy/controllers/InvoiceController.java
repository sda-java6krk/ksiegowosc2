package pl.sdacademy.controllers;

import pl.sdacademy.models.Invoice;
import pl.sdacademy.models.InvoiceRegistry;

/**
 * Created by marcin on 13.12.2017.
 */
public class InvoiceController {

    public static void createInvoice(String type, double netAmount, double vat, boolean paid ){
        InvoiceRegistry.getInstance().addFacture(new Invoice(type,netAmount,vat,paid));
    }
}
