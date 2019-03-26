public class WithdrawCapped implements IWithdrawable{
    private double minBalance;
    WithdrawCapped(int minBalance){
        this.minBalance = minBalance;
    }

    public double withdraw(int amount, double balance){
        if (balance + minBalance >= amount){
            ATM_machine.withdrawBills(amount/5);
            return balance - amount;
        }
        else {
            System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
            return -1;
        }
    }
}
