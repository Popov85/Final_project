package com.goitg2.final_project.performance;

import com.goitg2.final_project.validators.CardNumberValidatorApache;
import com.goitg2.final_project.validators.CardNumberValidatorCustom;
import com.goitg2.final_project.validators.CardNumberValidatorStandard;
import com.goitg2.final_project.validators.Validator;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class AppPerformanceTest {

        private static final long LOWER_RANGE = 100000000000L; //assign lower range value
        private static final long UPPER_RANGE = 9223372036854775807L; //assign upper range value

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

                System.out.println(testDifference(cards)+" differences");
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

        private static int testDifference(List<String> cards) {
                boolean resSt, resCu, resAp;
                int diff = 0;
                for (String e: cards) {
                        Validator validator = new CardNumberValidatorStandard(e);
                        resSt = validator.isNumberValid();
                        validator = new CardNumberValidatorCustom(e);
                        resCu = validator.isNumberValid();
                        validator = new CardNumberValidatorApache(e);
                        resAp = validator.isNumberValid();
                        if (resSt!=resCu | resSt!=resAp | resCu!=resAp) {
                                System.out.println("Card is: "+e+", Standard gave: "+resSt+", Custom gave: "+resCu+ ", Apache gave: "+resAp);
                                diff++;
                        }
                }
                return diff;
        }
}
