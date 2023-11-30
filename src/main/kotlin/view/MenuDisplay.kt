package view

import utils.ScannerInput

object MenuDisplay {
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