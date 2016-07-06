package com.goit.g2.final_project;

import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public final class CardNumberValidator implements Checkable {

        private static final int LENGTH = 16;

        private String cardNumber;

        public CardNumberValidator(String sequence) {
                this.cardNumber = prepareSequence(sequence);
        }

        private String prepareSequence(String sequence) {
                return sequence.replace(" ", "");
        }


        @Override
        public final boolean isInputValid() throws InputMismatchException {
                if ("".equals(cardNumber)) throw new InputMismatchException("Input ERROR: empty string!");
                if (cardNumber.length() != LENGTH) return false;
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

        @Override
        public final boolean isNumberValid() {
                // Guard clause
                if (!isInputValid()) return false;
                int sum = 0;
                boolean alternate = false;
                for (int i = cardNumber.length() - 1; i >= 0; i--) {
                        int n = Integer.parseInt(cardNumber.substring(i, i + 1));
                        if (alternate) {
                                n *= 2;
                                if (n > 9) {
                                        n = (n % 10) + 1;
                                }
                        }
                        sum += n;
                        alternate = !alternate;
                }
                return (sum % 10 == 0);
        }

        public final boolean isNumberValidCustom() {
                // Guard clause
                if (!isInputValid()) return false;
                int sum = 0, curr, next, doubled;
                char[] buffer = cardNumber.toCharArray();
                for (int i = 0; i < LENGTH; i += 2) {
                        curr = Character.getNumericValue(buffer[i]);
                        next = Character.getNumericValue(buffer[i + 1]);
                        doubled = 2 * curr;
                        if (doubled > 9) {
                                doubled = (doubled % 10) + 1;
                        }
                        sum += doubled + next;
                }
                return (sum % 10 == 0);
        }
}
