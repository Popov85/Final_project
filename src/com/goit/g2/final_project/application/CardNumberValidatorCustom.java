package com.goit.g2.final_project.application;

/**
 * Created by Андрей on 08.07.2016.
 */
public final class CardNumberValidatorCustom extends AbstractValidator {

        public CardNumberValidatorCustom() {
                super("");
        }

        public CardNumberValidatorCustom(String sequence) {
                super(sequence);
        }

        @Override
        public boolean isNumberValid() {
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
                                doubled = (doubled % 10) + 1; //doubled -=9;
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
