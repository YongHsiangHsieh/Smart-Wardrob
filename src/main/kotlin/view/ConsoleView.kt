package view

import mu.KotlinLogging
import controller.UserAPI
import controller.WardrobeAPI
import model.ClothingType
import model.User
import service.OutfitSuggester
import utils.ScannerInput

/**
 * Console-based view for the application, providing a user interface for interacting with the system.
 * Handles user and wardrobe management, including login, user creation/deletion, and wardrobe operations.
 *
 * @property userAPI An instance of [UserAPI] for handling user-related operations.
 * @property wardrobeAPI An instance of [WardrobeAPI] for handling wardrobe-related operations.
 */
class ConsoleView(
    private val userAPI: UserAPI,
    private val wardrobeAPI: WardrobeAPI
) {
    private val logger = KotlinLogging.logger {}
    private var currentUser: User? = null

    /**
     * Starts the application, displaying the main menu and handling user input.
     */
    fun startApplication() {
        var choice: Int
        do {
            choice = displayMainMenu()
            when (choice) {
                1 -> adminMenu()
                2 -> userLogin()
                0 -> exitApplication()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    /**
     * Displays the main menu and reads the user's choice.
     *
     * @return The user's menu choice as an integer.
     */
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

    /**
     * Displays the user management menu and reads the user's choice.
     *
     * @return The user's choice for user management operations as an integer.
     */
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

    /**
     * Handles the admin menu, providing options for user management.
     */
    private fun adminMenu() {
        var choice: Int
        do {
            choice = manageUsers()
            when (choice) {
                1 -> addUser()
                2 -> deleteUser()
                0 -> exitApplication()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    /**
     * Handles the process of adding a new user.
     */
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

    /**
     * Handles the process of deleting an existing user.
     */
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

    /**
     * Handles user login, setting the current user and transitioning to the wardrobe menu.
     */
    private fun userLogin() {
        println("Welcome to Smart Wardrobe! Please login to continue.")
        val username = ScannerInput.readNextLine("Enter username: ")
        val password = ScannerInput.readNextLine("Enter password: ")
        val user = userAPI.findUser(username)
        if (user != null && userAPI.authenticateUser(user, password)) {
            currentUser = user
            wardrobeAPI.setWardrobe(currentUser!!.getWardrobe())
            wardrobeMenu()
        } else {
            logger.info { "Invalid username or password" }
        }
    }

    /**
     * Displays the wardrobe menu and reads the user's choice.
     *
     * @return The user's choice for wardrobe operations as an integer.
     */
    private fun displayWardrobe(): Int {
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

    /**
     * Handles the wardrobe menu, providing options for outfit suggestions and wardrobe management.
     */
    private fun wardrobeMenu() {
        var choice: Int
        do {
            choice = displayWardrobe()
            when (choice) {
                1 -> todaysOutfit()
                2 -> viewWardrobe()
                3 -> manageWardrobe()
                0 -> exitApplication()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    /**
     * Suggests an outfit for the current day based on the current user's wardrobe.
     */
    private fun todaysOutfit() {
        val wardrobe = currentUser?.getWardrobe()
        if (wardrobe != null) {
            val outfit = OutfitSuggester.suggestOutfit(wardrobe, 5)
            if (outfit.isNotEmpty()) {
                outfit.forEach { println(it) }
            } else {
                logger.info { "No outfit suggestions for today" }
            }
        } else {
            logger.info { "No wardrobe found" }
        }
    }

    /**
     * Displays the wardrobe management menu and reads the user's choice.
     *
     * @return The user's choice for wardrobe management operations as an integer.
     */
    private fun displayManageWardrobe(): Int {
        return ScannerInput.readNextInt(
            """
            Wardrobe Management:
            1 -> Add Clothing
            2 -> Update Clothing
            3 -> Remove Clothing
            0 -> Back to Wardrobe Menu
            Enter option: 
        """.trimIndent()
        )
    }

    /**
     * Handles the process of managing the wardrobe, including adding, updating, and removing clothing.
     */
    private fun manageWardrobe() {
        var choice: Int
        do {
            choice = displayManageWardrobe()
            when (choice) {
                1 -> addClothing()
                2 -> updateClothing()
                3 -> removeClothing()
                0 -> exitApplication()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    /**
     * Handles the process of adding a new clothing item to the wardrobe.
     */
    private fun addClothing() {
        val clothingData = mutableMapOf<String, String>()
        clothingData["id"] = ScannerInput.readNextLine("Enter clothing id: ")
        clothingData["type"] = getClothingType().toString()
        clothingData["brand"] = ScannerInput.readNextLine("Enter clothing brand: ")
        clothingData["name"] = ScannerInput.readNextLine("Enter clothing name: ")
        clothingData["color"] = ScannerInput.readNextLine("Enter clothing color: ")
        clothingData["texture"] = ScannerInput.readNextLine("Enter clothing texture: ")
        val success = wardrobeAPI.addClothingToWardrobe(clothingData)
        if (success) {
            logger.info { "Clothing added successfully" }
        } else {
            logger.info { "Clothing already exists" }
        }
    }

    /**
     * Handles the process of updating an existing clothing item in the wardrobe.
     */
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

    /**
     * Handles the process of removing a clothing item from the wardrobe.
     */
    private fun removeClothing() {
        val id = ScannerInput.readNextInt("Enter clothing id: ")
        val success = wardrobeAPI.deleteClothingFromWardrobe(id)
        if (success) {
            logger.info { "Clothing deleted successfully" }
        } else {
            logger.info { "Clothing does not exist" }
        }
    }

    /**
     * Displays options for viewing the wardrobe and reads the user's choice.
     *
     * @return The user's choice for viewing wardrobe contents as an integer.
     */
    private fun displayViewWardrobe(): Int {
        return ScannerInput.readNextInt(
            """
            Wardrobe View:
            1 -> View All Clothing
            2 -> View Clothing by Type
            3 -> View Clothing by Type and Color
            0 -> Back to Wardrobe Menu
            Enter option: 
        """.trimIndent()
        )
    }

    /**
     * Handles the process of viewing the wardrobe contents.
     */
    private fun viewWardrobe() {
        var choice: Int
        do {
            choice = displayViewWardrobe()
            when (choice) {
                1 -> viewAllClothing()
                2 -> viewClothingByType()
                3 -> viewClothingByTypeAndColor()
                0 -> exitApplication()
                else -> logger.info { "Invalid option" }
            }
        } while (choice != 0)
    }

    /**
     * Displays all clothing items in the current user's wardrobe.
     */
    private fun viewAllClothing() {
        val clothing = wardrobeAPI.getAllClothing()
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            logger.info { "No clothing in wardrobe" }
        }
    }

    /**
     * Displays clothing items of a specific type in the current user's wardrobe.
     */
    private fun viewClothingByType() {
        val type = getClothingType()
        val clothing = wardrobeAPI.getClothingByType(type)
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            logger.info { "No clothing of type $type in wardrobe" }
        }
    }

    /**
     * Displays clothing items of a specific type and color in the current user's wardrobe.
     */
    private fun viewClothingByTypeAndColor() {
        val type = getClothingType()
        val color = ScannerInput.readNextLine("Enter clothing color: ")
        val clothing = wardrobeAPI.searchClothingByColorAndType(color, type)
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            logger.info { "No clothing of type $type and color $color in wardrobe" }
        }
    }

    /**
     * Gets the clothing type from the user.
     *
     * @return The chosen [ClothingType].
     */
    private fun getClothingType(): ClothingType {
        while (true) {
            val option = ScannerInput.readNextInt(
                """
            Clothing Types:
            1 -> JUMPER
            2 -> SHIRT
            3 -> SHORTS
            4 -> TRACKSUIT
            5 -> JACKET
            Enter option: 
        """.trimIndent()
            )
            return when (option) {
                1 -> ClothingType.JUMPER
                2 -> ClothingType.SHIRT
                3 -> ClothingType.SHORTS
                4 -> ClothingType.TRACKSUIT
                5 -> ClothingType.JACKET
                else -> {
                    logger.info { "Invalid option" }
                    continue
                }
            }
        }
    }

    /**
     * Handles the process of exiting the application, including saving any changes to the current user.
     */
    private fun exitApplication() {
        currentUser?.let {
            userAPI.updateUser(it)
            logger.info { "User data saved successfully." }
        }
    }

}
