package com.goit.g2.final_project.application;

import java.util.InputMismatchException;

/**
 * Created by Андрей on 08.07.2016.
 */
public abstract class AbstractValidator implements Validator {

        private static final int MIN_LENGTH = 12;
        private static final int MAX_LENGTH = 19;

        protected String cardNumber;

        protected AbstractValidator() {
                this("");
        }

        protected AbstractValidator(String sequence) {
                this.cardNumber = prepareSequence(sequence);
        }

        private String prepareSequence(String sequence) {
                return sequence.replace(" ", "");
        }


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
