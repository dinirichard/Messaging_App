package MessagingApp;

import java.io.IOException;
import java.util.Scanner;

public class App_Navigation {
    //Scanner scanner = new Scanner(System.in);

    public static void CreateNewUserPage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("Enter fullname");
        String name = scanner.nextLine();
        System.out.println("Enter your Age");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        UserServices.addUser(email, name, age, password);

    }

    public static void login() throws IOException{

        Scanner scanner = new Scanner(System.in);
        Scanner menu = new Scanner(System.in);

        System.out.println("Login Page");
        System.out.println("Enter email");
        String loginEmail = scanner.nextLine();
        System.out.println("Enter password");
        String loginPassword = scanner.nextLine();
        boolean login = UserServices.login(loginEmail, loginPassword);
        if (login == true) {

                loginMenu(loginEmail);

            return;
        }

    }

    public static void loginMenu(String loginEmail) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Scanner menu = new Scanner(System.in);

        while (true) {

            //UserServices.newMessagesSearch(loginEmail);
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        UserServices.newMessagesSearch(loginEmail);
                        //UserServices.newMessagesShow(loginEmail);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();


            System.out.println("Press 1 to Send messages");
            System.out.println("Press 2 to read new messages");
            System.out.println("Press 3 to check list of contacts");
            System.out.println("Press 4 to Logout");

            int loginMenuOpt = menu.nextInt();
            menu.nextLine();
            switch (loginMenuOpt) {

                case 1:
                    System.out.println("Please insert email of the user");
                    String emailToSearch4 = scanner.nextLine();
                    sendMessage(loginEmail, emailToSearch4);

                    break;


                case 2:

//                    Thread thread2 = new Thread(() -> {
//                        try {
//                            while (true) {
//                                Thread.sleep(1000);
//                                UserServices.newMessagesShow(loginEmail);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    });
//                    thread2.setDaemon(true);
//                    thread2.start();

                    System.out.println("Press 1 to search for contact");
                    System.out.println("Press 2 to go back");
                    int loginMenu2 = menu.nextInt();
                    menu.nextLine();

                    if (loginMenu2 == 1) {
                        String contactToSearch4 = scanner.nextLine();
                        sendMessage(loginEmail, contactToSearch4);
                    } else {
                        break;
                    }
                    break;

                case 3:

                    break;

                case 4:
                        return;


            }
        }
    }

    public static void sendMessage(String loginEmail, String emailToSearch4) throws IOException {

        Scanner scanner = new Scanner(System.in);

        boolean searchResult = UserServices.searchUser(emailToSearch4);
        if (searchResult == true) {
            System.out.println("Please type in your message -> \n");
            String message = scanner.nextLine();
            UserServices.messageUser(loginEmail, emailToSearch4, message);
            System.out.println("Message has been sent!");
        } else {
            loginMenu(loginEmail);
        }

    }
}
