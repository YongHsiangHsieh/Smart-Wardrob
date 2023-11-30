package service

import controller.WardrobeAPI
import model.Clothing
import model.ClothingType
import model.Wardrobe

/**
 * Manages wardrobe operations by interfacing with the [WardrobeAPI].
 * This class provides functionalities for adding, updating, removing, and retrieving clothing items.
 *
 * @property wardrobeAPI An instance of [WardrobeAPI] used to perform operations on the wardrobe data.
 */
class WardrobeManager(private val wardrobeAPI: WardrobeAPI) {

    /**
     * Adds a clothing item to the wardrobe.
     *
     * @param id The unique identifier of the clothing item. Must be greater than 0.
     * @param type The type of the clothing item, represented as an integer.
     * @param brand The brand of the clothing item. Cannot be blank.
     * @param name The name of the clothing item. Cannot be blank.
     * @param color The color of the clothing item. Cannot be blank.
     * @param texture The texture of the clothing item. Cannot be blank.
     * @return `true` if the clothing item was successfully added; `false` otherwise.
     */
    fun addClothing(id: Int, type: Int, brand: String, name: String, color: String, texture: String): Boolean {
        if (id <= 0 || type < 1 || brand.isBlank() || name.isBlank() || color.isBlank() || texture.isBlank()) {
            return false
        }

        val clothingData = mapOf(
            "id" to id.toString(),
            "type" to convertToClothingType(type).toString(),
            "brand" to brand,
            "name" to name,
            "color" to color,
            "texture" to texture
        )

        return wardrobeAPI.addClothingToWardrobe(clothingData)
    }

    /**
     * Updates the color and texture of a clothing item.
     *
     * @param color The new color of the clothing item. Cannot be blank.
     * @param texture The new texture of the clothing item. Cannot be blank.
     * @param id The unique identifier of the clothing item to update. Must be greater than 0.
     * @return `true` if the clothing item was successfully updated; `false` otherwise.
     */
    fun updateClothing(color: String, texture: String, id: Int): Boolean {
        if (color.isBlank() || texture.isBlank() || id <= 0) {
            return false
        }
        val clothingData = mapOf(
            "color" to color,
            "texture" to texture
        )
        return wardrobeAPI.updateClothingInWardrobe(id, clothingData)
    }

    /**
     * Removes a clothing item from the wardrobe by its ID.
     *
     * @param id The unique identifier of the clothing item to remove. Must be greater than 0.
     * @return `true` if the clothing item was successfully removed; `false` otherwise.
     */
    fun removeClothing(id: Int) =
        if (id <= 0) {
            false
        } else {
            wardrobeAPI.deleteClothingFromWardrobe(id)
        }

    /**
     * Retrieves all clothing items from the wardrobe.
     *
     * @return A list of [Clothing] items currently in the wardrobe.
     */
    fun getAllClothing(): List<Clothing> = wardrobeAPI.getAllClothing()

    /**
     * Retrieves clothing items of a specific type.
     *
     * @param type The type of the clothing items to retrieve, represented as an integer.
     * @return A list of [Clothing] items matching the specified type.
     */
    fun getClothingByType(type: Int): List<Clothing> {
        val clothingType = convertToClothingType(type)
        return wardrobeAPI.getClothingByType(clothingType)
    }

    /**
     * Retrieves clothing items of a specific type and color.
     *
     * @param type The type of the clothing items to retrieve, represented as an integer.
     * @param color The color of the clothing items to retrieve. Cannot be blank.
     * @return A list of [Clothing] items matching the specified type and color.
     */
    fun getClothingByTypeAndColor(type: Int, color: String): List<Clothing> =
        if (color.isBlank()) {
            emptyList()
        } else {
            wardrobeAPI.searchClothingByColorAndType(color, convertToClothingType(type))
        }

    /**
     * Converts an integer to a [ClothingType].
     *
     * @param type The integer representation of a clothing type.
     * @return The corresponding [ClothingType] enum.
     */
    fun convertToClothingType(type: Int): ClothingType =
        when (type) {
            1 -> ClothingType.JUMPER
            2 -> ClothingType.SHIRT
            3 -> ClothingType.SHORTS
            4 -> ClothingType.TRACKSUIT
            5 -> ClothingType.JACKET
            else -> ClothingType.UNKNOWN
        }

    /**
     * Sets the current wardrobe with a new [Wardrobe] instance.
     *
     * @param newWardrobe The new [Wardrobe] instance to set.
     */
    fun setWardrobe(newWardrobe: Wardrobe) {
        wardrobeAPI.setWardrobe(newWardrobe)
    }
}
