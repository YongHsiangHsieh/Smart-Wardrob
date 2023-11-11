package view

import controller.UserAPI
import controller.WardrobeAPI
import model.ClothingType
import utils.ScannerInput

class ConsoleView(
    private val userAPI: UserAPI,
    private val wardrobeAPI: WardrobeAPI
) {
    fun displayMainMenu() {
        val userInput = ScannerInput.readNextInt(
            """
            Welcome to the Smart Wardrobe Console App
            1. Manage Users
            2. Manage Wardrobe
            3. Suggest Outfit
            4. Exit
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
            3. Login
            4. Back to Main Menu
            Enter option: 
        """.trimIndent()
        )
        when (userInput) {
            1 -> promptUserDetails()
            2 -> promptUsernameForDeletion()
            3 -> promptLogin()
        }
    }

    private fun promptUserDetails() {
        // Prompt for user details and pass them to the UserController
    }

    private fun promptUsernameForDeletion() {
        // Prompt for username and pass it to the UserController
    }

    private fun promptLogin() {
        // Prompt for login details and authenticate through the UserController
    }

    fun manageWardrobe() {
        // Display wardrobe management options
        val userInput = ScannerInput.readNextInt(
            """
            Wardrobe Management:
            1. Add Clothing
            2. Update Clothing
            3. Remove Clothing
            4. View Clothing by Type
            5. Search Clothing by Color and Type
            6. Back to Main Menu
            Enter option: 
        """.trimIndent()
        )
        when (userInput) {
            1 -> promptAddClothing()
            // ... Other cases
        }
    }
}

private fun promptAddClothing() {
    // Prompt for clothing details and pass them to the WardrobeController
}

fun suggestOutfit() {
    // Interface with the OutfitSuggester to provide outfit suggestions
    println("Today's outfit suggestion is:")
    // ...
}

fun displayClothing(clothingList: List<ClothingType>) {
    // Display a list of clothing items to the user
}

fun displayMessage(message: String) {
    println(message)
}

// ... Other methods for different UI interactions

