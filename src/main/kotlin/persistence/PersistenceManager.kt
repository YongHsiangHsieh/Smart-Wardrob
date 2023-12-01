package persistence

import model.User
import utils.JsonUtil
import java.io.File

/**
 * Manages the persistence of user data. This object handles saving, loading, and deleting user data using JSON files.
 */
object PersistenceManager {

    /**
     * Path to the directory where user data is stored.
     */
    var USER_DATA_PATH = "data${File.separator}users${File.separator}"
    private const val JSON_EXTENSION = ".json"
    private const val YAML_EXTENSION = ".yaml"


    init {
        File(USER_DATA_PATH).mkdirs()
    }

    /**
     * Saves user data to a JSON file.
     *
     * Serializes the given [User] object into a JSON string and writes it to a file corresponding to the user's username.
     *
     * @param user The user whose data is to be saved.
     * @return True if the data is successfully saved, false otherwise.
     */
    fun saveUserData(user: User): Boolean {
        return try {
            val userData = JsonUtil.serializeToJson(user)
            File(USER_DATA_PATH + user.username + JSON_EXTENSION).writeText(userData)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Loads user data from a JSON file.
     *
     * Reads the file corresponding to the given username, deserializes it into a [User] object, and returns it.
     *
     * @param username The username of the user whose data is to be loaded.
     * @return The [User] object if found, null otherwise.
     */
    fun loadUserData(username: String): User? {
        val userFile = File(USER_DATA_PATH + username + JSON_EXTENSION)
        return if (userFile.exists()) JsonUtil.deserializeFromJson<User>(userFile.readText()) else null
    }

    /**
     * Deletes user data from a JSON file.
     *
     * Deletes the file corresponding to the given username.
     *
     * @param username The username of the user whose data is to be deleted.
     * @return True if the data is successfully deleted, false otherwise.
     */
    fun deleteUserData(username: String): Boolean = File(USER_DATA_PATH + username + JSON_EXTENSION).delete()
}
