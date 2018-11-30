package com.dikanskiy;

import com.dikanskiy.banknotes.Banknote;

import java.util.List;

public interface ATM {
    void putCash(Banknote... banknotes);

    List getCash(long cashQuantity);

    void viewBalance();

    void doBackup();

    void restore();
}
