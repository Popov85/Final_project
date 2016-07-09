package com.goit.g2.final_project.application;

import com.goit.g2.final_project.usage.CardNumberValidator;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Андрей on 07.07.2016.
 */
public class AppPerformanceTest {

        private static long LOWER_RANGE = 100000000000L; //assign lower range value
        private static long UPPER_RANGE = 9223372036854775807L; //assign upper range value

        public static void main(String[] args) {

                List<String> cards = new ArrayList<String>();
                initializer(cards);

                long startTime = System.nanoTime();
                System.out.println("We've got:"+testApache(cards)+" valid credit cards via Apache Luhn implementation");
                System.out.println("Time elapsed: "+(System.nanoTime() - startTime) / 1000000 +" ms");

                startTime = System.nanoTime();
                testApacheWrapper(cards);
                System.out.println("Apache wrapper implementation: "+(System.nanoTime() - startTime) / 1000000 +" ms");

                startTime = System.nanoTime();
                System.out.println("We've got:"+testCustom(cards)+" valid credit cards via Custom Luhn implementation");
                System.out.println("Time elapsed: "+(System.nanoTime() - startTime) / 1000000 +" ms");

                startTime = System.nanoTime();
                System.out.println("We've got:"+testStandard(cards)+" valid credit cards via Standard Luhn implementation");
                System.out.println("Time elapsed: "+(System.nanoTime() - startTime) / 1000000 +" ms");

                testDifference(cards);
        }

        private static void initializer(List cards) {
                Random rnd = new Random();
                for (int i=0; i<100000; i++) {
                        long value = LOWER_RANGE +
                                        (long)(rnd.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
                        String cardNumber = Objects.toString(value, null);
                        cards.add(cardNumber);
                }
        }

        private static int testStandard(List<String> cards) {
                int counter = 0;
                for (String e: cards) {
                        Validator validator = new CardNumberValidatorStandard(e);
                        if (validator.isNumberValid()) counter++;
                }
                return counter;
        }

        private static int testCustom(List<String> cards) {
                int counter = 0;
                for (String e: cards) {
                        Validator validator = new CardNumberValidatorCustom(e);
                        if (validator.isNumberValid()) counter++;
                }
                return counter;
        }

        private static int testApache(List<String> cards) {
                int counter = 0;
                for (String e: cards) {
                        LuhnCheckDigit validator = new LuhnCheckDigit();
                        if (validator.isValid(e)) counter++;
                }
                return counter;
        }

        private static int testApacheWrapper(List<String> cards) {
                int counter = 0;
                for (String e: cards) {
                        Validator validator = new CardNumberValidatorApache(e);
                        if (validator.isNumberValid()) counter++;
                }
                return counter;
        }

        private static void testDifference(List<String> cards) {
                boolean resultStandard, resultCustom;
                int diff = 0;
                for (String e: cards) {
                        CardNumberValidator validator = new CardNumberValidator(e);
                        resultStandard = validator.isNumberValid();
                        resultCustom = validator.isNumberValidCustom();
                        if (resultStandard!=resultCustom) {
                                System.out.println("Card is: "+e+", Standard gave: "+resultStandard+", Custom gave: "+resultCustom);
                                diff++;
                        }

                }
                System.out.println(diff+" differences");
        }
}
