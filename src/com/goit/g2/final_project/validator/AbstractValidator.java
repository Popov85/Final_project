package com.goit.g2.final_project.validator;

import java.util.InputMismatchException;

/**
 * This class provides a skeletal implementation of the validator interface for
 * validating bank cards only;
 * It fully realizes isInputValid() method to facilitate the implementation of
 * concrete classes for Luhn validation;
 * A programmer may use it as a guard clause in his realization of isNumberValid() method;
 * @author G2 team at GoJavaOnline#3 including:
 * @author Igor Stolyarov
 * @author Serhii Toporko
 * @author Roman Kalyga
 * @author Andrii Popov
 */
public abstract class AbstractValidator implements Validator {

        /** Minimal possible length of credit cards */
        private static final int MIN_LENGTH = 12;
        /** Maximal possible length of credit cards */
        private static final int MAX_LENGTH = 19;

        /** Actual bank card number to be validated for correctness */
        protected String cardNumber;


        /**No parameter constructor. For invocation by subclass constructors (implies an empty string)*/
        protected AbstractValidator() {
                this("");
        }

        /**One parameter constructor. For invocation by subclass constructors. Ignores spaces!*/
        protected AbstractValidator(String sequence) {
                this.cardNumber = prepareSequence(sequence);
        }

        private String prepareSequence(String sequence) {
                return sequence.replace(" ", "");
        }

        /**
         * Checks for general mistakes in input:
         * 1. Ignores spaces between number groups if present.
         * 2. Quantity of letters within the range [12- 19];
         * 3. Letters instead of/mixed with numbers;
         * @return true if what a user entered is a plausible credit card number, false otherwise
         * @throws InputMismatchException if nothing (empty string) was entered by a user
         */

        protected boolean isInputValid() throws InputMismatchException {
                if (cardNumber.isEmpty()) throw new InputMismatchException("Input ERROR: empty string!");
                if (cardNumber.length()< MIN_LENGTH || cardNumber.length()> MAX_LENGTH) return false;
                if (isAllNumbers(cardNumber)) {
                        return true;
                } else {
                        return false;
                }
        }

        private boolean isAllNumbers(String sequence) {
                char c;
                boolean isDigit;
                for (int i=0; i < sequence.length(); i++) {
                        c = sequence.charAt(i);
                        isDigit = (c >= '0' && c <= '9');
                        if (!isDigit) return false;
                }
                return true;
        }
}
