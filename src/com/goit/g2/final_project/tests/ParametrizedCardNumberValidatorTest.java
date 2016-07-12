package com.goit.g2.final_project.tests;

import com.goit.g2.final_project.efewrf.CardNumberValidatorCustom;
import com.goit.g2.final_project.efewrf.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith (value = Parameterized.class)
public class ParametrizedCardNumberValidatorTest {

        private String str;
        private boolean expectedLuhn;
        Validator cardNumber;


        public ParametrizedCardNumberValidatorTest (String str, boolean expectedLuhn) {
                this.str = str;
                this.expectedLuhn = expectedLuhn;
        }

        @Parameterized.Parameters(name = "{index}: isValid({0})")
        public static Collection<Object[]> getParametrizedData() {
                return Arrays.asList(new Object[][]{
                                {"4485982114912228", true},            // valid number
                                {"4485 9821 1491 2228",true},          // valid number with spaces
                                {"  6011219609778337  ", true},        // valid number with spaces
                                {"7011219609778337", false},           // valid number, invalid Luhn
                                {"4587 x578 ****  784d", false},      // not all numbers
                                {"965", false},                       // too short number (VCC instead of number)
                                {"7896 7896 8965 7895 555", false},    // quantity [12-19]
                                {"7896 7896 8965 7895 5555", false},  // too long number
                                {"1000 0000 0000",false},              // valid number, invalid Luhn
                                {"0000 0000 0000", true},              // Min valid number
                                {"2016/06/12", false},                // Date exp. field (field mismatch)
                                {"0000 0000 0000 0000 000", true},     // Longest valid number
                                {"9999 9999 9999 9999 999", false},    // Max possible number
                                {"Andrii Popov", false},              // FIO instead of number
                                {"Игорь Столяров", false},            // Russian FIO instead of numbers
                                {"q123 9633 4521 6521", false},       // Typo at the first position
                                {"5632 9658 8547 9696 63o", false},   // Letter o instead of zero at the last position
                                {"4111111111111111", true},            // Valid Visa card
                                {"XXXX-XXXX-XXXX-XXXX", false},       // Pattern was entered
                });
        }

        @Before
        public void setUp() throws Exception {
                cardNumber = new CardNumberValidatorCustom(str);
        }

        @After
        public void tearDown() throws Exception {
                cardNumber = null;
        }


        @Test (timeout = 1000)
        public void isNumberValid() throws Exception {
                assertThat(cardNumber.isNumberValid(), is(expectedLuhn));
        }
}