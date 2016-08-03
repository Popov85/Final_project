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
        private final String card;

        public PreAuthentication(User user, String card) {
                this.user = user;
                this.card = card;
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
        public String preAuthenticate() throws InputMismatchException {
                String message = "";
                Validator validator;
                /*if (repo.hasForeverBlockedUser(user)) {
                        message = "CONST: You are constantly blocked!";
                        return message;
                }
                if (repo.hasTempBlockedUser(user)) {
                        message = "TEMP: You are temporarily blocked! Too many attempts!";
                        return message;
                }*/
                validator = new CardNumberValidatorCustom(card);
                if (validator.isNumberValid()) {
                        user.clearAttempts();
                        message = "SUCCESS: Your card has been accepted!";
                } else {
                        // Suspicious behaviour
                        int digit = user.increaseAttempts();
                        if (digit==3) {
                                message = "CONST: You are constantly blocked!";
                        } else if (digit==2) {
                                message = "TEMP: You are temporarily blocked! Too many attempts!";
                        } else {
                                message = "DECLINE: You have "+(User.QUANTITY_OF_ATTEMPTS-user.getFailedAttempts())+ " attempts left";
                        }

                }
                System.out.println(message);
                return message;
        }
}
