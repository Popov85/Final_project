package com.goitg2.final_project.tests;

import com.goitg2.final_project.validators.CardNumberValidatorCustom;
import com.goitg2.final_project.validators.Validator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public class CardNumberValidatorExceptionTest {

        private Validator cardNumber;

        @Rule
        public final ExpectedException exception = ExpectedException.none();

        @Before
        public void setUp() throws Exception {
                cardNumber = new CardNumberValidatorCustom("     "); // Empty string was entered
        }

        @After
        public void tearDown() throws Exception {
                cardNumber = null;
        }

        @Test (timeout = 1000)
        public void isInputValid() throws Exception {
                // Checks for exception
                exception.expect(InputMismatchException.class);
                Assert.assertTrue(cardNumber.isNumberValid());
        }
}