package view

import controller.UserAPI
import controller.WardrobeAPI
import model.User
import service.OutfitSuggester
import service.UserManager
import service.WardrobeManager
import utils.LoggerUtil.printLogger
import utils.ScannerInput
import kotlin.system.exitProcess


class ConsoleView(
    userAPI: UserAPI,
    wardrobeAPI: WardrobeAPI
) {
    private val userManager = UserManager(userAPI)
    private val wardrobeManager = WardrobeManager(wardrobeAPI)
    private var currentUser: User? = null

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

    private fun deleteUser() {
        val username = ScannerInput.readNextLine("Enter username: ")
        val success = userManager.deleteUser(username)
        if (success) {
            println("User deleted successfully")
        } else {
            println("User does not exist")
        }
    }

    // User login
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

    private fun handleWardrobeManagement() {
        while (true) {
            val choice = MenuDisplay.displayWardrobe()
            when (choice) {
                1 -> suggestOutfit()
                2 -> viewWardrobe()
                3 -> manageWardrobe()
                0 -> {
                    if (exit()) return
                    else printLogger("Failed to exit")
                }

                else -> println("Invalid option, please try again")
            }
        }
    }

    private fun suggestOutfit() {
        val outfit = currentUser?.let { OutfitSuggester.suggestOutfit(it.getWardrobe(), 5) }
        if (outfit != null) {
            outfit.forEach { println(it) }
        } else {
            println("No outfit suggestions available")
        }
    }

    private fun viewWardrobe() {
        while (true) {
            val choice = MenuDisplay.displayViewWardrobe()
            when (choice) {
                1 -> viewAllClothing()
                2 -> viewClothingByType()
                3 -> viewClothingByTypeAndColor()
                0 -> {
                    if (exit()) return
                    else printLogger("Failed to exit")
                }

                else -> println("Invalid option, please try again")
            }
        }
    }

    private fun manageWardrobe() {
        while (true) {
            val choice = MenuDisplay.displayManageWardrobe()
            when (choice) {
                1 -> addClothing()
                2 -> updateClothing()
                3 -> removeClothing()
                0 -> {
                    if (exit()) return
                    else printLogger("Failed to exit")
                }

                else -> println("Invalid option, please try again")
            }
        }
    }

    private fun viewAllClothing() {
        val clothing = wardrobeManager.getAllClothing()
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            println("No clothing in wardrobe")
        }
    }

    private fun viewClothingByType() {
        val type = MenuDisplay.displayClothingType()
        val clothing = wardrobeManager.getClothingByType(type)
        if (clothing.isNotEmpty()) {
            clothing.forEach { println(it) }
        } else {
            println("No clothing of type ${wardrobeManager.convertToClothingType(type)} in wardrobe")
        }
    }

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

    private fun addClothing() {
        val id = ScannerInput.readNextInt("Enter clothing id: ")
        val type = MenuDisplay.displayClothingType()
        val brand = ScannerInput.readNextLine("Enter clothing brand: ")
        val name = ScannerInput.readNextLine("Enter clothing name: ")
        val color = ScannerInput.readNextLine("Enter clothing color: ")
        val texture = ScannerInput.readNextLine("Enter clothing texture: ")
        val success = wardrobeManager.addClothing(id, type, brand, name, color, texture)
        if (success) {
            println("Clothing added successfully")
        } else {
            println("Clothing already exists")
        }
    }

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
     * Handles the process of exiting the application, including saving any changes to the current user.
     */
    private fun exitApplication() {
        exit()
        printLogger("exitApp() function invoked")
        println("Exiting...bye")
        exitProcess(0)
    }

    private fun exit(): Boolean = userManager.saveUser(currentUser!!)

}
