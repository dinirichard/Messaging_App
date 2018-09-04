package MessagingApp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserServices {

    public static List<String> myConversations = new ArrayList<>();
    public UserServices(IOUtils ioUtils) {
        Scanner scanner = new Scanner(System.in);
    }

    public static void addUser(String email, String name, int age, String password) throws IOException {
        User newUser = new User(email, name, age, password);

        //Path emaill = Paths.get("output.txt");
        FileWriter writer = new FileWriter(String.valueOf(email));
        writer.write("Email:" + email + "\nName:" + name + "\nAge:" + age + "\nPassword:" + password);
        writer.close();
    }

    public static boolean login(String email, String password) throws IOException {

        Path filePath = Paths.get(email);

        File file = new File(String.valueOf(email));

        if (!file.exists()) {                               //Check if the file Exists
            System.out.println("User does not exist");
            return false;
        } else {

            List<String> lines = Files.readAllLines(filePath);

            String Email = "Email:" + email;
            String Password = "Password:" + password;

            Optional<String> emailCheckOptional = lines.stream()
                    .filter(n -> n.contentEquals(Email))
                    .findFirst();                       //Find first match
            String emailToCheck = "";
            if (emailCheckOptional.isPresent()) {
                emailToCheck = emailCheckOptional.get();    // .get() to get the match
            }
            //.filter( n -> n.contentEquals("Password:" + password));

            if (Email.equals(emailToCheck)) {

                Optional<String> passwordCheckOptional = lines.stream()
                        .filter(n -> n.contentEquals(Password))
                        .findFirst();                       //Find first match
                String passwordToCheck = "";
                if (passwordCheckOptional.isPresent()) {
                    passwordToCheck = passwordCheckOptional.get();    // .get() to get the match
                }
                //.filter( n -> n.contentEquals("Password:" + password));

                if (Password.equals(passwordToCheck)) {
                    System.out.println("Login Successful");
                    return true;
                } else {
                    System.out.println("Email or password is wrong");
                    return false;
                }

            } else {
                System.out.println("Email or password is wrong");
                return false;
            }

            //return true;
        }
        //return true;
    }


    public static boolean searchUser(String email) throws IOException {
        Path filePath = Paths.get(email);

        File file = new File(String.valueOf(email));

        if (!file.exists()) {                               //Check if the file Exists
            System.out.println("User does not exist");
            return false;
        } else {

            List<String> lines = Files.readAllLines(filePath);

            String Email = "Email:" + email;


            Optional<String> emailCheckOptional = lines.stream()
                    .filter(n -> n.contentEquals(Email))
                    .findFirst();                       //Find first match
            String emailToCheck = "";
            if (emailCheckOptional.isPresent()) {
                emailToCheck = emailCheckOptional.get();    // .get() to get the match
            }
            //.filter( n -> n.contentEquals("Password:" + password));

            if (Email.equals(emailToCheck)) {
                System.out.println("User Found");
                return true;
            } else {
                System.out.println("User does not exist");
                return false;
            }
        }

    }

    public static void messageUser(String user1Email, String secondEmail, String message) throws IOException {

        Path usersMessages = searchForMessage(user1Email, secondEmail);

        if (usersMessages != null) {

            FileWriter writer = new FileWriter(String.valueOf(usersMessages), true);
            writer.write("\n" + user1Email + ": \n" + message);
            writer.close();
        } else {

            FileWriter writer = new FileWriter(user1Email + "_" + secondEmail);

            writer.write(user1Email + ": \n" + message);
            writer.close();
            for (int i = 0; i < myConversations.size(); i++) {
                if (i == myConversations.size()) {

                    myConversations.add(i+1,user1Email + "_" + secondEmail);
                }
            }
        }


    }

    public static Path searchForMessage(String user1Email, String secondEmail) throws IOException {

        Path filePath = Paths.get(user1Email + "_" + secondEmail);
        Path filePath2 = Paths.get(secondEmail + "_" + user1Email);

        File file = new File(user1Email + "_" + secondEmail);
        File file2 = new File(secondEmail + "_" + user1Email);

        if (!file.exists()) {                               //Check if the file Exists
            if (!file2.exists()) {
                System.out.println("User does not exist");
            } else {
                return filePath2;
            }
        } else {
            return filePath;
        }

        return null;
    }

    public static void newMessagesSearch(String email) throws IOException {



    }





}
