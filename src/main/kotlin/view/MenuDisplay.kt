package view

import utils.ScannerInput

/**
 * Provides display menus and options for the user interface.
 * This object manages various menu displays, guiding users through different choices in the application.
 *
 * Utilizes [ScannerInput] for reading user input to navigate through the menus.
 */
object MenuDisplay {

    /**
     * Displays the main menu and handles user input for menu selection.
     *
     * Offers options for navigating to different sections such as Admin, Smart Wardrobe, or exiting the application.
     * Validates the user input to ensure it's within the acceptable range.
     *
     * @return The selected menu option as an [Int].
     */
    fun displayMainMenu(): Int {
        while (true) {
            val choice = ScannerInput.readNextInt(
                """
                1 -> Admin
                2 -> Smart Wardrobe
                0 -> Exit
                Enter option: 
                """.trimIndent()
            )
            if (choice in 0..2) {
                return choice
            }
            println("Invalid option, please try again")
        }
    }

    /**
     * Displays the user management menu and handles user input for menu selection.
     *
     * Provides options for creating or deleting users, or returning to the main menu.
     * Validates the user input to ensure it's within the acceptable range.
     *
     * @return The selected menu option as an [Int].
     */
    fun displayUserManagementMenu(): Int {
        while (true) {
            val choice = ScannerInput.readNextInt(
                """
                1 -> Create User
                2 -> Delete User
                0 -> Back to Main Menu
                Enter option: 
                """.trimIndent()
            )
            if (choice in 0..2) {
                return choice
            }
            println("Invalid option, please try again")
        }
    }

    /**
     * Displays the wardrobe menu and handles user input for menu selection.
     *
     * Offers choices like viewing today's outfit, managing or viewing the entire wardrobe, or returning to the main menu.
     * Validates the user input to ensure it's within the acceptable range.
     *
     * @return The selected menu option as an [Int].
     */
    fun displayWardrobe(): Int {
        while (true) {
            val choice = ScannerInput.readNextInt(
                """
            1 -> Today's Outfit
            2 -> View Wardrobe
            3 -> Manage Wardrobe
            0 -> Back to Main Menu
            Enter option: 
                """.trimIndent()
            )
            if (choice in 0..3) {
                return choice
            }
            println("Invalid option, please try again")
        }
    }

    /**
     * Displays the wardrobe management menu and handles user input for menu selection.
     *
     * Includes options for adding, updating, or removing clothing, or returning to the previous menu.
     * Validates the user input to ensure it's within the acceptable range.
     *
     * @return The selected menu option as an [Int].
     */
    fun displayManageWardrobe(): Int {
        while (true) {
            val choice = ScannerInput.readNextInt(
                """
            1 -> Add Clothing
            2 -> Update Clothing
            3 -> Remove Clothing
            0 -> Back to Wardrobe Menu
            Enter option: 
                """.trimIndent()
            )
            if (choice in 0..3) {
                return choice
            }
            println("Invalid option, please try again")
        }
    }

    /**
     * Displays the view wardrobe menu and handles user input for menu selection.
     *
     * Provides options for viewing all clothing, clothing by type, clothing by type and color, or returning to the previous menu.
     * Validates the user input to ensure it's within the acceptable range.
     *
     * @return The selected menu option as an [Int].
     */
    fun displayViewWardrobe(): Int {
        while (true) {
            val choice = ScannerInput.readNextInt(
                """
            1 -> View All Clothing
            2 -> View Clothing by Type
            3 -> View Clothing by Type and Color
            0 -> Back to Wardrobe Menu
            Enter option: 
                """.trimIndent()
            )
            if (choice in 0..3) {
                return choice
            }
            println("Invalid option, please try again")
        }
    }

    /**
     * Displays clothing types and handles user input for selecting a type.
     *
     * Lists different types of clothing such as Jumper, Shirt, Shorts, etc., for user selection.
     * Validates the user input to ensure it's within the acceptable range of types.
     *
     * @return The selected clothing type as an [Int].
     */
    fun displayClothingType(): Int {
        while (true) {
            val choice = ScannerInput.readNextInt(
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
            if (choice in 1..5) {
                return choice
            }
            println("Invalid option, please try again")
        }
    }
}
