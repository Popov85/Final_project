package com.goit.g2.final_project;

import java.security.InvalidAlgorithmParameterException;
import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public final class CardNumber {

        private String cardNumber;

        public CardNumber(String sequence) throws InputMismatchException, InvalidAlgorithmParameterException {
                CardNumberValidator validator = new CardNumberValidator(sequence);
                if (validator.isNumberValidCustom()) {
                        this.cardNumber = sequence;
                }
                else {
                        throw new InvalidAlgorithmParameterException("Input ERROR: wrong number!");
                }
        }

        public String getCardNumber() {
                return cardNumber;
        }
}
