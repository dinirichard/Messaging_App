package MessagingApp;

import java.io.IOException;
import java.util.Scanner;

public class UserApp {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Scanner menu = new Scanner(System.in);

        while (true) {

            System.out.println("Welcome to java Messenger");
            System.out.println("");
            System.out.println("Choose your action:");
            System.out.println("1 - create new user;");
            System.out.println("2 - Login;");
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    System.out.println("Enter email");
                    String email = scanner.next();
                    System.out.println("Enter fullname");
                    String name = scanner.next();
                    System.out.println("Enter your Age");
                    int age = scanner.nextInt();
                    System.out.println("Enter password");
                    String password = scanner.next();
                    UserServices.addUser(email, name, age, password);
                    break;

                case 2:
                    System.out.println("Login Page");
                    System.out.println("Enter email");
                    String loginEmail = scanner.next();
                    System.out.println("Enter password");
                    String loginPassword = scanner.next();
                    boolean login = UserServices.login(loginEmail, loginPassword);
                    if (login == true) {
                        while (true) {
                            System.out.println("Press 1 to Send messages");
                            System.out.println("Press 2 to read new messages");
                            System.out.println("Press 3 to check list of contacts");
                            System.out.println("Press 4 to Logout");

                            int loginMenu = menu.nextInt();
                            switch (loginMenu) {

                                case 1:
                                    System.out.println("Please insert email of the user");
                                    String emailToSearch4 = scanner.next();
                                    boolean searchResult = UserServices.searchUser(emailToSearch4);
                                    if (searchResult == true) {
                                        System.out.println("Please type in your message -> \n");
                                        String message = scanner.next();
                                        UserServices.messageUser(loginEmail, emailToSearch4, message);
                                        System.out.println("Message has been sent!");
                                    } else {
                                        break;
                                    }

                                    break;


                                case 2:

                                    break;

                                case 3:

                                    break;

                            }
                            if (loginMenu == 4) {
                                break;
                            }


                        }
                    }
                    break;
            }
        }
    }
}
