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
     * This implementation gives a filer for validation user's input
     * and is the first step of validation a credit card number
     * @return true if the sequence is plausible, false if not
     * @throws InputMismatchException if an empty string was entered
     */
    @Override
        public boolean isInputValid() throws InputMismatchException {
                if ("".equals(cardNumber)) throw new InputMismatchException("Input ERROR: empty string!");
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
