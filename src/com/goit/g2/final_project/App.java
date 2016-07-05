package com.goit.g2.final_project;

import java.security.InvalidAlgorithmParameterException;
import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public class App {
        public static void main(String[] args) {
                Card card;
                try {
                        card = new Card("848848");
                        System.out.println(card.getNumber().getCardNumber());
                } catch (InputMismatchException e) {
                        System.out.println("Invalid card number! Empty entry.");
                } catch (InvalidAlgorithmParameterException e) {
                        System.out.println("Invalid card number!");;
                } finally {
                        System.out.println("Finished");
                }
        }
}
