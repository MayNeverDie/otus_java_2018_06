package com.dikanskiy.ATMDepartment;

import com.dikanskiy.ATM.ATM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ATMDepartmentImpl implements ATMDepartment {
    private List<ATM> atmList;

    public ATMDepartmentImpl(ATM... ATMS) {
        this.atmList = new ArrayList();
        Collections.addAll(atmList,ATMS);
    }

    @Override
    public void restore() {
        atmList.forEach(ATM::restore);

    }

    @Override
    public long getSum() {
        //atmList.forEach(ATM::getBalance);
        long sum = atmList.stream().mapToLong(o -> o.getBalance()).sum();
        return sum;
    }
}
