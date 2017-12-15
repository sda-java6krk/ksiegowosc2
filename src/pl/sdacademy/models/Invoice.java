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
   private int invoiceId;

    public Invoice(String type, double netAmmount, double vat, boolean paid) {
        this.type = type;
        this.netAmmount = netAmmount;
        this.vat = vat;
        this.paid = paid;
        if(type.equals("sprzedaz")){
            invoiceId = idSale;
            idSale++;
        }else if(type.equals("zakup")){
            invoiceId = idBuy;
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

}
