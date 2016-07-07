package com.goit.g2.final_project.tests;

import com.goit.g2.final_project.CardNumberValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Андрей on 06.07.2016.
 */


@RunWith (value = Parameterized.class)
public class ParametrizedCardNumberValidatorTest {

        private String str;
        boolean expectedInput;
        boolean expectedLuhn;

        CardNumberValidator cardNumber;


        public ParametrizedCardNumberValidatorTest (String str, boolean expectedInput, boolean expectedLuhn) {
                this.str = str;
                this.expectedInput = expectedInput;
                this.expectedLuhn = expectedLuhn;
        }

        @Parameterized.Parameters(name = "{index}: isValid({0})")
        public static Collection<Object[]> getParametrizedData() {
                return Arrays.asList(new Object[][]{
                                {"4485982114912228", true, true},            // valid number
                                {"4485 9821 1491 2228",true, true},          // valid number with spaces
                                {"  6011219609778337  ", true, true},        // valid number starting/ending with spaces
                                {"7011219609778337", true, false},           // valid number, invalid Luhn
                                {"4587 x578 ****  784d", false, false},      // invalid number
                                {"9965", false, false},                      // too short number
                                {"7896 7896 8965 7895 555", true ,false},    // quantity [12-19]
                                {"7896 7896 8965 7895 5555", false, false}   // too long number
                });
        }

        @Before
        public void setUp() throws Exception {
                cardNumber = new CardNumberValidator(str);
        }

        @After
        public void tearDown() throws Exception {
                cardNumber = null;
        }

        @Test (timeout = 1000)
        public void isInputValid() throws Exception {
                assertThat(cardNumber.isInputValid(), is(expectedInput));

        }

        @Test (timeout = 1000)
        public void isNumberValid() throws Exception {
                assertThat(cardNumber.isNumberValid(), is(expectedLuhn));
        }

        @Test (timeout = 1000)
        public void isNumberValidCustom() throws Exception {
                assertThat(cardNumber.isNumberValidCustom(), is(expectedLuhn));
        }

}