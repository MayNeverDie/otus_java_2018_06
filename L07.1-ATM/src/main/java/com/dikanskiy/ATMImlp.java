package com.dikanskiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ATMImlp implements ATM {
    private final List<Casette> casetteList;
    /*private Casette hundredCasette = new Casette(2500);
    private Casette fiveHundredCasette = new Casette(2500);
    private Casette thousandCasette = new Casette(2500);
    private Casette fiveThousandCasette = new Casette(2500);
    private Casette cashInCasette = new Casette(2500);*/

    public ATMImlp(Casette... casettes) {
        this.casetteList = new ArrayList<>();
        Collections.addAll(casetteList,casettes);
    }

    public void putCash() {

    }

    public void getCash() {

    }

    public void viewBalance() {
        long cashBalance = 0;
        for (Casette casette: casetteList) {
            cashBalance+=casette.getCount()*casette.getBanknoteValue();
        }
            System.out.println(cashBalance);
    }
}
