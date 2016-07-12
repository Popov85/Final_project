package com.goit.g2.final_project.validators;

/**
 * Interface {@code validators}  for validating a credit card number in two steps:
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
         * Checks the checksum according to Luhn algorithm
         * @return true if the number is valid, false otherwise;
         */
        boolean isNumberValid();
}
