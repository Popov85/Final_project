package com.goit.g2.final_project.application;

import com.goit.g2.final_project.efewfef.CardNumberValidatorCustom;
import com.goit.g2.final_project.efewfef.Validator;

import java.security.InvalidAlgorithmParameterException;
import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public final class CardNumber {

        private String cardNumber;

        public CardNumber(String sequence) throws InputMismatchException, InvalidAlgorithmParameterException {
                Validator validator = new CardNumberValidatorCustom(sequence);
                if (validator.isNumberValid()) {
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
