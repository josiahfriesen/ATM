//The bank manager class
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.GregorianCalendar;

public class BankManager extends ATM_User implements IBankManager, IEmployeePermissions{

    /**
     * Requests are stored as a AccountCreationRequest<String username, String type, String accountName>
     */
    private static List<AccountCreationRequest> requests = new ArrayList<>();

    public BankManager(String username, String password){
        super(username, password);
    }

    static List<AccountCreationRequest> getRequests(){
        return requests;
    }

    /**
     * Adds a request for the account of the specified type.
     */
    static void requestAccount(String username, String type, String accountName){
        requests.add(new AccountCreationRequest(username, type, accountName));
    }

    /**
     * Sets the ATM's date to the one specified, at 12:00 exactly.
     */
    public boolean setSystemDate(int year, int month, int day){
        try{
            Calendar time = new GregorianCalendar(year, month - 1, day);
            ATM_machine.setTime(time);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * Adds num bills of the specified type to the machine.
     * @return Returns the new number of bills, or -1 if the bill type cannot be found.
     */

    /**
     * Undoes the last transaction (excluding bill payments) performed by the indicated user in the given account.
     */
    public void undoTransaction(String username, String account){
        if (ATM_machine.getUser(username) instanceof Customer){
            Customer target = (Customer) ATM_machine.getUser(username);
            GenericAccount targetacc = target.getAccountByName(account);
        }
    }

    /**
     * Approves a customer's account creation request.
     */
    public boolean approveAccount(int id){
        String username = requests.get(id).getUser();
        String type = requests.get(id).getType();
        String name = requests.get(id).getName();
        if (ATM_machine.getUser(username) instanceof Customer){
            Customer user = (Customer) ATM_machine.getUser(username);
            if(user.addAccount(type, name)) {
                requests.remove(id);
                return true;
            }
        }
        requests.remove(id);
        return false;
    }

    /**
     * Creates a new customer with the given login credentials.
     */

}