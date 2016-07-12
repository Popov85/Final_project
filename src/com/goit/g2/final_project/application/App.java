package com.goit.g2.final_project.application;

import com.goit.g2.final_project.efewrf.CardNumberValidatorCustom;
import com.goit.g2.final_project.efewrf.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Андрей on 05.07.2016.
 */
public class App {
        public static void main(String[] args) {

                BlockedUsersRepository repo = BlockedUsersRepository.getInstance();
                Validator validator;
                Scanner scanner = new Scanner(System.in);
                String email, card, attempt;

                System.out.println("Enter email: ");
                email= scanner.nextLine();

                User user = new User(email);

                try {
                        do {
                                if (user.isBlocked()) {
                                        break;
                                }
                                System.out.println("Enter card: ");
                                card= scanner.nextLine();
                                validator = new CardNumberValidatorCustom(card);
                                if (validator.isNumberValid()) {
                                        user.clearAttempts();
                                        break;
                                }
                                // suspicious behaviour
                                user.increaseAttempts(repo);
                                System.out.print("Another attempt?: ");
                                attempt= scanner.nextLine();
                        } while (!attempt.equals("not"));

                        if (repo.hasBlockedUser(user)) {
                                System.out.println("Too many attempts! You were blocked!");
                        }
                        else {
                                System.out.println("Card has been accepted!");
                        }
                } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                }
                // Do smth. here with further validation
        }
}
