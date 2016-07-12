package com.goit.g2.final_project.application;

import java.util.Date;

/**
 * Created by Андрей on 12.07.2016.
 */
public class User {

        /** Specifies for how long a user is blocked */
        private static final long BLOCKING_TIME = 100000;
        /** Specifies how many times a user must enter wrong card number before being blocked */
        private static final long QUANTITY_OF_ATTEMPTS = 3;

        /** Unique user ID */
        private String email;
        private String cardNumber;
        // Specifies how many attempts in one sequence was made to enter a correct card number
        private int failedAttempts = 0;
        private boolean isBlocked;
        private Date timeWhenBlocked;

        public User(String email) {
                this.email = email;
        }


        public String getEmail() {
                return email;
        }

        public String getCardNumber() {
                return cardNumber;
        }

        public int getFailedAttempts() {
                return failedAttempts;
        }

        public boolean isBlocked() {
                return isBlocked;
        }

        public Date getTimeWhenBlocked() {
                return timeWhenBlocked;
        }

        public void increaseAttempts(BlockedUsersRepository repo) {
                if (this.failedAttempts < QUANTITY_OF_ATTEMPTS-1) {
                        this.failedAttempts++;
                }
                else {
                        this.isBlocked = true;
                        repo.addBlockedUser(this);
                }
        }

        public void clearAttempts() {
                this.failedAttempts = 0;
        }

}
