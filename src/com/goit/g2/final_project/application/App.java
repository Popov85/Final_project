package com.goit.g2.final_project.application;

import com.goit.g2.final_project.validators.CardNumberValidatorCustom;
import com.goit.g2.final_project.validators.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Emulation of real app when users enter wrong credit cards and keep getting blocked
 * after a number of unsuccessful attempts
 * @author Andrii Popov
 */
public class App {

        private static final BlockedUsersRepository BLOCKED = BlockedUsersRepository.getInstance();

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
                System.out.println("Enter email: ");
                String email= scanner.nextLine();
                User user = null;
                try {
                        user = new User(email);
                } catch (InputMismatchException e) {
                        System.out.println("Invalid e-mail...");
                        return;
                }
                Authentication identity = new Authentication(user, scanner);
                try {
                        identity.authenticate();
                } catch (InputMismatchException e) {
                        System.out.println("Invalid card number...");
                        return;
                }
        }

        private static void displayBlocked() {
                System.out.println(BLOCKED.toString());
        }
}
