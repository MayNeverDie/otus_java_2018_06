package com.dikanskiy.banknotes;

public class ThousandBanknote extends Banknote {
    private int value = 1000;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ThousandBanknote";
    }
}
