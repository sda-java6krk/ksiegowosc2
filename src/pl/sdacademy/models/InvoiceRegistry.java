package pl.sdacademy.models;

public class InvoiceRegistry {
    private static InvoiceRegistry instance = null;
    public static InvoiceRegistry getInstance() {
        if (instance == null) {
            instance = new InvoiceRegistry();
        }
        return instance;
    }
    public void addFacture(Invoice invoice){

    }
}
