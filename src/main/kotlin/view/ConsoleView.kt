package view

import mu.KotlinLogging
import controller.UserAPI
import controller.WardrobeAPI
import model.ClothingType
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
//                2 -> smartWardrobeMenu()
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


    fun displayWardrobe(): Int{
        return ScannerInput.readNextInt(
            """
            1. Today's Outfit
            2. View Wardrobe
            3. Manage Wardrobe
            4. Back to Main Menu
            Enter option: 
        """.trimIndent()
        )
    }
    fun manageWardrobe() {
        val userInput = ScannerInput.readNextInt(
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

    fun viewWardrobe() {
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
}
