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
            scanner.nextLine();
            switch (userOption) {
                case 1:
                    App_Navigation.CreateNewUserPage();
                    break;

                case 2:
                    App_Navigation.login();
                    break;
            }
        }
    }
}
