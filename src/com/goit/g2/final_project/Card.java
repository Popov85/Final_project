package com.goit.g2.final_project;

import java.security.InvalidAlgorithmParameterException;
import java.util.Calendar;
import java.util.InputMismatchException;

/**
 * Created by Андрей on 05.07.2016.
 */
public final class Card {

        private CardNumber number;

        private boolean isOccupied;

        private Object cardHolder;

        private Calendar validDate;

        private String cvvCode;

        public Card(String sequence) throws InputMismatchException, InvalidAlgorithmParameterException {
                this.number = new CardNumber(sequence);
        }

        public CardNumber getNumber() {
                return this.number;
        }

        public void setCardHolder(Object cardHolder) {
                this.cardHolder = cardHolder;
                this.isOccupied = true;
        }
}
