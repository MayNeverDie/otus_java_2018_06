package com.dikanskiy.banknotes;

public class FiveThousandBanknote extends Banknote {
    private int value = 5000;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FiveThousandBanknote";
    }
}
