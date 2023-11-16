package view

import mu.KotlinLogging
import controller.UserAPI
import controller.WardrobeAPI
import model.ClothingType
import model.User
import utils.ScannerInput

class ConsoleView(
    private val userAPI: UserAPI,
    private val wardrobeAPI: WardrobeAPI
) {
    private val logger = KotlinLogging.logger {}
    fun startApplication() {
        var choice: Int
        do {
            choice = displayMainMenu()
            when (choice) {
                1 -> adminMenu()
                2 -> userLogin()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    private fun displayMainMenu(): Int {
        return ScannerInput.readNextInt(
            """
            1 -> Admin
            2 -> Smart Wardrobe
            0 -> Exit
            Enter option: 
        """.trimIndent()
        )
    }

    private fun manageUsers(): Int {
        return ScannerInput.readNextInt(
            """
            User Management:
            1 -> Create User
            2 -> Delete User
            0 -> Back to Main Menu
            Enter option: 
        """.trimIndent()
        )
    }

    private fun adminMenu() {
        var choice: Int
        do {
            choice = manageUsers()
            when (choice) {
                1 -> addUser()
                2 -> deleteUser()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    private fun addUser() {
        val username = ScannerInput.readNextLine("Enter username: ")
        val password = ScannerInput.readNextLine("Enter password: ")
        val success = userAPI.createUser(username, password)
        if (success) {
            logger.info { "User created successfully" }
        } else {
            logger.info { "User already exists" }
        }
    }

    private fun deleteUser() {
        val username = ScannerInput.readNextLine("Enter username: ")
        val success = userAPI.deleteUser(username)
        if (success) {
            logger.info { "User deleted successfully" }
        } else {
            logger.info { "User does not exist" }
        }
    }

    // Wardrobe Menu

    private fun userLogin() {
        val username = ScannerInput.readNextLine("Enter username: ")
        val password = ScannerInput.readNextLine("Enter password: ")
        val user = userAPI.findUser(username)
        if (user != null && userAPI.authenticateUser(user, password)) {
            wardrobeAPI.setWardrobe(user.getWardrobe())
            wardrobeMenu()
        } else {
            logger.info { "Invalid username or password" }
        }
    }

    private fun displayWardrobe(): Int{
        return ScannerInput.readNextInt(
            """
            1 -> Today's Outfit
            2 -> View Wardrobe
            3 -> Manage Wardrobe
            0 -> Back to Main Menu
            Enter option: 
        """.trimIndent()
        )
    }

    private fun wardrobeMenu() {
        var choice: Int
        do {
            choice = displayWardrobe()
            when (choice) {
                1 -> todaysOutfit()
                2 -> viewWardrobe()
                3 -> manageWardrobe()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    private fun displayManageWardrobe(): Int {
        return ScannerInput.readNextInt(
            """
            Wardrobe Management:
            1. Add Clothing
            2. Update Clothing
            3. Remove Clothing
            4. Back to Wardrobe Menu
            Enter option: 
        """.trimIndent()
        )
    }

    private fun manageWardrobe() {
        var choice: Int
        do {
            choice = displayManageWardrobe()
            when (choice) {
                1 -> addClothing()
                2 -> updateClothing()
                3 -> removeClothing()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    private fun addClothing() {
        val clothingData = mutableMapOf<String, String>()
        clothingData["id"] = ScannerInput.readNextLine("Enter clothing id: ")
        clothingData["type"] = ScannerInput.readNextLine("Enter clothing type: ")
        clothingData["color"] = ScannerInput.readNextLine("Enter clothing color: ")
        clothingData["texture"] = ScannerInput.readNextLine("Enter clothing texture: ")
        val success = wardrobeAPI.addClothingToWardrobe(clothingData)
        if (success) {
            logger.info { "Clothing added successfully" }
        } else {
            logger.info { "Clothing already exists" }
        }
    }

    private fun updateClothing() {
        val clothingData = mutableMapOf<String, String>()
        val id = ScannerInput.readNextInt("Enter clothing id: ")
        clothingData["color"] = ScannerInput.readNextLine("Enter clothing color: ")
        clothingData["texture"] = ScannerInput.readNextLine("Enter clothing texture: ")
        val success = wardrobeAPI.updateClothingInWardrobe(id, clothingData)
        if (success) {
            logger.info { "Clothing updated successfully" }
        } else {
            logger.info { "Clothing does not exist" }
        }
    }

    private fun removeClothing() {
        val id = ScannerInput.readNextInt("Enter clothing id: ")
        val success = wardrobeAPI.deleteClothingFromWardrobe(id)
        if (success) {
            logger.info { "Clothing deleted successfully" }
        } else {
            logger.info { "Clothing does not exist" }
        }
    }

    private fun viewWardrobe() {
        val userInput = ScannerInput.readNextInt(
            """
            Wardrobe View:
            1. View All Clothing
            2. View Clothing by Type
            3. View Clothing by Type and Color
            3. Back to Wardrobe Menu
            Enter option: 
        """.trimIndent()
        )
    }
    private fun todaysOutfit() {
    }
}
