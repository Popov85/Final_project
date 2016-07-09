package com.goit.g2.final_project.tests;

import com.goit.g2.final_project.usage.CardNumberValidator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public class CardNumberValidatorExceptionTest {

        private CardNumberValidator cardNumber;

        @Rule
        public final ExpectedException exception = ExpectedException.none();

        @Before
        public void setUp() throws Exception {
                cardNumber = new CardNumberValidator("     "); // Empty string was entered
        }

        @After
        public void tearDown() throws Exception {
                cardNumber = null;
        }

        @Test (timeout = 1000)
        public void isInputValid() throws Exception {
                // Checks for exception
                exception.expect(InputMismatchException.class);
                Assert.assertTrue(cardNumber.isInputValid());
        }
}