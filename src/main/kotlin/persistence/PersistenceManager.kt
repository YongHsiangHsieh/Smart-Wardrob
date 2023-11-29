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
    private const val EXTENSION = ".json"

    init {
        File(USER_DATA_PATH).mkdirs()
    }

    /**
     * Saves user data to a JSON file.
     *
     * Serializes the [User] object into JSON and writes it to a file named after the user's username.
     *
     * @param user The [User] object to be saved.
     */
    fun saveUserData(user: User) {
        val userData = JsonUtil.serializeToJson(user)
        File(USER_DATA_PATH + user.username + EXTENSION).writeText(userData)
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
        val userFile = File("$USER_DATA_PATH$username.json")
        if (userFile.exists()) {
            return JsonUtil.deserializeFromJson(userFile.readText())
        }
        return null
    }

    /**
     * Deletes a user's data file.
     *
     * Removes the file corresponding to the given username if it exists.
     *
     * @param username The username of the user whose data file is to be deleted.
     */
    fun deleteUserData(username: String) {
        val userFile = File("$USER_DATA_PATH$username.json")
        if (userFile.exists()) {
            userFile.delete()
        }
    }
}
