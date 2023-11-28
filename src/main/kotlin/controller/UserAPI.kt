package controller

import model.User
import persistence.PersistenceManager

/**
 * API for managing user data. This class provides functionalities to create, delete, find, authenticate, and update users.
 *
 * @property persistenceManager The persistence manager responsible for handling user data storage and retrieval.
 */
class UserAPI(private val persistenceManager: PersistenceManager) {

    /**
     * Creates a new user with the specified username and password.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @return True if the user is successfully created, false if a user with the same username already exists.
     */
    fun createUser(username: String, password: String): Boolean {
        if (persistenceManager.loadUserData(username) != null) {
            return false
        }
        val newUser = User(username, password)
        persistenceManager.saveUserData(newUser)
        return true
    }

    /**
     * Deletes a user with the specified username.
     *
     * @param username The username of the user to be deleted.
     * @return True if the user is successfully deleted, false if the user does not exist.
     */
    fun deleteUser(username: String): Boolean {
        return if (persistenceManager.loadUserData(username) != null) {
            persistenceManager.deleteUserData(username)
            true
        } else {
            false
        }
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The user if found, null otherwise.
     */
    fun findUser(username: String): User? {
        return persistenceManager.loadUserData(username)
    }

    /**
     * Authenticates a user based on their password.
     *
     * @param user The user to authenticate.
     * @param password The password to verify.
     * @return True if the password is correct, false otherwise.
     */
    fun authenticateUser(user: User, password: String): Boolean {
        return user.checkPassword(password)
    }

    /**
     * Updates the data of an existing user.
     *
     * @param user The user with updated information.
     */
    fun updateUser(user: User) {
        persistenceManager.saveUserData(user)
    }
}
