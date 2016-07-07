package com.goit.g2.final_project;

import java.util.InputMismatchException;
/**
 * Created by Андрей on 05.07.2016.
 */
public final class CardNumberValidator implements Validator {

        private static final int MIN_LENGTH = 12;
        private static final int MAX_LENGTH = 19;


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

        /**
         * Custom implementation of isNumberValid() method
         * @return true if a cardNumber is valid, false - otherwise
         */
        public final boolean isNumberValidCustom() {
                // Guard clause
                if (!isInputValid()) return false;

                int sum = 0, curr, next, doubled;
                char[] buffer = cardNumber.toCharArray();
                int lastIndex = buffer.length-1;
                for (int i = lastIndex - 1; i >= 0; i -= 2) {
                        curr = getInt(buffer[i]);
                        if (i != 0) {
                                next = getInt(buffer[i - 1]);
                        } else {
                                next = 0;
                        }
                        doubled = 2 * curr;
                        if (doubled > 9) {
                                doubled = (doubled % 10) + 1;
                        }
                        sum += doubled + next;
                }
                int last = getInt(buffer[lastIndex]);
                sum +=last;
                return (sum % 10 == 0);
        }

        private int getInt(char c) {
                return Character.getNumericValue(c);
        }

}
