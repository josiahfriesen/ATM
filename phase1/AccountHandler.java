import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountHandler implements Serializable {
    private ArrayList<GenericAccount> accounts;
    private IAccountHolder user;

    AccountHandler(IAccountHolder user){
        this.user = user;
        accounts = user.accounts;
    }

    boolean addAccount(String type, String name) {
        if (type.length() >= 8){
            if (type.substring(0, 8).equals("chequing")) {
                if (type.length() != 8) {
                    accounts.add(new ChequingAcc(name, user, true));
                }
                else {
                    accounts.add(new ChequingAcc(name, user, false));
                }
            }
        } else if (type.equalsIgnoreCase("CREDIT")) {
            accounts.add(new CreditCardAcc(name, user));
        } else if (type.equalsIgnoreCase("CREDITLINE")) {
            accounts.add(new CreditLineAcc(name, user));
        } else if (type.equalsIgnoreCase("SAVINGS")) {
            accounts.add(new SavingAcc(name, user));
        } else {
            return false;
        }
        if (type.equals("chequing(primary)")) {
            checkChequingPrimary(accounts, name);
        }
        return true;
    }

    //Make sure there is only one primary account
    //There should only be one other primary account because it gets checked everytime an account is created
    void checkChequingPrimary(ArrayList<GenericAccount> accs, String name) {
        int idx = 0;
        for (GenericAccount a: accs) {
            if (a.type.equals(" (Chequing)") && !a.name.equals(name) && ((ChequingAcc) a).isPrimary()) {
                idx = accs.indexOf(a);
                break;
            }
        }
        if (idx != 0) {
            ChequingAcc c = (ChequingAcc) accs.get(idx);
            c.setPrimary(false);
            accs.set(idx, c);
        }
    }

    ArrayList<GenericAccount> getAccounts() {
        return accounts;
    }

    //Summary of account balances
    String getFullSummary(){
        String summary = "";
        for (GenericAccount acc : accounts){
            summary += acc.getSummary();
            summary += "\n";
        }
        return summary;
    }

    double getNetTotal(){
        double total = 0;
        for (GenericAccount acc : accounts) {
            if (acc.isAsset()) {
                total += acc.getBalance();
            } else {
                total -= acc.getBalance();
            }
        }
        return total;
    }

    /**
     * Gets an account given the name.
     * Assume account exists.
     */
    GenericAccount getAccountByName(String name) {
        GenericAccount account = new ChequingAcc("BAD", user, false);
        for (GenericAccount a: accounts) {
            if (a.name.equals(name)) {
                account =  a;
                break;
            }
        }
        return account;
    }
}
