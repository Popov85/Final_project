package com.goit.g2.final_project;

import java.util.InputMismatchException;

/**
 * Interface {@code Validator}  for validating a credit card number in two steps:
 * 1. General input errors;
 * 2. Luhn algorithm;
 * @author G2 team at GoJavaOnline#3 including:
 * @author Igor Stolyarov
 * @author Serhii Toporko
 * @author Roman Kalyga
 * @author Andrii Popov
 */
public interface Validator {

        /**
         * Checks for general mistakes in input:
         * 1. Letters instead of/mixed with numbers;
         * 2. Quantity of letters more or less 16;
         * 3. Ignores spaces between number groups if present.
         * @return true if what a user entered is a plausible credit card number, false otherwise
         * @throws InputMismatchException if nothing (empty string) was entered by a user
         */
        boolean isInputValid() throws InputMismatchException;

        /**
         * Checks the checksum according to Luhn algorithm
         * @return true if the number is valid, false otherwise;
         */
        boolean isNumberValid();
}
