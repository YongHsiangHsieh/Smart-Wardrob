package controller

import model.User
import model.Wardrobe
import persistence.PersistenceManager

class UserController(private val persistenceManager: PersistenceManager) {

    private val users: MutableMap<String, User> = mutableMapOf()

    fun createUser(username: String, password: String): Boolean {
        if (users.containsKey(username)) {
            return false
        }
        val newUser = User(username, password)
        users[username] = newUser
        persistenceManager.saveUserData(newUser)
        return true
    }

    fun deleteUser(username: String): Boolean {
        val removedUser = users.remove(username)
        if (removedUser != null) {
            persistenceManager.deleteUserData(username)
            return true
        }
        return false
    }

    fun findUser(username: String): User? {
        return users[username] ?: persistenceManager.loadUserData(username)
    }

//    fun authenticateUser(username: String, password: String): Boolean {
//        return users[username]?.checkPassword(password) ?: false
//    }
//
//    fun updateUserWardrobe(username: String, wardrobe: Wardrobe) {
//        users[username]?.let {
//            it.wardrobe = wardrobe
//            persistenceManager.saveUserData(it)
//        }
//    }
}
