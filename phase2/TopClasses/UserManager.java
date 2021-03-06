package TopClasses;

import AccountClasses.Accounts.SavingAcc;
import UserClasses.Users.*;

import java.util.ArrayList;
import java.util.Calendar;

public class UserManager {

    private static ArrayList<ATM_User> users = new ArrayList<>();

    /**
     * Manages ATM_users
     */

    static void instantiate() {
        users = new FileManager().retrieveUsers();
        if(users.size()==0) {
            System.out.println("Adding a manager");
            users.add(new BankManager("manager","password"));
        }
    }

    public ATM_User getUser(String username){
        ATM_User found = null;
        for (ATM_User user: users){
            if (user.getUsername().equals(username)){
                saveUsers();
                found = user;
            }
        }
        return found;
    }

    public void saveUsers(){
        new FileManager().saveUsers(users);
    }

    void checkInterest(){
        if (ATM_machine.getTime().get(Calendar.DAY_OF_MONTH) == 1){
            for(int i = 0; i < users.size(); i++){
                if (users.get(i) instanceof IAccountHolder){
                    for(int j = 0; j < ((IAccountHolder) users.get(i)).getAccounts().size(); j++){
                        if (((IAccountHolder) users.get(i)).getAccounts().get(j) instanceof SavingAcc){
                            ((SavingAcc) ((IAccountHolder) users.get(i)).getAccounts().get(j)).increase_interest();
                        }
                    }
                }
            }
        }
        saveUsers();
    }

    public boolean addUser(String username, String password, int result){
        boolean isFree = getUser(username)==null;
        if(isFree) {
            if(result == 0){
                users.add(new Employee(username, password));
            }
            else if(result == 1){
                users.add(new Customer(username, password));
            }

            saveUsers();
        }
        return isFree;
    }
}