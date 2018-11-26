package com.dikanskiy;

import com.dikanskiy.banknotes.Banknote;

import java.util.List;
import java.util.stream.Stream;

class ATMHelper {

    private ATMHelper() {
    }

    static void fillCasette(Casette casette, Banknote banknote, int maxElements){
        for (int i = 0; i < maxElements; i++) {
            casette.put(banknote);
        }
    }

    static void printCashValue(List withdrawnBanknotes){
        Stream.of(withdrawnBanknotes).forEach(System.out::println);
    }
}
