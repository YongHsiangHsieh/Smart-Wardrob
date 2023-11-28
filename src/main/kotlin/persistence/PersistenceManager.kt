package persistence

import model.User
import utils.JsonUtil
import java.io.File

object PersistenceManager {
    var USER_DATA_PATH = "data/users/"

    init {
        File(USER_DATA_PATH).mkdirs()
    }

    fun saveUserData(user: User) {
        val userData = JsonUtil.serializeToJson(user)
        File("$USER_DATA_PATH${user.username}.json").writeText(userData)
    }

    fun loadUserData(username: String): User? {
        val userFile = File("$USER_DATA_PATH$username.json")
        if (userFile.exists()) {
            return JsonUtil.deserializeFromJson(userFile.readText())
        }
        return null
    }
    fun deleteUserData(username: String) {
        val userFile = File("$USER_DATA_PATH$username.json")
        if (userFile.exists()) {
            userFile.delete()
        }
    }
}
