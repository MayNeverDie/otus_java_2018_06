package com.dikanskiy.ATM;

import com.dikanskiy.banknotes.Banknote;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Casette implements Comparable<Casette>, Cloneable {
    private final List<Banknote> banknoteStack;
    private List<Banknote> withdrawnBanknotes;
    private final int banknoteValue;
    private final int casetteCapacity = 2500;
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
        banknoteStack = new ArrayList<>(casetteCapacity);
    }

    public void put(Banknote banknote) {

        if (banknoteStack.size() == casetteCapacity) {
            throw new RuntimeException("Casette is full");
        } else {
            banknoteStack.add(banknote);
        }
        count++;
    }

    public ArrayList<Banknote> get(int banknoteQuantity) {
        ArrayList<Banknote> withdrawnBanknotes = new ArrayList<>();

        for (int i = 0; i < banknoteQuantity; ) {
            Banknote withdrawnBanknote = banknoteStack.iterator().next();
            banknoteStack.remove(withdrawnBanknote);
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
        return Objects.equals(banknoteStack, casette.banknoteStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banknoteStack);
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
