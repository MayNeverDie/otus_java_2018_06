package com.dikanskiy;

import com.dikanskiy.ATM.ATM;
import com.dikanskiy.ATM.ATMImlp;
import com.dikanskiy.ATM.Casette;
import com.dikanskiy.ATMDepartment.ATMDepartment;
import com.dikanskiy.ATMDepartment.ATMDepartmentImpl;
import com.dikanskiy.banknotes.*;
import com.dikanskiy.exceptions.ATMException;

public class Main {
    public static void main(String[] args) {

        Banknote tb = new ThousandBanknote();
        Banknote ftb = new FiveThousandBanknote();

        Casette thousandCasette = new Casette(tb);
        Casette fiveThousandCasette = new Casette(ftb);

        ATMHelper.fillCasette(thousandCasette, tb, 5);
        ATMHelper.fillCasette(fiveThousandCasette, ftb, 2);

        ATM myAtm = new ATMImlp(thousandCasette, fiveThousandCasette); //setting up the ATM

        try {
            myAtm.putCash(new ThousandBanknote()); //putting one banknote in
        } catch (ATMException e) {
            e.printStackTrace();
        }

        myAtm.doBackup();

        try {
            ATMHelper.printCashValue(myAtm.getCash(15000)); //withdrawing cash off ATM
        } catch (ATMException e) {
            e.printStackTrace();
        }

        ATMDepartment myATMD = new ATMDepartmentImpl(myAtm); //adding ATM to Department
        System.out.println(myATMD.getSum());
        myATMD.restore(); //restoring initial ATM state
        System.out.println(myATMD.getSum()); //printing department cash sum
    }
}
