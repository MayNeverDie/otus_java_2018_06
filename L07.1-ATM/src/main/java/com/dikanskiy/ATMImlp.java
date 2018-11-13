package com.dikanskiy;

import com.dikanskiy.banknotes.Banknote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ATMImlp implements ATM {
    private final List<Casette> casetteList;

    public ATMImlp(Casette... casettes) {
        this.casetteList = new ArrayList<>();
        Collections.addAll(casetteList, casettes);
    }

    public void putCash(Banknote... banknotes) {

        for (Banknote banknote : banknotes) {
            for (Casette casette : casetteList) {
                if (casette.getBanknoteValue() == banknote.getValue()) {
                    casette.put(banknote);
                    break;
                }
            }
        }
    }

    public void getCash(long cashQuantity) {
        if (getBalance()<cashQuantity){
            throw new RuntimeException("Not enough cash");
        }
    }

    private long getBalance(){
        long cashBalance = 0;
        for (Casette casette : casetteList) {
            cashBalance += casette.getCount() * casette.getBanknoteValue();
        }
        return cashBalance;
    }
    public void viewBalance() {
        System.out.println(getBalance());
    }
}
