package com.goitg2.final_project.application;

import java.util.InputMismatchException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Emulation of real app when users enter wrong credit cards and keep getting blocked
 * after a number of unsuccessful attempts
 * @author Andrii Popov
 */
public class Session {

        /** Repository of this session users */
        private static final ConcurrentMap<String, User> SESSION_USERS = new ConcurrentHashMap<String, User>();

        public void startSession() throws InterruptedException {
                for (int i = 1; i <= 50; i++) {
                        anotherUser("user"+i+"@gmail.com");
                }
                Thread.sleep(3000);
                // Users 1-50 should be declined immediately since they are already temp. blocked
                System.out.println("--------------------3 sec elapsed-------------------");
                for (int i = 1; i <= 100; i++) {
                        anotherUser("user"+i+"@gmail.com");
                }
                Thread.sleep(10000);
                // Users 1-5 should be constantly blocked
                System.out.println("--------------------13 sec elapsed-------------------");
                for (int i = 1; i <= 5; i++) {
                        anotherUser("user"+i+"@gmail.com");
                }
        }

        private void anotherUser(String email) {
                User user = null;
                if (hasUser(email)) {
                        user = getSessionUser(email);
                } else {
                        try {
                                user = new User(email);
                                addSessionUser(user);
                        } catch (InputMismatchException e) {
                                System.out.println("Invalid e-mail...");
                                return;
                        }
                }
                PreAuthentication authentication = new PreAuthentication(user);
                try {
                        authentication.start();
                } catch (InputMismatchException e) {
                        System.out.println("Invalid card number...");
                        return;
                }
        }

        private boolean hasUser(String email) {
                if (SESSION_USERS.get(email)!=null) {
                        return true;
                }else {
                        return false;
                }
        }

        private void addSessionUser(User user) {
                SESSION_USERS.put(user.getEmail(),user);
        }

        private User getSessionUser(String email) {
                return SESSION_USERS.get(email);
        }
}
