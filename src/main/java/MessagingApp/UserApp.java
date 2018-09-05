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
                    break;

                case 2:
                    System.out.println("Login Page");
                    System.out.println("Enter email");
                    String loginEmail = scanner.nextLine();
                    System.out.println("Enter password");
                    String loginPassword = scanner.nextLine();
                    boolean login = UserServices.login(loginEmail, loginPassword);
                    if (login == true) {
                        while (true) {
                            //UserServices.newMessagesSearch(loginEmail);
                            Thread thread = new Thread(() -> {
                                try {
                                    Thread.sleep(1000);
                                    UserServices.newMessagesSearch(loginEmail);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                            thread.start();


                            System.out.println("Press 1 to Send messages");
                            System.out.println("Press 2 to read new messages");
                            System.out.println("Press 3 to check list of contacts");
                            System.out.println("Press 4 to Logout");

                            int loginMenu = menu.nextInt();
                            menu.nextLine();
                            switch (loginMenu) {

                                case 1:
                                    System.out.println("Please insert email of the user");
                                    String emailToSearch4 = scanner.nextLine();
                                    boolean searchResult = UserServices.searchUser(emailToSearch4);
                                    if (searchResult == true) {
                                        System.out.println("Please type in your message -> \n");
                                        String message = scanner.nextLine();
                                        UserServices.messageUser(loginEmail, emailToSearch4, message);
                                        System.out.println("Message has been sent!");
                                    } else {
                                        break;
                                    }

                                    break;


                                case 2:

                                    Thread thread2 = new Thread(() -> {
                                        try {
                                            Thread.sleep(1000);
                                            UserServices.newMessagesShow(loginEmail);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                    thread2.start();

                                    System.out.println("Press 1 to search for contact");
                                    System.out.println("Press 2 to go back");
                                    int loginMenu2 = menu.nextInt();
                                    menu.nextLine();

                                    if(loginMenu2 == 1) {
                                        String contactToSearch4 = scanner.nextLine();
                                        boolean searchResult2 = UserServices.searchUser(contactToSearch4);
                                        if (searchResult2 == true) {
                                            System.out.println("Please type in your message -> \n");
                                            String message = scanner.nextLine();
                                            UserServices.messageUser(loginEmail, contactToSearch4, message);
                                            System.out.println("Message has been sent!");
                                        } else {
                                            break;
                                        }
                                    }
                                    break;

                                case 3:

                                    break;

                                case 4:

                                    break;

                            }



                        }
                    }
                    break;
            }
        }
    }
}
