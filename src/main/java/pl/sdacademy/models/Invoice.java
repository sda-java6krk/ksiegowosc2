package pl.sdacademy.models;

/**
 * Created by marcin on 13.12.2017.
 */
public class Invoice {

    private String type;
    private double netAmmount;
    private double vat;
    private boolean paid;
    private static int idSale = 0;
    private static int idBuy = 0;
    private int invoiceIdSale;
    private int invoiceIdBuy;



    public Invoice(String type, double netAmmount, double vat, boolean paid) {
        this.type = type;
        this.netAmmount = netAmmount;
        this.vat = vat;
        this.paid = paid;
        if (type.equals("Sprzedaz")) {

            invoiceIdSale = idSale;
            idSale++;
        } else if (type.equals("Zakup")) {

            invoiceIdBuy = idBuy;
            idBuy++;
        }
    }

    public String getType() {
        return type;
    }

    public double getNetAmmount() {
        return netAmmount;
    }

    public double getVat() {
        return vat;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        if (type.equals("Zakup")) {
            return "Invoice{" +
                    "type='" + type + '\'' +
                    ", netAmmount=" + netAmmount +
                    ", vat=" + vat +
                    ", paid=" + paid +
                    ", invoiceId=" + invoiceIdBuy +
                    '}';
        }
        return "Invoice{" +
                "type='" + type + '\'' +
                ", netAmmount=" + netAmmount +
                ", vat=" + vat +
                ", paid=" + paid +
                ", invoiceId=" + invoiceIdSale +
                '}';
    }
}