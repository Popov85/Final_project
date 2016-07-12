package com.goit.g2.final_project.application;

import com.goit.g2.final_project.efewfef.CardNumberValidatorCustom;
import com.goit.g2.final_project.efewfef.Validator;

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

                do {
                        System.out.println("Enter card: ");
                        card= scanner.nextLine();
                        validator = new CardNumberValidatorCustom(card);
                        if (validator.isNumberValid()) {
                                user.clearAttempts();
                                break;
                        }
                        // suspicious behaviour
                        user.increaseAttempts(repo);
                        System.out.println("Another attempt?: ");
                        attempt= scanner.nextLine();
                } while (!attempt.equals("not"));

                System.out.println("Has this user been added to blocked: "+repo.hasBlockedUser(user));
                // Do smth. here with further validation
        }
}
