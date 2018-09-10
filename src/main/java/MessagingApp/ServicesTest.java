package MessagingApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ServicesTest {

    IOUtils ioUtils;

    public ServicesTest(IOUtils ioUtils) {
        Scanner scanner = new Scanner(System.in);
        this.ioUtils = ioUtils;
    }

    public  void addUser(/*String email, String name, int age, String password*/) throws IOException {

        ioUtils.writeMessage("Enter Email: ");
        String email = ioUtils.readNextLine();
        if (ioUtils.fileExists(email + ".txt")) {
            ioUtils.writeMessage("User already existdsf");
            return;
        }
        ioUtils.writeMessage("All good");






//        User newUser = new User(email, name, age, password);
//
//        //Path email = Paths.get("output.txt");
//        FileWriter writer = new FileWriter(String.valueOf(email));
//        writer.write("Email:" + email + "\nName:" + name + "\nAge:" + age + "\nPassword:" + password);
//        writer.close();
    }

    public static void login(String email, String password) throws IOException {

        Path filePath = Paths.get(email);

        File file = new File(String.valueOf(email));

        if (!file.exists()) {                               //Check if the file Exists
            System.out.println("User does not exist");
            return;
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
                } else {
                    System.out.println("Email or password is wrong");
                    return;
                }

            } else {
                System.out.println("Email or password is wrong");
                return;
            }

        }
    }

}
