package model

import kotlinx.serialization.Serializable

/**
 * Represents a user in the system with a username, password, and wardrobe.
 *
 * The password is kept private for security reasons. The wardrobe is initially empty
 * and can be manipulated using various wardrobe-specific methods.
 *
 * @property username The username of the user.
 * @property password The user's password, kept private.
 * @property wardrobe The user's wardrobe, containing their clothing items.
 */
@Serializable
class User(
    var username: String,
    private val password: String,
    private var wardrobe: Wardrobe = Wardrobe()
) {
    /**
     * Checks if the provided password matches the user's password.
     *
     * @param inputPassword The password to check against the user's password.
     * @return True if the provided password matches the user's password, false otherwise.
     */
    fun checkPassword(inputPassword: String) = inputPassword == password

    /**
     * Retrieves the user's wardrobe.
     *
     * @return The wardrobe of the user.
     */
    fun getWardrobe() = wardrobe

    /**
     * Returns a string representation of the User object.
     *
     * The format includes the username and password, followed by the wardrobe details.
     * Note: This method exposes the password and should be used with caution.
     *
     * @return A string representation of the User object.
     */
    override fun toString() = "User: $username\nWardrobe: $wardrobe"

}
