package controller

import model.User
import model.Wardrobe
import persistence.PersistenceManager

class UserAPI(private val persistenceManager: PersistenceManager) {

    fun createUser(username: String, password: String): Boolean {
        if (persistenceManager.loadUserData(username) != null) {
            return false
        }
        val newUser = User(username, password)
        persistenceManager.saveUserData(newUser)
        return true
    }

    fun deleteUser(username: String): Boolean {
        return if (persistenceManager.loadUserData(username) != null) {
            persistenceManager.deleteUserData(username)
            true
        } else {
            false
        }
    }

    fun findUser(username: String): User? {
        return persistenceManager.loadUserData(username)
    }

    fun authenticateUser(user: User, password: String): Boolean {
        return user.checkPassword(password)
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
