package com.goitg2.final_project.application;

import com.goitg2.final_project.validators.CardNumberValidatorCustom;
import com.goitg2.final_project.validators.Validator;

import java.util.InputMismatchException;

/**
 * Class {@code PreAuthentication} for distinguishing a true user from a robot or detecting a
 * fraudulent behaviour user. Some checking before real authentication.
 * @author Andrii Popov
 */
public final class PreAuthentication extends Thread {

        private final User user;
        private final BlockedUsersRepository repo;

        public PreAuthentication(User user) {
                this.user = user;
                this.repo = BlockedUsersRepository.getInstance();
        }

        @Override
        public void run() {
                preAuthenticate();
        }

        /**
         * Whether identifies a user or blocks a user with such an e-mail
         * after a number of unsuccessful attempts
         * @throws InputMismatchException when empty string is entered
         */
        private void preAuthenticate() throws InputMismatchException {
                String message=null;
                Validator validator;
                int counter = 0;
                do {
                        if (repo.hasForeverBlockedUser(user)) {
                                message = user.getEmail()+" got constantly blocked!\nAddress the system admin to get unblocked!";
                                break;
                        }
                        if (repo.hasTempBlockedUser(user)) {
                                message = user.getEmail()+" got temporarily blocked! Too many attempts! ";
                                break;
                        }
                        // !!! Keep intentionally entering invalid card number !!!Just in order to model the behaviour
                        validator = new CardNumberValidatorCustom("4845 2565 2325 2365"); // Correct number: 4485 9821 1491 2228
                        if (validator.isNumberValid()) {
                                user.clearAttempts();
                                message = "Card has been accepted!";
                                break;
                        }
                        // Suspicious behaviour
                        user.increaseAttempts();
                        counter++;
                } while (counter<=5);

                System.out.println(message);
                // Do smth. here with further validation
        }

}
