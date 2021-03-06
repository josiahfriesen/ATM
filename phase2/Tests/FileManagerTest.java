package Tests;

import TopClasses.FileManager;
import UserClasses.Users.ATM_User;
import UserClasses.Users.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileManagerTest {
    FileManager fileManager;

    @Before
    public void setUp() {
        try {
            fileManager = new FileManager();

            Writer writer = new BufferedWriter(new FileWriter(new File("phase2/users.txt")));
            writer.write("");
            writer.close();

            writer = new BufferedWriter(new FileWriter(new File("phase2/alerts.txt")));
            writer.write("");
            writer.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {

    }

    @Test
    public void checkForAlert() {
    }

    @Test
    public void retrieveUsers() {
        Customer user1 = new Customer("user1", "pass1");
        Customer user2 = new Customer("user2", "pass2");
        ArrayList<ATM_User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        fileManager.saveUsers(userList);
        List retrievedUsers = fileManager.retrieveUsers();
        assertEquals("user1", ((Customer)retrievedUsers.get(0)).getUsername());
        assertEquals("user2", ((Customer)retrievedUsers.get(1)).getUsername());



    }

    @Test
    public void writeOutgoing() {
    }


    @Test
    public void writeAlerts() {
        ArrayList<int[]> alerts = new ArrayList<>();
        alerts.add(new int[]{5, 15});
        alerts.add(new int[]{20, 19});
        alerts.add(new int[]{50, 10});
        fileManager.writeAlerts(alerts);
        try {
            FileReader file = new FileReader(new File("phase2/deposits.txt"));
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            assertNotNull(line);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveBills() {
        int[] testBills = new int[]{14,335,23,47};
        fileManager.writeBills(testBills);
        int[] bills = fileManager.retrieveBills();
        assertArrayEquals(testBills, bills);
    }


    @Test
    public void readDeposits() {
    }
}