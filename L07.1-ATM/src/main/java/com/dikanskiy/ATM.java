package com.dikanskiy;

import com.dikanskiy.banknotes.Banknote;

public interface ATM {
    void putCash(Banknote... banknotes);

    void getCash(long cashQuantity);

    void viewBalance();
}
