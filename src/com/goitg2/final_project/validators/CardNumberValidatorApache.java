package com.goitg2.final_project.validators;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

/**
 * Wrapper class over Apache realization of Luhn algorithm
 * @author GoJavaOnline #3 team
 * @author Apache Software Foundation
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
