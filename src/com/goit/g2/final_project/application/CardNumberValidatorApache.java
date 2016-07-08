package com.goit.g2.final_project.application;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

/**
 * Created by Андрей on 08.07.2016.
 */
public final class CardNumberValidatorApache extends AbstractValidator {

        public CardNumberValidatorApache() {
                super("");
        }

        public CardNumberValidatorApache(String sequence) {
                super(sequence);
        }

        @Override
        public boolean isNumberValid() {
                LuhnCheckDigit validator = new LuhnCheckDigit();
                return validator.isValid(cardNumber);
        }
}
