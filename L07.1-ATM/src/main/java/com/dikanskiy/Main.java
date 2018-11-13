package com.dikanskiy;

import com.dikanskiy.banknotes.Banknote;
import com.dikanskiy.banknotes.FiveHundredBanknote;
import com.dikanskiy.banknotes.FiveThousandBanknote;
import com.dikanskiy.banknotes.HundredBanknote;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        Casette FiveHundredCasette = new Casette(2,new FiveHundredBanknote());
        Casette FiveThousandCasette = new Casette(2,new FiveThousandBanknote());

        ATM myAtm = new ATMImlp(FiveHundredCasette,FiveThousandCasette);
        myAtm.viewBalance();

        myAtm.putCash(new FiveHundredBanknote(),new FiveThousandBanknote());
        myAtm.viewBalance();

        myAtm.getCash(10000);
    }
}
