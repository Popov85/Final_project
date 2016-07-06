package com.goit.g2.final_project;

import java.security.InvalidAlgorithmParameterException;
import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public class App {
        public static void main(String[] args) {

                //CardNumberValidator validator = new CardNumberValidator("4587 x578 9654  784d");
                //System.out.println(validator.isNumberValid());
                //Integer.parseInt("Z");

                Card card;
                try {
                        card = new Card("4539 2301 5686 1986");
                        System.out.println(card.getNumber().getCardNumber());
                } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                } catch (InvalidAlgorithmParameterException e) {
                        System.out.println("Invalid card number!");;
                } finally {
                        System.out.println("Finished");
                }
        }
}
