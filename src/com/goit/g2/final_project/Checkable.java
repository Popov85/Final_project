package com.goit.g2.final_project;

import java.util.InputMismatchException;

/**
 * Interface {@code Checkable}  for validating a credit card number in two steps:
 * 1. General input errors;
 * 2. Luhn algorithm;
 * @author G2 team at GoJavaOnline#3:
 * @author Igor Stolyarov
 * @author Serhii Toporko
 * @author Roman Kalyga
 * @author Andrii Popov
 */
public interface Checkable {

        /**
         * Checks for general mistakes in input:
         * 1. letters instead of numbers;
         * 2. Quantity of letters more or less 16;
         * 3. Ignores spaces between number groups if present.
         * @return true if what a user entered is a plausible credit card number, false otherwise
         * @throws InputMismatchException
         */
        boolean isInputValid() throws InputMismatchException;

        /**
         * Checks the checksum according to Luhn algorithm
         * @return true if the number is valid, false otherwise;
         */
        boolean isNumberValid();
}
