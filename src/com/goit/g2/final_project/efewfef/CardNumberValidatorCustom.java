package com.goit.g2.final_project.efewfef;

/**
 * Class provides a custom realization of Luhn validation algorithm
 * @author Andrii Popov
 */
public final class CardNumberValidatorCustom extends AbstractValidator {

        // ASCII codes for numbers {0-9}
        private final static int ASCII_NUMERIC_CODES[] = new int[60];

        // Only for better performance purposes!
        static {
                ASCII_NUMERIC_CODES[48] = 0;
                ASCII_NUMERIC_CODES[49] = 1;
                ASCII_NUMERIC_CODES[50] = 2;
                ASCII_NUMERIC_CODES[51] = 3;
                ASCII_NUMERIC_CODES[52] = 4;
                ASCII_NUMERIC_CODES[53] = 5;
                ASCII_NUMERIC_CODES[54] = 6;
                ASCII_NUMERIC_CODES[55] = 7;
                ASCII_NUMERIC_CODES[56] = 8;
                ASCII_NUMERIC_CODES[57] = 9;
        }

        public CardNumberValidatorCustom() {
                super("");
        }

        public CardNumberValidatorCustom(String sequence) {
                super(sequence);
        }


        /** Custom implementation of Luhn algorithm */
        @Override
        public boolean isNumberValid() {
                // Guard clause
                if (!isInputValid()) return false;

                int sum = 0, curr, next, doubled;
                int lastIndex = cardNumber.length()-1;
                for (int i = lastIndex - 1; i >= 0; i -= 2) {
                        curr = getInt(cardNumber.charAt(i));
                        if (i != 0) {
                                next = getInt(cardNumber.charAt(i-1));
                        } else {
                                next = 0;
                        }
                        doubled = 2 * curr;
                        if (doubled > 9) {
                                doubled = (doubled % 10) + 1; //doubled -=9;
                        }
                        sum += doubled + next;
                }
                int last = getInt(cardNumber.charAt(lastIndex));
                sum +=last;
                return (sum % 10 == 0);
        }

        private int getInt(char c) {
                return ASCII_NUMERIC_CODES[(int) c];
                //return Character.getNumericValue(c);
        }
}
