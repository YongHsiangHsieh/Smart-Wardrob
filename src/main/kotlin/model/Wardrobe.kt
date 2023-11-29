package model

import kotlinx.serialization.Serializable

/**
 * A class representing a wardrobe that stores various clothing items.
 *
 * @property clothes A private mutable list to hold [Clothing] items.
 */
@Serializable
class Wardrobe {
    private val clothes: MutableList<Clothing> = mutableListOf()

    /**
     * Adds a clothing item to the wardrobe.
     *
     * @param clothing The [Clothing] item to be added.
     * @return True if the item is successfully added, false otherwise.
     */
    fun addClothing(clothing: Clothing) = clothes.add(clothing)

    /**
     * Updates the color and/or texture of a clothing item identified by its ID.
     *
     * @param id The ID of the clothing to be updated.
     * @param color Optional new color for the clothing.
     * @param texture Optional new texture for the clothing.
     * @return True if the update is successful, false if the item with the given ID is not found.
     */
    fun updateClothing(id: Int, color: String? = null, texture: String? = null): Boolean =
        clothes.firstOrNull { it.id == id }?.apply {
            if (color != null) this.color = color
            if (texture != null) this.texture = texture
        } != null

    /**
     * Deletes a clothing item from the wardrobe based on its ID.
     *
     * @param clothingId The ID of the clothing to be deleted.
     * @return True if the deletion is successful, false otherwise.
     */
    fun deleteClothing(clothingId: Int) = clothes.removeIf { it.id == clothingId }

    /**
     * Retrieves all clothing items in the wardrobe.
     *
     * @return A list of all [Clothing] items.
     */
    fun getAllClothing(): List<Clothing> = clothes.toList()

    /**
     * Retrieves a specific clothing item by its ID.
     *
     * @param id The ID of the clothing to be retrieved.
     * @return The [Clothing] item if found, null otherwise.
     */
    fun getClothingById(id: Int): Clothing? = clothes.firstOrNull { it.id == id }

    /**
     * Retrieves all clothing items of a specific type.
     *
     * @param type The [ClothingType] of clothing to retrieve.
     * @return A list of [Clothing] items of the specified type.
     */
    fun getClothesByType(type: ClothingType): List<Clothing> = clothes.filter { it.type == type }

    /**
     * Searches for clothing items by their color and type.
     *
     * @param color The color of the clothing to search for.
     * @param type The [ClothingType] of the clothing to search for.
     * @return A list of [Clothing] items that match the specified color and type.
     */
    fun searchByColorAndType(color: String, type: ClothingType): List<Clothing> {
        return clothes.filter { it.type == type && it.color.equals(color, ignoreCase = true) }
    }
}
