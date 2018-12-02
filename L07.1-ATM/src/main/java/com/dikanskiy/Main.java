package com.dikanskiy;

import com.dikanskiy.ATM.ATM;
import com.dikanskiy.ATM.ATMImlp;
import com.dikanskiy.ATM.Casette;
import com.dikanskiy.ATMDepartment.ATMDepartment;
import com.dikanskiy.ATMDepartment.ATMDepartmentImpl;
import com.dikanskiy.banknotes.*;

public class Main {
    public static void main(String[] args) {

        Banknote TB = new ThousandBanknote();
        Banknote FTB = new FiveThousandBanknote();

        Casette ThousandCasette = new Casette(TB);
        Casette FiveThousandCasette = new Casette(FTB);

        ATMHelper.fillCasette(ThousandCasette,TB,5);
        ATMHelper.fillCasette(FiveThousandCasette,FTB,2);

        ATM myAtm = new ATMImlp(ThousandCasette,FiveThousandCasette);

        myAtm.doBackup();

        ATMHelper.printCashValue(myAtm.getCash(15000));

        ATMDepartment myATMD = new ATMDepartmentImpl(myAtm);
        myATMD.restore();
        System.out.println(myATMD.getSum());
    }
}
