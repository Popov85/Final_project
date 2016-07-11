package com.goit.g2.final_project.application;

/**
 * Created by Андрей on 05.07.2016.
 */
public class App {
        public static void main(String[] args) {

                //List list =
                Validator validator = new CardNumberValidatorApache("ergreygtrytytry");
                System.out.println(validator.isNumberValid());
                //Integer.parseInt("Z");

                /*Card card;
                try {
                        card = new Card();
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
