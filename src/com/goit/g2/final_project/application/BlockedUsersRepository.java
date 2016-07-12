package com.goit.g2.final_project.application;

import java.util.HashMap;

/**
 * Singleton realization
 */
public final class BlockedUsersRepository {

        private static BlockedUsersRepository repository = new BlockedUsersRepository();

        private final HashMap<String, User> blockedUsers = new HashMap<String, User>();

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

}
