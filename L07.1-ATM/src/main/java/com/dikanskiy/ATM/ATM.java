package com.dikanskiy.ATM;

import com.dikanskiy.banknotes.Banknote;

import java.util.List;

public interface ATM {
    void putCash(Banknote... banknotes);

    List getCash(long cashQuantity);

    long getBalance();

    void doBackup();

    void restore();
}
