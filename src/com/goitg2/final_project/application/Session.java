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

        private String email;
        private String card;

        public Session(String email, String card) {
                this.email = email;
                this.card = card;
        }

        public String startSession() throws IllegalArgumentException, InputMismatchException {
                if (!checkEmail(email)) {
                        throw new IllegalArgumentException("Wrong email pattern!");
                }
                User user = null;
                if (hasUser(email)) {
                        user = getSessionUser(email);
                } else {
                        try {
                                user = new User(email);
                                addSessionUser(user);
                        } catch (InputMismatchException e) {
                                e.getMessage();
                        }
                }
                PreAuthentication authentication = new PreAuthentication(user, card);
                //authentication.start();
                return authentication.preAuthenticate();
        }

        private boolean checkEmail(String email) throws InputMismatchException {
                if (email.isEmpty()||email == null)
                        throw new InputMismatchException("Empty email!");
                return email.indexOf('@')==-1 ? false:true;
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
