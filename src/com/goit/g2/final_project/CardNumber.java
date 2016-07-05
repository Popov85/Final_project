package com.goit.g2.final_project;

import java.security.InvalidAlgorithmParameterException;
import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public final class CardNumber {

        private static final int LENGTH = 16;

        private String cardNumber;

        public CardNumber(String sequence) throws InputMismatchException, InvalidAlgorithmParameterException {
                sequence = prepareSequence(sequence);
                if (isInputValid(sequence) && isNumberValid(sequence)) {
                        this.cardNumber = sequence;
                }
                else {
                        throw new InvalidAlgorithmParameterException();
                }
        }

        private String prepareSequence(String sequence) {
                return sequence.replace(" ", "");
        }

        private boolean isInputValid(String sequence) throws InputMismatchException {
                if ("".equals(sequence)) throw new InputMismatchException("Input ERROR!");
                if (sequence.length()!=LENGTH) return false;
                if (isAllNumbers(sequence)) {
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

        private boolean isNumberValid(String number){
                int sum = 0;
                boolean alternate = false;
                for (int i = number.length() - 1; i >= 0; i--) {
                        int n = Integer.parseInt(number.substring(i, i + 1));
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

        public String getCardNumber() {
                return cardNumber;
        }
}
