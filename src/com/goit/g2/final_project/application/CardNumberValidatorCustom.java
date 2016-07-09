package com.goit.g2.final_project.application;

/**
 * Created by Андрей on 08.07.2016.
 */
public final class CardNumberValidatorCustom extends AbstractValidator {

        // ASCII codes for numbers {0-9}
        private final static int ASCII_NUMERIC_CODES[] = new int[60];

        //private final static Map<Integer,Integer> map = new HashMap<Integer, Integer>();

        //private final static int QUANTITY_OF_NUMBERS = 10;

        /*static {
                map.put(48,0);
                map.put(49,1);
                map.put(50,2);
                map.put(51,3);
                map.put(52,4);
                map.put(53,5);
                map.put(54,6);
                map.put(55,7);
                map.put(56,8);
                map.put(57,9);
        }*/

        // Only for better performance purposes!
        static {
                ASCII_NUMERIC_CODES[48] = 1;
                ASCII_NUMERIC_CODES[49] = 2;
                ASCII_NUMERIC_CODES[50] = 3;
                ASCII_NUMERIC_CODES[51] = 4;
                ASCII_NUMERIC_CODES[52] = 5;
                ASCII_NUMERIC_CODES[53] = 6;
                ASCII_NUMERIC_CODES[54] = 7;
                ASCII_NUMERIC_CODES[55] = 8;
                ASCII_NUMERIC_CODES[56] = 9;
        }



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

        /*private int getInt(char c) {
                return Character.getNumericValue(c);
        }*/

        // Returns integer assiciated with ASCII code
        /*private int getInt(char c) throws InputMismatchException {
                // ANSII code for c is:
                int asciiCode = (int) c;
                for (int i = 0;i<=QUANTITY_OF_NUMBERS;i++) {
                        if (ASCII_NUMERIC_CODES[i]==asciiCode) return i;
                }
                throw new InputMismatchException("Not all characters are numbers!");
        }*/

        /*private int getInt(char c) {
                return map.get((int) c);
        }*/

        private int getInt(char c) {
                return ASCII_NUMERIC_CODES[(int) c];
        }
}
