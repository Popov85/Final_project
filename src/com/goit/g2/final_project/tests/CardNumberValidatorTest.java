package com.goit.g2.final_project.tests;

import com.goit.g2.final_project.CardNumberValidator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public class CardNumberValidatorTest {

        private CardNumberValidator cardNumber;

        @Rule
        public final ExpectedException exception = ExpectedException.none();

        @Before
        public void setUp() throws Exception {
        }

        @After
        public void tearDown() throws Exception {
                cardNumber = null;
        }

        @Test
        public void isInputValid() throws Exception {
                cardNumber = new CardNumberValidator("1254 3652 9874  2569");
                Assert.assertTrue(cardNumber.isInputValid());
                cardNumber = new CardNumberValidator("0000000000000000");
                Assert.assertTrue(cardNumber.isInputValid());
                cardNumber = new CardNumberValidator("XXXX XXXX XXXX  XXXX ");
                Assert.assertFalse(cardNumber.isInputValid());
                // Checks for exception
                exception.expect(InputMismatchException.class);
                cardNumber = new CardNumberValidator("");
                Assert.assertTrue(cardNumber.isInputValid());
        }

        @Test
        public void isNumberValid() throws Exception {
                cardNumber = new CardNumberValidator("1254365298742568");
                Assert.assertTrue(cardNumber.isNumberValid());
                cardNumber = new CardNumberValidator("1254365298742569");
                Assert.assertFalse(cardNumber.isNumberValid());
        }

}