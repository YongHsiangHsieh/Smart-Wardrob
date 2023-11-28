package controller

import model.Clothing
import model.ClothingType
import model.Wardrobe

/**
 * API class for managing a wardrobe. Provides functionalities to add, update, delete, and retrieve clothing items.
 *
 * @property wardrobe An optional [Wardrobe] instance which can be initialized later.
 */
class WardrobeAPI(private var wardrobe: Wardrobe? = null) {

    /**
     * Adds a new clothing item to the wardrobe.
     *
     * @param clothingData A map containing the details of the clothing item.
     * @return True if the clothing item is successfully added, false otherwise.
     */
    fun addClothingToWardrobe(clothingData: Map<String, String>): Boolean {
        val id = clothingData["id"]?.toIntOrNull() ?: return false
        val type = clothingData["type"]?.let { ClothingType.valueOf(it) } ?: return false
        val brand = clothingData["brand"] ?: return false
        val name = clothingData["name"] ?: return false
        val color = clothingData["color"] ?: return false
        val texture = clothingData["texture"] ?: return false
        return wardrobe?.addClothing(Clothing(id, type, brand, name, color, texture)) ?: false
    }

    /**
     * Updates a clothing item in the wardrobe identified by its ID.
     *
     * @param clothingId The ID of the clothing to update.
     * @param clothingData A map containing the updated attributes (color and texture) of the clothing.
     * @return True if the update is successful, false otherwise.
     */
    fun updateClothingInWardrobe(clothingId: Int, clothingData: Map<String, String>): Boolean {
        val color = clothingData["color"]?: return false
        val texture = clothingData["texture"]?: return false
        return wardrobe?.updateClothing(clothingId, color, texture) ?: false
    }

    /**
     * Deletes a clothing item from the wardrobe by its ID.
     *
     * @param clothingId The ID of the clothing to delete.
     * @return True if the deletion is successful, false otherwise.
     */
    fun deleteClothingFromWardrobe(clothingId: Int): Boolean {
        return wardrobe?.deleteClothing(clothingId) ?:  false
    }

    /**
     * Retrieves all clothing items from the wardrobe.
     *
     * @return A list of [Clothing] items in the wardrobe, or an empty list if the wardrobe is not set.
     */
    fun getAllClothing(): List<Clothing> {
        return wardrobe?.getAllClothing() ?: emptyList()
    }

    /**
     * Retrieves a specific clothing item from the wardrobe by its ID.
     *
     * @param id The ID of the clothing item to retrieve.
     * @return The [Clothing] item if found, null otherwise.
     */
    fun getClothingById(id: Int): Clothing? {
        return wardrobe?.getClothingById(id)
    }

    /**
     * Retrieves all clothing items of a specific type from the wardrobe.
     *
     * @param type The [ClothingType] to filter the clothing items.
     * @return A list of [Clothing] items of the specified type, or an empty list if the wardrobe is not set.
     */
    fun getClothingByType(type: ClothingType): List<Clothing> {
        return wardrobe?.getClothesByType(type) ?: emptyList()
    }

    /**
     * Searches for clothing items in the wardrobe by their color and type.
     *
     * @param color The color to filter the clothing items.
     * @param type The [ClothingType] to filter the clothing items.
     * @return A list of [Clothing] items that match the specified color and type, or an empty list if the wardrobe is not set.
     */
    fun searchClothingByColorAndType(color: String, type: ClothingType): List<Clothing> {
        return wardrobe?.searchByColorAndType(color, type) ?: emptyList()
    }

    /**
     * Sets a new wardrobe instance for the API to manage.
     *
     * @param newWardrobe The new [Wardrobe] instance to be managed by the API.
     */
    fun setWardrobe(newWardrobe: Wardrobe) {
        wardrobe = newWardrobe
    }


}
