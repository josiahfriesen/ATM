import java.io.Serializable;
import java.util.ArrayList;

public class MainMenu extends TextInterface implements Serializable {

    public MainMenu(){
        super(null);
        actions.clear();
        header = "Main Menu";
        footer = "Choose an option:";
        addAction(0, ()->exit(), "Shut down");
        addAction(1, ()->login(), "Login to an account");
    }

    private void login(){
        System.out.println("Input username:");
        String username = nextLine();
        System.out.println("Input password:");
        String password = nextLine();
        ATM_User user = ATM_machine.getUser(username);
        if ((user != null) && (user.getPassword().equals(password))){
            if (user instanceof Customer) {
                System.out.println("Login successful");
                new CustomerMenu((Customer) user, this).showMenu();
            }
            else if (user instanceof BankManager) {
                System.out.println("Login successful");
                new BankManagerMenu((BankManager) user, this).showMenu();
            }
            else{
                System.out.println("This should never, ever, happen.");
            }
        }
        else{
            System.out.println("Invalid entry");
            showMenu();
        }
    }

    @Override
    public void exit() {
        ATM_machine.onExit();
        active = false;
    }
}
