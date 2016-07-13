package com.goit.g2.final_project.application;

import com.goit.g2.final_project.validators.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class {@code Authentification} for distinguishing a true user from a robot or
 * fraudulent behaviour user
 */
public final class Authentication {

        private final User user;

        private final BlockedUsersRepository repo;

        private final Scanner scanner;

        public Authentication(User user, Scanner scanner) {
                this.user = user;
                this.scanner = scanner;
                this.repo = BlockedUsersRepository.getInstance();
        }

        /**
         * Whether identifies a user or blocks a user with such an e-mail
         * after a number of unsuccessful attempts
         * @throws InputMismatchException when empty string was entered
         */
        public void authenticate() throws InputMismatchException {
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
                // Do smth. here with further validation
        }


}
