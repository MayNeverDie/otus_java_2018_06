package com.dikanskiy;

import com.dikanskiy.banknotes.Banknote;
import com.dikanskiy.banknotes.FiveHundredBanknote;
import com.dikanskiy.banknotes.FiveThousandBanknote;
import com.dikanskiy.banknotes.HundredBanknote;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        Casette FiveHundredCasette = new Casette(2,500);
        FiveHundredCasette.Fill(new FiveHundredBanknote());
        Casette FiveThousandCasette = new Casette(2,5000);
        FiveThousandCasette.Fill(new FiveThousandBanknote());

        ATM myAtm = new ATMImlp(FiveHundredCasette,FiveThousandCasette);
        myAtm.viewBalance();
    }
}
