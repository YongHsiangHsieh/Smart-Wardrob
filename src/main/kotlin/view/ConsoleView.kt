package view

import controller.UserAPI
import controller.WardrobeAPI
import controller.WeatherAPI
import model.User
import service.OutfitSuggester
import service.UserManager
import service.WardrobeManager
import service.WeatherForecaster
import utils.LoggerUtil.printLogger
import utils.ScannerInput
import utils.TimeUtil
import kotlin.system.exitProcess

/**
 * Provides a console-based user interface for interacting with the application.
 * This class encapsulates user interface logic, handling user inputs and displaying relevant information.
 *
 * Utilizes [UserManager] and [WardrobeManager] for handling user and wardrobe data respectively.
 *
 * @property userManager Instance of [UserManager] for managing user-related operations.
 * @property wardrobeManager Instance of [WardrobeManager] for managing wardrobe-related operations.
 * @property currentUser The currently logged-in user, if any.
 */
class ConsoleView(
    userAPI: UserAPI,
    wardrobeAPI: WardrobeAPI
) {
    private val userManager = UserManager(userAPI)
    private val wardrobeManager = WardrobeManager(wardrobeAPI)
    private var currentUser: User? = null

    /**
     * Starts the application, displaying the main menu and handling user interactions.
     */
    fun startApplication() {
        while (true) {
            val choice = MenuDisplay.displayMainMenu()
            when (choice) {
                1 -> handleUserManagement()
                2 -> userLogin()
                0 -> exitApplication()
                else -> println("Invalid option, please try again")
            }
        }
    }

    /**
     * Handles the user management section of the application.
     * Provides options for adding or deleting users.
     */
    private fun handleUserManagement() {
        while (true) {
            val choice = MenuDisplay.displayUserManagementMenu()
            when (choice) {
                1 -> addUser()
                2 -> deleteUser()
                0 -> return
                else -> println("Invalid option, please try again")
            }
        }
    }

    /**
     * Handles the process of adding a new user.
     * Collects user input for username and password, and attempts to create a new user.
     */
    private fun addUser() {
        val username = ScannerInput.readNextLine("Enter username: ")
        val password = ScannerInput.readNextLine("Enter password: ")
        val success = userManager.createUser(username, password)
        if (success) {
            println("User created successfully")
        } else {
            println("User already exists")
        }
    }

    /**
     * Handles the process of deleting an existing user.
     * Collects user input for username, and attempts to delete the user.
     */
    private fun deleteUser() {
        val username = ScannerInput.readNextLine("Enter username: ")
        val success = userManager.deleteUser(username)
        if (success) {
            println("User deleted successfully")
        } else {
            println("User does not exist")
        }
    }

    /**
     * Manages the user login process.
     * Prompts for username and password, and attempts to authenticate the user.
     */
    private fun userLogin() {
        println("Welcome to Smart Wardrobe! Please login to continue.")
        val username = ScannerInput.readNextLine("Enter username: ")
        val password = ScannerInput.readNextLine("Enter password: ")
        val user = userManager.userLogin(username, password)
        if (user != null) {
            println("Login successful")
            currentUser = user
            wardrobeManager.setWardrobe(user.getWardrobe())
            handleWardrobeManagement()
        } else {
            printLogger("Login failed")
        }
    }

    /**
     * Handles wardrobe management functionalities after user login.
     * Provides options for outfit suggestion, viewing, and managing wardrobe.
     */
    private fun handleWardrobeManagement() {
        while (true) {
            val choice = MenuDisplay.displayWardrobe()
            when (choice) {
                1 -> suggestOutfit()
                2 -> viewWardrobe()
                3 -> manageWardrobe()
                0 -> {
                    if (exit()) {
                        return
                    } else {
                        printLogger("Failed to exit")
                    }
                }

                else -> println("Invalid option, please try again")
            }
        }
    }

    /**
     * Suggests an outfit based on the user's wardrobe.
     * Utilizes [OutfitSuggester] for generating outfit suggestions.
     */
    private fun suggestOutfit() {
        val outfit = currentUser?.let { OutfitSuggester.suggestOutfit(it.getWardrobe(), TimeUtil.getCurrentMonthAsInt()) }
        if (!outfit.isNullOrEmpty()) {
            println("Suggested outfit:")
            outfit.forEach { println(it) }
        } else {
            println("No outfit suggestions available")
        }
        val weatherData = WeatherAPI.getApiResponse()
        if (weatherData.isEmpty()) {
            println("No API key found")
        } else {
            if (WeatherForecaster.willRainToday(weatherData)) {
                println("It will rain today, consider bringing an umbrella")
            } else {
                println("It will not rain today, no need for an umbrella")
            }
        }
    }

    /**
     * Manages the display and selection process for viewing wardrobe options.
     * Provides options for viewing all clothing, clothing by type, or clothing by type and color.
     */
    private fun viewWardrobe() {
        while (true) {
            val choice = MenuDisplay.displayViewWardrobe()
            when (choice) {
                1 -> viewAllClothing()
                2 -> viewClothingByType()
                3 -> viewClothingByTypeAndColor()
                0 -> {
                    if (exit()) {
                        return
                    } else {
                        printLogger("Failed to exit")
                    }
                }

                else -> println("Invalid option, please try again")
            }
        }
    }

    /**
     * Manages the display and selection process for managing wardrobe options.
     * Provides options for adding, updating, or removing clothing from the wardrobe.
     */
    private fun manageWardrobe() {
        while (true) {
            val choice = MenuDisplay.displayManageWardrobe()
            when (choice) {
                1 -> addClothing()
                2 -> updateClothing()
                3 -> removeClothing()
                0 -> {
                    if (exit()) {
                        return
                    } else {
                        printLogger("Failed to exit")
                    }
                }

                else -> println("Invalid option, please try again")
            }
        }
    }

    /**
     * Displays all clothing items in the wardrobe.
     * Lists each item with its details if any are available.
     */
    private fun viewAllClothing() {
        val clothing = wardrobeManager.getAllClothing()
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            println("No clothing in wardrobe")
        }
    }

    /**
     * Displays clothing items by a specific type.
     * Prompts for the type and lists items of that type with their details if any are available.
     */
    private fun viewClothingByType() {
        val type = MenuDisplay.displayClothingType()
        val clothing = wardrobeManager.getClothingByType(type)
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            println("No clothing of type ${wardrobeManager.convertToClothingType(type)} in wardrobe")
        }
    }

    /**
     * Displays clothing items by a specific type and color.
     * Prompts for the type and color and lists matching items with their details if any are available.
     */
    private fun viewClothingByTypeAndColor() {
        val type = MenuDisplay.displayClothingType()
        val color = ScannerInput.readNextLine("Enter clothing color: ")
        val clothing = wardrobeManager.getClothingByTypeAndColor(type, color)
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            println("No clothing of type ${wardrobeManager.convertToClothingType(type)} and color $color in wardrobe")
        }
    }

    /**
     * Handles the process of adding a new clothing item to the wardrobe.
     * Collects clothing type, brand, name, color, and texture information for the new item.
     */
    private fun addClothing() {
        val type = MenuDisplay.displayClothingType()
        val brand = ScannerInput.readNextLine("Enter clothing brand: ")
        val name = ScannerInput.readNextLine("Enter clothing name: ")
        val color = ScannerInput.readNextLine("Enter clothing color: ")
        val texture = ScannerInput.readNextLine("Enter clothing texture: ")
        val success = wardrobeManager.addClothing(type, brand, name, color, texture)
        if (success) {
            println("Clothing added successfully")
        } else {
            println("Clothing already exists")
        }
    }

    /**
     * Handles the process of updating an existing clothing item in the wardrobe.
     * Collects new color and texture information along with the clothing item's ID for the update.
     */
    private fun updateClothing() {
        val id = ScannerInput.readNextInt("Enter clothing id: ")
        val color = ScannerInput.readNextLine("Enter clothing color: ")
        val texture = ScannerInput.readNextLine("Enter clothing texture: ")
        val success = wardrobeManager.updateClothing(color, texture, id)
        if (success) {
            println("Clothing updated successfully")
        } else {
            println("Clothing does not exist")
        }
    }

    /**
     * Handles the process of removing a clothing item from the wardrobe.
     * Collects the clothing item's ID and removes the item if it exists.
     */
    private fun removeClothing() {
        val id = ScannerInput.readNextInt("Enter clothing id: ")
        val success = wardrobeManager.removeClothing(id)
        if (success) {
            println("Clothing removed successfully")
        } else {
            println("Clothing does not exist")
        }
    }

    /**
     * Handles the application exit process.
     * Logs the exit attempt, saves the current user's state, and terminates the application.
     */
    private fun exitApplication() {
        printLogger("exitApp() function invoked")
        println("Exiting...bye")
        exitProcess(0)
    }

    /**
     * Saves the current state of the user.
     * This function is typically called before exiting the application.
     *
     * @return `true` if the user state is saved successfully, `false` otherwise.
     */
    private fun exit(): Boolean = userManager.saveUser(currentUser!!)
}
