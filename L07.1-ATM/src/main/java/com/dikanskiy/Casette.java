package com.dikanskiy;

import com.dikanskiy.banknotes.Banknote;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Casette {
    private final List<Banknote> banknoteStack;
    private final int maxElements;
    private final int banknoteValue;
    private int count = 0;

    public Casette(int maxElements, int banknoteValue) {
        this.banknoteValue = banknoteValue;
        this.maxElements = maxElements;
        banknoteStack = new ArrayList<>(maxElements);
        //()->Stream.generate(()-> new Banknote()).limit(quantity).collect(Collectors.toList(banknoteStack));
    }

    public void put(Banknote banknote) {

        if (banknoteStack.size() == maxElements) {
            throw new RuntimeException("Casette is full");
        } else {
            banknoteStack.add(banknote);
        }
        count++;
    }

    public Banknote get() {
        Banknote withdrawnBanknote = banknoteStack.iterator().next();
        banknoteStack.remove(withdrawnBanknote);
        count--;
        return withdrawnBanknote;
    }

    public void Fill(Banknote banknote) {
        for (int i = 0; i < maxElements; i++) {
            put(banknote);
        }
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
}
