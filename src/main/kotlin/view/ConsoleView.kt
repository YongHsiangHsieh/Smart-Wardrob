package view

import controller.UserAPI
import controller.WardrobeAPI
import model.ClothingType
import utils.ScannerInput

class ConsoleView(
    private val userAPI: UserAPI,
    private val wardrobeAPI: WardrobeAPI
) {
//    fun loginUser(): Boolean {
//        val username = ScannerInput.readNextLine("Enter username: ")
//        val user = userAPI.findUser(username)
//        if (user == null) {
//            println("User not found!")
//            return false
//        }
//        val password = ScannerInput.readNextLine("Enter password: ")
//        // Check password
//        return true
//    }

    fun displayMainMenu(): Int {
        return ScannerInput.readNextInt(
            """
            1. Admin
            2. Smart Wardrobe
            3. Exit
            Enter option: 
        """.trimIndent()
        )
    }

    fun manageUsers() {
        val userInput = ScannerInput.readNextInt(
            """
            User Management:
            1. Create User
            2. Delete User
            3. Update User
            4. Back to Main Menu
            Enter option: 
        """.trimIndent()
        )
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
