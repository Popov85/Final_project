package com.goitg2.final_project.application;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton realization of repository with blocked users (those
 * who entered a wrong credit card number several times)
 */
public final class BlockedUsersRepository {

        private static BlockedUsersRepository repository = new BlockedUsersRepository();

        private final Map<String, User> blockedUsers = new HashMap<String, User>();

        private BlockedUsersRepository() {}

        /* Static 'instance' method */
        public static BlockedUsersRepository getInstance( ) {
                return repository;
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
