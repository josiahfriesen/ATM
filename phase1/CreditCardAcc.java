// A super class for debt accounts.

import java.io.Serializable;
import java.util.*;

public class CreditCardAcc extends GenericAccount implements Serializable{
    private final int maxDebt = 3000;



    public CreditCardAcc(String name_p, IAccountHolder o) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = false;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        type = "CREDIT CARD";
    }

    @Override
    boolean withdraw(int amount){
        if (balance + amount <= maxDebt){
            balance += amount;
            return super.withdraw(amount);
        }
        System.out.println("You don't have enough remaining credit to withdraw that much! Remaining credit: " +
                (maxDebt - balance));
        return false;
    }
}