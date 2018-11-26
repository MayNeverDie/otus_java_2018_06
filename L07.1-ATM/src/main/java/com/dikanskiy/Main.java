package com.dikanskiy;

import com.dikanskiy.banknotes.*;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        Banknote TB = new ThousandBanknote();
        Banknote FTB = new FiveThousandBanknote();

        Casette ThousandCasette = new Casette(TB);
        Casette FiveThousandCasette = new Casette(FTB);

        ATMHelper.fillCasette(ThousandCasette,TB,5);
        ATMHelper.fillCasette(FiveThousandCasette,FTB,2);

        ATM myAtm = new ATMImlp(ThousandCasette,FiveThousandCasette);
        myAtm.viewBalance();

        ATMHelper.printCashValue(myAtm.getCash(15000));
        myAtm.viewBalance();
    }
}
