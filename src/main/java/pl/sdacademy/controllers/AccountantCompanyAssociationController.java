package pl.sdacademy.controllers;

import pl.sdacademy.associations.AccountantCompanyAssociation;
import pl.sdacademy.associations.AccountantCompanyAssociationRegistry;
import pl.sdacademy.exceptions.*;

import java.io.IOException;

public class AccountantCompanyAssociationController {

    public static void createAccountantCompanyAssociation(String accountantLogin, String companyNip) throws AccountantCompanyAssociationAlreadyExistException, AccountantNotFoundException, CompanyNotFoundException, ClassNotFoundException, AccountantPasswordIsToShort, AccountantWrongLogin, AccountantAlreadyExistException, IOException {
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(new AccountantCompanyAssociation(accountantLogin,companyNip));
    }

    public static void saveAccountantCompanyAssociation() throws IOException, ClassNotFoundException {
        AccountantCompanyAssociationRegistry.saveAccountantCompanyAssiociationToFile(AccountantCompanyAssociationRegistry.getInstance().getAccountantCompanyAssociations());
    }

    public static void readAccountantCompanyAssociation() throws ClassNotFoundException, AccountantPasswordIsToShort, AccountantAlreadyExistException, IOException, AccountantWrongLogin, AccountantCompanyAssociationAlreadyExistException, AccountantNotFoundException, CompanyNotFoundException {
        AccountantCompanyAssociationRegistry.readAccountantCompanyAssiociation(AccountantCompanyAssociationRegistry.getInstance().getAccountantCompanyAssociations());
    }
}
