package pl.sdacademy.controllers;

import pl.sdacademy.associations.AccountantCompanyAssociation;
import pl.sdacademy.associations.AccountantCompanyAssociationRegistry;
import pl.sdacademy.exceptions.*;

import java.io.IOException;

public class AccountantCompanyAssociationController {

    public static void createAccountantCompanyAssociation(String accountantLogin, String companyNip) throws AccountantCompanyAssociationAlreadyExistException, AccountantNotFoundException, CompanyNotFoundException, ClassNotFoundException, AccountantPasswordIsToShort, AccountantWrongLogin, AccountantAlreadyExistException, IOException {
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(new AccountantCompanyAssociation(accountantLogin,companyNip));
    }

}
