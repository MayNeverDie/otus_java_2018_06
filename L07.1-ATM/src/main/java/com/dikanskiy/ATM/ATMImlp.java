package com.dikanskiy.ATM;

import com.dikanskiy.banknotes.Banknote;

import java.util.*;

public class ATMImlp implements ATM {
    private List<Casette> casetteList;
    private Memento memento;

    public ATMImlp(Casette... casettes) {
        this.casetteList = new ArrayList<>();
        Collections.addAll(casetteList, casettes);
        casetteList.sort(Collections.reverseOrder());
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

    public List getCash(long cashQuantity) {
        ArrayList withdrawnBanknotes = new ArrayList();
        if (cashQuantity > 0) {
            if (getBalance() < cashQuantity) {
                System.out.println("Not enough cash in ATM");
            } else {
                if (cashQuantity % 100 == 0) {
                    withdrawnBanknotes = getCashFromCasette(cashQuantity);
                } else {
                    System.out.println("Enter valid cash quantity");
                }
            }
        } else {
            System.out.println("Enter a non-zero cash quantity");
        }
        return withdrawnBanknotes;
    }

    private ArrayList getCashFromCasette(long cashQuantity) {
        ArrayList withdrawnBanknotes = new ArrayList();
        for (Casette casette : casetteList) {
            int banknoteValue = casette.getBanknoteValue();
            int coefficient = (int) cashQuantity / banknoteValue;
            int cashCount = casette.getCount();
            int cashOutSize;
            if (coefficient > 0) {
                if (cashCount >= coefficient) {
                    cashQuantity -= coefficient * banknoteValue;
                    cashOutSize = coefficient;
                } else {
                    cashQuantity -= cashCount * banknoteValue;
                    cashOutSize = cashCount;
                }
                withdrawnBanknotes.addAll(casette.get(cashOutSize));
            }
            if (cashQuantity == 0) break;
        }
        return withdrawnBanknotes;
    }


    public long getBalance() {
        long cashBalance = 0;
        for (Casette casette : casetteList) {
            cashBalance += casette.getCount() * casette.getBanknoteValue();
        }
        return cashBalance;
    }

    public void doBackup(){
        memento = new Memento(casetteList);
    }

    public void restore(){
        casetteList = memento.getMemCasetteList();
    }

    @Override
    public String toString() {
        return "ATMImlp{" +
                "casetteList=" + casetteList +
                '}';
    }

}

class Memento {
    private List memCasetteList;

    public Memento(List casetteList) {
        memCasetteList = new ArrayList(casetteList.size());
        Iterator<Casette> iterator = casetteList.iterator();
        while (iterator.hasNext()){
            memCasetteList.add(iterator.next().clone());
        }
    }

    public List getMemCasetteList() {
        return memCasetteList;
    }
}

