public class BankManagerMenu extends TextInterface{

    private BankManager bankManager;

    public BankManagerMenu(BankManager bankManager){
        this.bankManager = bankManager;
        addAction(1, ()->setSystemDate(), "Set system date");
        addAction(2, ()->addBills(), "Restock bills");
        addAction(3, ()->undoTransaction(), "Undo a customer transaction");
        addAction(4, ()->approveAccount(), "Approve an account request");
        addAction(4, ()->createNewCustomer(), "Create a new customer account");
    }

    private void setSystemDate(){
        System.out.println("Input year:");
        int year = Integer.parseInt(nextLine());
        System.out.println("Input month:");
        int month = Integer.parseInt(nextLine());
        System.out.println("Input day:");
        int day = Integer.parseInt(nextLine());
        bankManager.setSystemDate(year, month, day);
    }

    private void addBills(){
        System.out.println("What kind?");
        int type = Integer.parseInt(nextLine());
        System.out.println("How many?");
        int num = Integer.parseInt(nextLine());
        if(bankManager.addBills(type, num)==-1){
            System.out.println("ERROR: Invalid input");
        }
    }

    private void undoTransaction(){
        System.out.println("Input target user:");
        String username = nextLine();
        System.out.println("Input account name:");
        String account = nextLine();
        bankManager.undoTransaction(username, account);
    }

    //TODO: Finish this
    public void approveAccount(){
        bankManager.approveAccount(0);
    }

    private void createNewCustomer(){
        System.out.println("Input username:");
        String username = nextLine();
        System.out.println("Input password:");
        String password = nextLine();
        bankManager.createNewCustomer(username, password);
    }
}
