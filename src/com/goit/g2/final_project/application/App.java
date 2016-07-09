package com.goit.g2.final_project.application;

/**
 * Created by Андрей on 05.07.2016.
 */
public class App {
        public static void main(String[] args) {
                String str = "0123456789";
                char[] charArr = str.toCharArray();
                for (int k=0; k<charArr.length;k++) {
                        System.out.println((int) charArr[k]);
                }
                System.out.println("-----------------------------");
                System.out.println((int) charArr[0]);
                System.out.println((char) 49);


                //AbstractValidator validator = new CardNumberValidatorApache("ergreygtrytytry");
                //System.out.println(validator.isNumberValid());
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
