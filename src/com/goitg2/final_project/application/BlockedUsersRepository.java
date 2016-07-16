package com.goitg2.final_project.application;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Singleton realization of REPOSITORY with blocked users (those
 * who entered a wrong credit card number several times)
 * @author Andrii Popov
 */
public enum BlockedUsersRepository {

        REPOSITORY;

        private final ConcurrentMap<String, User> blockedUsers = new ConcurrentHashMap<String, User>();

        /* Static 'instance' method */
        public static BlockedUsersRepository getInstance( ) {
                return REPOSITORY;
        }

        public void addBlockedUser(User user) {
                blockedUsers.put(user.getEmail(),user);
        }

        public void removeBlockedUser(User user) {
                blockedUsers.remove(user.getEmail());
        }

        public boolean hasBlockedUser(User user) {
               if (blockedUsers.get(user.getEmail())!=null) {
                       return true;
               }else {
                       return false;
               }
        }

        @Override
        public String toString() {
                String blockedOnes = "Blocked users: \n";
                for (String key : blockedUsers.keySet()) {
                        blockedOnes+=key+"\n";
                }
                return blockedOnes;
        }
}
