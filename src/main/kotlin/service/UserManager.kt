package service

import controller.UserAPI
import model.User

/**
 * Manages user-related operations by interfacing with the [UserAPI].
 * This class provides functionalities for user creation, deletion, authentication, and updates.
 *
 * @property userAPI An instance of [UserAPI] used to perform operations on user data.
 */
class UserManager(private val userAPI: UserAPI) {

    /**
     * Creates a new user with the specified username and password.
     *
     * @param username The username for the new user. Cannot be blank.
     * @param password The password for the new user. Cannot be blank.
     * @return `true` if the user was successfully created; `false` otherwise.
     */
    fun createUser(username: String, password: String): Boolean =
        if (username.isBlank() || password.isBlank()) {
            false
        } else userAPI.createUser(username, password)

    /**
     * Deletes a user with the specified username.
     *
     * @param username The username of the user to delete. Cannot be blank.
     * @return `true` if the user was successfully deleted; `false` otherwise.
     */
    fun deleteUser(username: String): Boolean =
        if (username.isBlank()) {
            false
        } else userAPI.deleteUser(username)

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user. Cannot be blank.
     * @param password The password of the user. Cannot be blank.
     * @return The [User] object if authentication is successful; `null` otherwise.
     */
    fun userLogin(username: String, password: String): User? {
        if (username.isBlank() || password.isBlank()) {
            return null
        }
        return userAPI.findUser(username)?.takeIf { userAPI.authenticateUser(it, password) }
    }

    /**
     * Updates the details of an existing user.
     *
     * @param user The [User] object containing updated user information.
     * @return `true` if the user was successfully updated; `false` otherwise.
     */
    fun saveUser(user: User): Boolean = userAPI.updateUser(user)

}