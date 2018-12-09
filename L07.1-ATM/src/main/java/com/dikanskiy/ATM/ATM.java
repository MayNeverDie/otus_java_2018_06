package com.dikanskiy.ATM;

import com.dikanskiy.exceptions.ATMException;
import com.dikanskiy.banknotes.Banknote;

import java.util.List;

public interface ATM {
    void putCash(Banknote... banknotes) throws ATMException;

    List<? extends Banknote> getCash(long cashQuantity) throws ATMException;

    long getBalance();

    void doBackup();

    void restore();
}
