package com.goitg2.final_project.validators;

/**
 * A realization of Luhn algorithm taken from the Internet
 * the slowest implementation
 * @author unknown
 */
public final class CardNumberValidatorStandard extends AbstractValidator {

        public CardNumberValidatorStandard() {
                super("");
        }

        public CardNumberValidatorStandard(String sequence) {
                super(sequence);
        }

        @Override
        public boolean isNumberValid() {
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
}
