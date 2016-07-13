package com.goit.g2.final_project.application;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class {@code Authentification} represents a user by his ID being e-mail
 * @author Popov Andrii
 */

public class User extends TimerTask {

        /** Specifies for how long a user is blocked */
        private static final long BLOCKING_TIME = 60000; // 60 sec
        /** Specifies how many times a user must enter wrong card number before being blocked */
        private static final long QUANTITY_OF_ATTEMPTS = 3;

        private final BlockedUsersRepository repo;

        /** Unique user ID */
        private final String email;
        // Specifies how many attempts in one sequence was made to enter a correct card number
        private int failedAttempts = 0;
        private boolean isBlocked;
        private Date timeWhenBlocked;

        public User(String email) throws InputMismatchException {
                if (email.isEmpty()) throw new InputMismatchException("Invalid email!");
                this.email = email;
                this.repo = BlockedUsersRepository.getInstance();
        }

        public String getEmail() {
                return email;
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

        /**
         * Increases the counter of unsuccessful attempts for this user
         * After a number of such failed attempts the user becomes blocked
         */
        public void increaseAttempts() {
                if (this.failedAttempts < QUANTITY_OF_ATTEMPTS-1) {
                        this.failedAttempts++;
                }
                else {
                        blockUser();
                }
        }

        private void blockUser() {
                this.isBlocked = true;
                repo.addBlockedUser(this);
                TimerTask task = this;
                Timer timer = new Timer();
                timer.schedule(task, BLOCKING_TIME);
        }

        private void unBlockUser() {
                this.isBlocked = false;
                repo.removeBlockedUser(this);
                System.out.println("Success! User with email:" + this.email+" was unblocked!");
                System.out.println(repo.toString());
        }

        public void clearAttempts() {
                this.failedAttempts = 0;
        }

        @Override
        public void run() {
                unBlockUser();
        }
}
