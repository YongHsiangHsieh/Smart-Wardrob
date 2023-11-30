package view

import utils.ScannerInput

object MenuDisplay {
    fun displayMainMenu(): Int =
        ScannerInput.readNextInt(
            """
            1 -> Admin
            2 -> Smart Wardrobe
            0 -> Exit
            Enter option: 
            """.trimIndent()
        )

    fun displayUserManagementMenu(): Int =
        ScannerInput.readNextInt(
            """
            User Management:
            1 -> Create User
            2 -> Delete User
            0 -> Back to Main Menu
            Enter option: 
            """.trimIndent()
        )

    fun displayWardrobe(): Int =
        ScannerInput.readNextInt(
            """
            1 -> Today's Outfit
            2 -> View Wardrobe
            3 -> Manage Wardrobe
            0 -> Back to Main Menu
            Enter option: 
            """.trimIndent()
        )

    fun displayManageWardrobe(): Int =
        ScannerInput.readNextInt(
            """
            Wardrobe Management:
            1 -> Add Clothing
            2 -> Update Clothing
            3 -> Remove Clothing
            0 -> Back to Wardrobe Menu
            Enter option: 
            """.trimIndent()
        )

    fun displayViewWardrobe(): Int =
        ScannerInput.readNextInt(
            """
            Wardrobe View:
            1 -> View All Clothing
            2 -> View Clothing by Type
            3 -> View Clothing by Type and Color
            0 -> Back to Wardrobe Menu
            Enter option: 
            """.trimIndent()
        )

    fun displayClothingType(): Int =
        ScannerInput.readNextInt(
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
}