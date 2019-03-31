import java.text.SimpleDateFormat;
import java.util.*;


public class ATM_machine{

    //the final ints represent the indexes in an array of bill quantities, with the index corresponding to their
    //type.
    private static BillHandler billHandler;

    private static Calendar date = new GregorianCalendar();

    public static void main (String[] args){
        date.add(Calendar.DAY_OF_MONTH, 1);
        billHandler = new BillHandler();
        MainMenuGUI.main();
    }

    static void onExit() {
        billHandler.saveBills();
        new UserManager().saveUsers();
        new UserManager().checkInterest();
    }

    static Calendar getTime(){return date;}

    static void setTime(Calendar newDate){date = newDate;}

//    static int getNumFifties(){return bills[FIFTY];}
//    static int getNumTwenties(){return bills[TWENTY];}
//    static int getNumTens(){return bills[TEN];}
//    static int getNumFives(){return bills[FIVE];}
    static int[] getBills(){return billHandler.getBills();}

    static void depositBills(int fives, int tens, int twenties, int fifties) {
        billHandler.depositBills(fives, tens, twenties, fifties);
    }

    static void withdrawBills(int amount){
        billHandler.withdrawBills(amount);
    }

    static String getTimeFormatted(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(ATM_machine.getTime().getTime());
    }
}
