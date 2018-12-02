package com.dikanskiy.ATMDepartment;

import com.dikanskiy.ATM.ATM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ATMDepartmentImpl implements ATMDepartment {
    private List<ATM> ATMList;

    public ATMDepartmentImpl(ATM... ATMS) {
        this.ATMList = new ArrayList();
        Collections.addAll(ATMList,ATMS);
    }

    @Override
    public void restore() {
        ATMList.forEach(ATM::restore);

    }

    @Override
    public long getSum() {
        //ATMList.forEach(ATM::getBalance);
        long sum = ATMList.stream().mapToLong( o -> o.getBalance()).sum();
        return sum;
    }
}
