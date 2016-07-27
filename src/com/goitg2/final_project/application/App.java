package com.goitg2.final_project.application;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Emulation of real app when users enter wrong credit cards and keep getting blocked
 * after a number of unsuccessful attempts
 * @author Andrii Popov
 */
public class App {

        /** Singleton. Repository of temporarily blocked users */
        private static final BlockedUsersRepository BLOCKED = BlockedUsersRepository.getInstance();

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                String attempt;
                do {
                        try {
                                anotherUser(scanner);
                        } catch (InputMismatchException e) {
                                System.out.println(e.getMessage());
                        }
                        displayBlocked();
                        System.out.print("More users? (yes/not)\n");
                        attempt= scanner.nextLine();
                } while (!attempt.equals("not"));

        }

        private static void anotherUser(Scanner scanner) throws InputMismatchException {
                System.out.println("Enter email: ");
                String email= scanner.nextLine();
                if (!checkEmail(email)) {
                        throw new InputMismatchException("Wrong email!");
                }
                User user = null;
                try {
                        user = new User(email);
                } catch (InputMismatchException e) {
                        System.out.println("Invalid e-mail...");
                        return;
                }
                PreAuthentication identity = new PreAuthentication(user, scanner);
                try {
                        identity.preAuthenticate();
                } catch (InputMismatchException e) {
                        System.out.println("Invalid card number...");
                        return;
                }
        }

        private static boolean checkEmail(String email) {
                return email.indexOf('@')==-1 ? false:true;
        }

        private static void displayBlocked() {
                System.out.println(BLOCKED.toString());
        }
}
