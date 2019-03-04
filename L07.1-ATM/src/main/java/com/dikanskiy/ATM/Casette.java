package com.dikanskiy.ATM;

import com.dikanskiy.exceptions.ATMException;
import com.dikanskiy.banknotes.Banknote;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Casette implements Comparable<Casette>, Cloneable {
    private final List<Banknote> banknotes;
    private List<Banknote> withdrawnBanknotes;
    private final int banknoteValue;
    private static final int casetteCapacity = 2500;
    private int count = 0;

    @Override
    public String toString() {
        return "Casette{" +
                "banknoteValue=" + banknoteValue +
                ", count=" + count +
                '}';
    }

    public Casette(Banknote banknote) {
        this.banknoteValue = banknote.getValue();
        banknotes = new ArrayList<>(casetteCapacity);
    }

    public void put(Banknote banknote) throws ATMException {
        if (banknotes.size() == casetteCapacity) {
            throw new ATMException("Casette is full");
        } else if(banknoteValue!=banknote.getValue()){
            throw  new ATMException("Incorrect banknote value");
        } else {
            banknotes.add(banknote);
        }
        count++;
    }

    public List<Banknote> get(int banknoteQuantity) {
        List<Banknote> withdrawnBanknotes = new ArrayList<>();

        for (int i = 0; i < banknoteQuantity; ) {
            Banknote withdrawnBanknote = banknotes.iterator().next();
            banknotes.remove(withdrawnBanknote);
            withdrawnBanknotes.add(withdrawnBanknote);
            count--;
            i++;
        }
        return withdrawnBanknotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casette casette = (Casette) o;
        return Objects.equals(banknotes, casette.banknotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banknotes);
    }

    public int getCount() {
        return count;
    }

    public int getBanknoteValue() {
        return banknoteValue;
    }

    private int compareSize(int firstSize, int secondSize) {
        return (firstSize < secondSize) ? -1 : ((firstSize == secondSize) ? 0 : 1);
    }

    @Override
    public int compareTo(Casette secondCasette) {
        return compareSize(this.getBanknoteValue(), secondCasette.getBanknoteValue());
    }

    @Override
    protected Casette clone(){
      Casette clone = null;
        try {
            clone = (Casette) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
