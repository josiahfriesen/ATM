import javax.xml.soap.Text;
import java.io.*;


import java.lang.reflect.Array;
import java.util.*;


public class ATM_machine extends TextInterface{

    static ArrayList<ATM_User> users = new ArrayList<ATM_User>();
    private static int numFifties = 100;
    private static int numTwenties = 250;
    private static int numTens = 500;
    private static int numFives = 1000;
    private static File userFile;

    private static Calendar date = new GregorianCalendar();

    public static void main (String[] args){
        userFile = new File("group_0331\\phase1\\users.txt");
        try {
            FileInputStream file = new FileInputStream(userFile);
            ObjectInputStream objectStream = new ObjectInputStream(file);

            users = (ArrayList) objectStream.readObject();
            objectStream.close();
        }
        catch (IOException | ClassNotFoundException x){
            x.printStackTrace();
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Input your username");
        String user = s.nextLine();
        System.out.println("Input your password");
        String pass = s.nextLine();
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(user) && users.get(i).getPassword().equals(pass)){
                System.out.println("Success!");
                users.get(i).showMenu();
            }
        }
    }

    static void checkForAlert(){
        try{
            FileWriter writer = new FileWriter("alerts.txt");

            if (getNumFifties() < 20){
                writer.write("numFifties has fallen below 20. Current number: "+getNumFifties());
            }
            if (getNumTwenties() < 20){
                writer.write("numTwenties has fallen below 20. Current number: "+getNumTwenties());
            }
            if (getNumTens() < 20){
                writer.write("numTens has fallen below 20. Current number: "+getNumTens());
            }
            if (getNumFives() < 20) {
                writer.write("numFives has fallen below 20. Current number: "+getNumFives());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Calendar getTime(){return date;}

    static void setTime(Calendar newDate){date = newDate;}

    static int getNumFifties(){return numFifties;}

    static int getNumTwenties(){return numTwenties;}

    static int getNumTens(){return numTens;}

    static int getNumFives(){return numFives;}

    static void setFifties(int numBills){
        numFifties = numBills;
    }

    static void setTwenties(int numBills){
        numTwenties = numBills;
    }

    static void setTens(int numBills){
        numTens = numBills;
    }

    static void setFives(int numBills){
        numFives = numBills;
    }
}
