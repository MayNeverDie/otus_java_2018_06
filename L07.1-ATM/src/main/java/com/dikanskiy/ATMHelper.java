package com.dikanskiy;

import com.dikanskiy.ATM.Casette;
import com.dikanskiy.banknotes.Banknote;
import com.dikanskiy.exceptions.ATMException;

import java.util.List;
import java.util.stream.Stream;

class ATMHelper {

    private ATMHelper() {
    }

    static void fillCasette(Casette casette, Banknote banknote, int maxElements){
        for (int i = 0; i < maxElements; i++) {
            try {
                casette.put(banknote);
            } catch (ATMException e) {
                e.printStackTrace();
            }
        }
    }

    static void printCashValue(List withdrawnBanknotes){
        Stream.of(withdrawnBanknotes).forEach(System.out::println);
    }
}
