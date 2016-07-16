package com.goitg2.final_project.application;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Singleton realization of REPOSITORY with temporarily blocked users (those
 * who entered a wrong credit card number several times) as well as forever blocked users.
 * @author Andrii Popov
 */
public enum BlockedUsersRepository {

        REPOSITORY;

        /** Only temporarily blocked users */
        private final ConcurrentMap<String, User> tempBlockedUsers = new ConcurrentHashMap<String, User>();
        /** Forever blocked users, who kept getting blocked several times.
         * They shall be unblocked on request, only manually */
        private final ConcurrentMap<String, User> foreverBlockedUsers = new ConcurrentHashMap<String, User>();

        /* Static 'instance' method */
        public static BlockedUsersRepository getInstance( ) {
                return REPOSITORY;
        }

        public void addTempBlockedUser(User user) {
                tempBlockedUsers.put(user.getEmail(),user);
        }

        public void removeTempBlockedUser(User user) {
                tempBlockedUsers.remove(user.getEmail());
        }

        public void addForeverBlockedUser(User user) {
                foreverBlockedUsers.put(user.getEmail(),user);
        }

        public void removeForeverBlockedUser(User user) {
                foreverBlockedUsers.remove(user.getEmail());
        }

        public boolean hasTempBlockedUser(User user) {
               if (tempBlockedUsers.get(user.getEmail())!=null) {
                       return true;
               }else {
                       return false;
               }
        }

        public boolean hasForeverBlockedUser(User user) {
                if (foreverBlockedUsers.get(user.getEmail()) != null) {
                        return true;
                } else {
                        return false;
                }
        }

        @Override
        public String toString() {
                String blockedOnes = "Temporarily blocked users: ";
                if (!tempBlockedUsers.isEmpty()) {
                        for (String key : tempBlockedUsers.keySet()) {
                                blockedOnes+="\n"+key;
                        }
                } else {
                        blockedOnes +="none\n";
                }
                blockedOnes += "\nForever blocked users: ";
                if (!foreverBlockedUsers.isEmpty()) {
                        for (String key : foreverBlockedUsers.keySet()) {
                                blockedOnes+="\n"+key;
                        }
                } else {
                        blockedOnes +="none\n";
                }
                return blockedOnes;
        }
}
