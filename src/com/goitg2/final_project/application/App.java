package com.goitg2.final_project.application;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Emulation of real app when users enter wrong credit cards and keep getting blocked
 * after a number of unsuccessful attempts
 * @author Andrii Popov
 */
public class App {

        /** Singleton. Repository of temporarily blocked users */
        private static final BlockedUsersRepository BLOCKED = BlockedUsersRepository.getInstance();

        private static final ConcurrentMap<String, User> SESSION_USERS = new ConcurrentHashMap<String, User>();

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                String attempt;
                do {
                        anotherUser(scanner);
                        displayBlocked();
                        System.out.print("More users?\n");
                        attempt= scanner.nextLine();
                } while (!attempt.equals("not"));

        }

        private static void anotherUser(Scanner scanner) {
                User user = null;
                System.out.println("Enter email: ");
                String email= scanner.nextLine();
                if (hasUser(email)) {
                        user = getSessionUser(email);
                } else {
                        try {
                                user = new User(email);
                                addSessionUser(user);
                        } catch (InputMismatchException e) {
                                System.out.println("Invalid e-mail...");
                                return;
                        }
                }
                PreAuthentication identity = new PreAuthentication(user, scanner);
                try {
                        identity.preAuthenticate();
                } catch (InputMismatchException e) {
                        System.out.println("Invalid card number...");
                        return;
                }
        }

        public static boolean hasUser(String email) {
                if (SESSION_USERS.get(email)!=null) {
                        return true;
                }else {
                        return false;
                }
        }

        public static void addSessionUser(User user) {
                SESSION_USERS.put(user.getEmail(),user);
        }

        public static User getSessionUser(String email) {
                return SESSION_USERS.get(email);
        }


        private static void displayBlocked() {
                System.out.println(BLOCKED.toString());
        }
}
