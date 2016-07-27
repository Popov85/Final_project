package com.goitg2.final_project.application;

import com.goitg2.final_project.validators.CardNumberValidatorCustom;
import com.goitg2.final_project.validators.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class {@code PreAuthentication} for distinguishing a true user from a robot or detecting a
 * fraudulent behaviour user. Some checking before real authentication.
 * @author Andrii Popov
 */
public final class PreAuthentication {

        private final User user;
        private final BlockedUsersRepository repo;
        private final Scanner scanner;

        public PreAuthentication(User user, Scanner scanner) {
                this.user = user;
                this.scanner = scanner;
                this.repo = BlockedUsersRepository.getInstance();
        }

        /**
         * Whether identifies a user or blocks a user with such an e-mail
         * after a number of unsuccessful attempts
         * @throws InputMismatchException when empty string is entered
         */
        public void preAuthenticate() throws InputMismatchException {
                String card, attempt;
                Validator validator;
                do {
                        if (repo.hasBlockedUser(user)) {
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
                        user.increaseAttempts();
                        System.out.print("Another attempt? (yes/not) ");
                        attempt= scanner.nextLine();
                } while (!attempt.equals("not"));
                if (repo.hasBlockedUser(user)) {
                        System.out.println("Too many attempts! You were blocked!");
                }
                else {
                        System.out.println("Card has been accepted!");
                }
                // Do smth. here with further validation
        }
}
