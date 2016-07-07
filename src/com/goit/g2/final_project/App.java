package com.goit.g2.final_project;

/**
 * Created by Андрей on 05.07.2016.
 */
public class App {
        public static void main(String[] args) {

                CardNumberValidator validator = new CardNumberValidator("4485 9821 1491 2228");
                System.out.println(validator.isNumberValidCustom());
                //Integer.parseInt("Z");

                /*Card card;
                try {
                        card = new Card("");
                        System.out.println(card.getNumber().getCardNumber());
                } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                } catch (InvalidAlgorithmParameterException e) {
                        System.out.println(e.getMessage());
                } finally {
                        System.out.println("Finished");
                }*/
        }
}
