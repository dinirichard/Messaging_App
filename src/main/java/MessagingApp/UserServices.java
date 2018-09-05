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

    public static int newMessagesCount = 0;
    public UserServices(IOUtils ioUtils) {
        Scanner scanner = new Scanner(System.in);
    }

    public static void addUser(String email, String name, int age, String password) throws IOException {
        User newUser = new User(email, name, age, password);

        //Path emaill = Paths.get("output.txt");
        FileWriter writer = new FileWriter(email + ".txt");
        writer.write("Email:" + email + "\nName:" + name + "\nAge:" + age + "\nPassword:" + password);
        writer.close();

        // Create a new conversations file for User, to save new conversations.
        FileWriter scribbler = new FileWriter(email + "_Conversations.txt");
        scribbler.close();
    }

    public static boolean login(String email, String password) throws IOException {

        Path filePath = Paths.get(email + ".txt");

        File file = new File(email + ".txt");

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
        Path filePath = Paths.get(email + ".txt");

        File file = new File(email + ".txt");

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
        //Path usersMessages2 = searchForMessage(secondEmail, user1Email);

        if (usersMessages != null) {

            FileWriter writer = new FileWriter(String.valueOf(usersMessages), true);
            writer.write("\n" + user1Email + ": \n" + message);
            writer.close();

//  Find conversation file and add a new conversation
            Path conversation = Paths.get(user1Email + "_Conversations.txt");
            List<String> lines = Files.readAllLines(conversation);
            Optional<String> conversationCheckOptional = lines.stream()
                    .filter(n -> n.contentEquals(user1Email + "_" + secondEmail))
                    .findFirst();                       //Find first match
            Optional<String> conversationCheckOptional2 = lines.stream()
                    .filter(n -> n.contentEquals(secondEmail + "_" + user1Email))
                    .findFirst();                       //Find first match

            if (!conversationCheckOptional.isPresent() || !conversationCheckOptional2.isPresent() ) {
                FileWriter scribble = new FileWriter(user1Email + "_Conversations.txt", true);
                scribble.write("\n" + user1Email + "_" + secondEmail);
                scribble.close();
            }
// Find conversation file for the reciever and add a new conversation
            Path user2conversation = Paths.get(secondEmail + "_Conversations.txt");
            List<String> lines2 = Files.readAllLines(user2conversation);
            Optional<String> conversationCheckOptional_a = lines.stream()
                    .filter(n -> n.contentEquals(user1Email + "_" + secondEmail))
                    .findFirst();                       //Find first match
            Optional<String> conversationCheckOptional_b = lines.stream()
                    .filter(n -> n.contentEquals(secondEmail + "_" + user1Email))
                    .findFirst();                       //Find first match

            if (!conversationCheckOptional_a.isPresent() || !conversationCheckOptional_b.isPresent() ) {
                FileWriter scribble2 = new FileWriter(secondEmail + "_Conversations.txt", true);
                scribble2.write("\n" + user1Email + "_" + secondEmail );
                scribble2.close();
            }


        } else {

            FileWriter writer = new FileWriter(user1Email + "_" + secondEmail + ".txt");

            writer.write(user1Email + ": \n" + message);
            writer.close();


//  Find conversation file and add a new conversation
            Path conversation = Paths.get(user1Email + "_Conversations.txt");
            List<String> lines = Files.readAllLines(conversation);
            Optional<String> conversationCheckOptional = lines.stream()
                    .filter(n -> n.contentEquals(user1Email + "_" + secondEmail))
                    .findFirst();                       //Find first match
            Optional<String> conversationCheckOptional2 = lines.stream()
                    .filter(n -> n.contentEquals(secondEmail + "_" + user1Email))
                    .findFirst();                       //Find first match

            if (!conversationCheckOptional.isPresent() || !conversationCheckOptional2.isPresent() ) {
                FileWriter scribble = new FileWriter(user1Email + "_Conversations.txt", true);
                scribble.write("\n" + user1Email + "_" + secondEmail);
                scribble.close();
            }
// Find conversation file for the reciever and add a new conversation
            Path user2conversation = Paths.get(secondEmail + "_Conversations.txt");
            List<String> lines2 = Files.readAllLines(user2conversation);
            Optional<String> conversationCheckOptional_a = lines.stream()
                    .filter(n -> n.contentEquals(user1Email + "_" + secondEmail))
                    .findFirst();                       //Find first match
            Optional<String> conversationCheckOptional_b = lines.stream()
                    .filter(n -> n.contentEquals(secondEmail + "_" + user1Email))
                    .findFirst();                       //Find first match

            if (!conversationCheckOptional_a.isPresent() || !conversationCheckOptional_b.isPresent() ) {
                FileWriter scribble2 = new FileWriter(secondEmail + "_Conversations.txt", true);
                scribble2.write("\n" + user1Email + "_" + secondEmail);
                scribble2.close();
            }

        }


    }

    public static Path searchForMessage(String user1Email, String secondEmail) throws IOException {

        Path filePath = Paths.get(user1Email + "_" + secondEmail + ".txt");
        Path filePath2 = Paths.get(secondEmail + "_" + user1Email + ".txt");

        File file = new File(user1Email + "_" + secondEmail + ".txt");
        File file2 = new File(secondEmail + "_" + user1Email + ".txt");

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

        Path conversation = Paths.get(email + "_Conversations.txt");
        List<String> lines = Files.readAllLines(conversation);



            for (String chat : lines) {
                Path chatAddress = Paths.get(chat + ".txt");
                List<String> chatLines = Files.readAllLines(chatAddress);
                String checkRecipient = chatLines.get(chatLines.size() - 2);

                if (!checkRecipient.equals(email + ": ")) {
                    newMessagesCount += newMessagesCount + 1;
                    System.out.println("You have " + newMessagesCount + " new message"); if (newMessagesCount > 1) { System.out.print("s"); }

                }
            }






    }
    public static void newMessagesShow(String email) throws IOException {

        Path conversation = Paths.get(email + "_Conversations.txt");
        List<String> lines = Files.readAllLines(conversation);


            for (String chat : lines) {
                Path chatAddress = Paths.get(chat + ".txt");
                List<String> chatLines = Files.readAllLines(chatAddress);
                String checkRecipient = chatLines.get(chatLines.size() - 2);

                if (!checkRecipient.equals(email + ": ")) {

                    System.out.println("You have a new message from " + checkRecipient);
                    System.out.println(chatLines.get(chatLines.size()-1));
                    newMessagesCount --;
                    FileWriter scribble2 = new FileWriter(chat + ".txt", true);
                    scribble2.write("\n<-seen->");
                    scribble2.close();
                }
            }






    }





}
