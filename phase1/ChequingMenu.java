public class ChequingMenu extends AccountMenu{

    public ChequingMenu(GenericAccount account, TextInterface previous){
        super(account, previous);
        addAction(1, ()->showBalance(), "View Balance");
        addAction(2, ()->getLastTransaction(), "See Last Transaction");
        addAction(3, ()->depositCash(), "Deposit Cash");
        addAction(4, ()->transferToSelf(), "Transfer to another account");
        addAction(5, ()->transferToOther(), "Transfer to another user");
        addAction(6, ()->transferToExternal(), "Pay external bill");
        addAction(7, ()->withdraw(), "Withdraw money");
    }
}
