package com.goit.g2.final_project.tests;

import com.goit.g2.final_project.CardNumber;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Андрей on 05.07.2016.
 */
public class CardNumberTest {

        private static final String NUMBER = "4136 9663 9865 9874";

        private CardNumber cardNumber;

        @Rule
        public final ExpectedException exception = ExpectedException.none();

        @Before
        public void setUp() throws Exception {
                cardNumber = new CardNumber(NUMBER);
        }

        @After
        public void tearDown() throws Exception {
                cardNumber = null;
        }

        @Test
        public void isInputValid() throws Exception {
                //Assert.assertTrue(cardNumber.isInputValid("1254 3652 9874  2569"));
                //Assert.assertTrue(cardNumber.isInputValid("0000000000000000"));
                //Assert.assertFalse(cardNumber.isInputValid("XXXX XXXX XXXX XXXX"));
                //exception.expect(InputMismatchException.class);
                //Assert.assertTrue(cardNumber.isInputValid(""));
        }

        @Test
        public void isNumberValid() throws Exception {

        }

}