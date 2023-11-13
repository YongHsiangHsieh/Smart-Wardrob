package persistence

import model.User
import model.Wardrobe
import utils.JsonUtils
import java.io.File

object PersistenceManager {
    private const val USER_DATA_PATH = "data/users/"
//    private const val WARDROBE_DATA_PATH = "data/wardrobes/"

    init {
        File(USER_DATA_PATH).mkdirs()
//        File(WARDROBE_DATA_PATH).mkdirs()
    }

    fun saveUserData(user: User) {
        val userData = JsonUtils.serializeToJson(user)
        File("$USER_DATA_PATH${user.username}.json").writeText(userData)
    }

    fun loadUserData(username: String): User? {
        val userFile = File("$USER_DATA_PATH$username.json")
        if (userFile.exists()) {
            return JsonUtils.deserializeFromJson(userFile.readText())
        }
        return null
    }
    fun deleteUserData(username: String) {
        val userFile = File("$USER_DATA_PATH$username.json")
        if (userFile.exists()) {
            userFile.delete()
        }
//        val wardrobeFile = File("$WARDROBE_DATA_PATH${username}_wardrobe.json")
//        if (wardrobeFile.exists()) {
//            wardrobeFile.delete()
//        }
    }

//    fun saveWardrobeData(username: String, wardrobe: Wardrobe) {
//        val wardrobeData = JsonUtils.serializeToJson(wardrobe)
//        File("$WARDROBE_DATA_PATH${username}_wardrobe.json").writeText(wardrobeData)
//    }
//
//    fun loadWardrobeData(username: String): Wardrobe? {
//        val wardrobeFile = File("$WARDROBE_DATA_PATH${username}_wardrobe.json")
//        if (wardrobeFile.exists()) {
//            return JsonUtils.deserializeFromJson(wardrobeFile.readText())
//        }
//        return null
//    }

}
