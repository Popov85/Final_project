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


        public final boolean isInputValid() throws InputMismatchException {
                if ("".equals(cardNumber)) throw new InputMismatchException("Input ERROR!");
                if (cardNumber.length() != LENGTH) return false;
                if (isAllNumbers(this.cardNumber)) {
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

        public final boolean isNumberValid() {
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
}
