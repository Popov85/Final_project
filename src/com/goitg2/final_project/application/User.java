package com.goitg2.final_project.application;

import java.util.InputMismatchException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class {@code User} represents a user by his ID being e-mail
 * @author Popov Andrii
 */

public class User extends TimerTask {

        /** Specifies for how long a user is blocked */
        private static final long BLOCKING_TIME = 6000; // 6 sec
        /** Specifies how many times a user must enter wrong card number before being blocked */
        public static final long QUANTITY_OF_ATTEMPTS = 3;

        private final BlockedUsersRepository repo;

        /** Unique user ID */
        private final String email;
        /**Specifies how many attempts in one sequence was made as yet*/
        private int failedAttempts = 0;
        private boolean isBlocked;
        /** Points out at the fact the user has already been banned during the app's runtime */
        private boolean wasBlocked;
        private Timer timer;

        public User(String email) throws InputMismatchException {
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

        /**
         * Increases the counter of unsuccessful attempts for this user
         * After a number of such failed attempts the user becomes blocked
         */
        public void increaseAttempts() {
                this.failedAttempts++;
                if (this.failedAttempts >= QUANTITY_OF_ATTEMPTS) {
                        blockUser();
                }
        }

        private void blockUser() {
                if (wasBlocked) {
                        foreverBlockUser();
                        return;
                }
                this.isBlocked = true;
                this.wasBlocked = true; // Says that this user was already banned during the app's runtime at least once
                repo.addTempBlockedUser(this);
                TimerTask task = this;
                timer = new Timer();
                timer.schedule(task, BLOCKING_TIME);
        }

        private void foreverBlockUser() {
                repo.addForeverBlockedUser(this);
        }

        private void unBlockUser() {
                this.isBlocked = false;
                repo.removeTempBlockedUser(this);
                System.out.println("Success! User with email:" + this.email+" was unblocked!");
                System.out.println(repo.toString());
        }

        public void clearAttempts() {
                this.failedAttempts = 0;
        }

        /**
         * Unblocks this user after penalty time elapses
         */
        @Override
        public void run() {
                unBlockUser();
                timer.cancel();
                timer.purge();
        }
}
