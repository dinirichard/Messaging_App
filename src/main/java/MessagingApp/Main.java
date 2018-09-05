package MessagingApp;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        IOUtils ioUtils = new IOUtils();   ///     Placing the class as an object to skip the static requirement from Methods

        while (true) {

            ioUtils.writeMessage("Welcome to java Messenger");
            ioUtils.writeMessage("");
            ioUtils.writeMessage("Choose your action:");
            ioUtils.writeMessage("1 - create new user;");
            ioUtils.writeMessage("2 - Login;");
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    ioUtils.writeMessage("Enter email");
                    String email = scanner.next();
                    ioUtils.writeMessage("Enter fullname");
                    String name = scanner.next();
                    ioUtils.writeMessage("Enter your Age");
                    int age = scanner.nextInt();
                    ioUtils.writeMessage("Enter password");
                    String password = scanner.next();
                    UserServices.addUser(email, name, age, password);
                    break;

                case 2:
                    System.out.println("Login Page");
                    System.out.println("Enter email");
                    String loginEmail = scanner.next();
                    System.out.println("Enter password");
                    String loginPassword = scanner.next();
                    UserServices.login(loginEmail, loginPassword);
                    break;

            }
        }
    }

}
