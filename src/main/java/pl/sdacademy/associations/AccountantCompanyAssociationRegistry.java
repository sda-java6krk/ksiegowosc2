package pl.sdacademy.associations;

import pl.sdacademy.controllers.AccountantCompanyAssociationController;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.models.Admin;
import pl.sdacademy.models.CompanyRegistry;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AccountantCompanyAssociationRegistry extends AccountantCompanyAssociation implements Serializable {

    private static AccountantCompanyAssociationRegistry instance = null;
    private Set<AccountantCompanyAssociation> accountantCompanyAssociations;


    public AccountantCompanyAssociationRegistry() {
        this.accountantCompanyAssociations = new HashSet<>();
    }

    public static AccountantCompanyAssociationRegistry getInstance() {
        if (instance == null) {
            instance = new AccountantCompanyAssociationRegistry();
        }
        return instance;
    }

    public void addAccountantCompanyAssociation(AccountantCompanyAssociation accountantCompanyAssociation) throws AccountantCompanyAssociationAlreadyExistException, AccountantNotFoundException, CompanyNotFoundException, ClassNotFoundException, AccountantPasswordIsToShort, AccountantWrongLogin, AccountantAlreadyExistException, IOException {
        if(AccountantRegistry.getInstance().findAccountantByLogin(accountantCompanyAssociation.getAccountantLogin()) == null){
            throw new AccountantNotFoundException("Ksiegowy o loginie " + accountantCompanyAssociation.getAccountantLogin() + " nie istnieje");
        }

        if(CompanyRegistry.getInstance().findCompanyByNip(accountantCompanyAssociation.getNip()) == null){
            throw new AccountantNotFoundException("Firma o NIPie " + accountantCompanyAssociation.getNip() + " nie istnieje");
        }

        if (accountantCompanyAssociations.contains(accountantCompanyAssociation)) {
            throw new AccountantCompanyAssociationAlreadyExistException("Podane przypisanie Ksiegowy-Firma juz istnieje");
        }else {
            accountantCompanyAssociations.add(accountantCompanyAssociation);
            AccountantCompanyAssociationController.saveAccountantCompanyAssociation();
        }
    }

    public Set<AccountantCompanyAssociation> getAccountantCompanyAssociations() {
        return accountantCompanyAssociations;
    }


    public static void saveAccountantCompanyAssiociationToFile(Set <AccountantCompanyAssociation> accountantCompanyAssociations) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/myAccountantCompanyAssociations.bin"));
        objectOutputStream.writeObject(accountantCompanyAssociations);
        objectOutputStream.close();
    }

    public static void readAccountantCompanyAssiociation(Set <AccountantCompanyAssociation> accountantCompanyAssociations) throws IOException, ClassNotFoundException, AccountantPasswordIsToShort, AccountantCompanyAssociationAlreadyExistException, AccountantWrongLogin, AccountantNotFoundException, CompanyNotFoundException, AccountantAlreadyExistException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/myAccountantCompanyAssociations.bin"));
        Set <AccountantCompanyAssociation> list = (Set<AccountantCompanyAssociation>) objectInputStream.readObject();
        getInstance().removeAllAccountantCompanyAssociation();
        for (AccountantCompanyAssociation a : list) {
            getInstance().addAccountantCompanyAssociation(a);
        }
        objectInputStream.close();
    }

    public void removeAllAccountantCompanyAssociation() {
        accountantCompanyAssociations = new HashSet<>();
    }
}
