package controller

import model.Clothing
import model.ClothingType
import model.Wardrobe

class WardrobeAPI(private val wardrobe: Wardrobe) {

    fun addClothingToWardrobe(clothingData: Map<String, String>): Boolean {
        val id = clothingData["id"]?.toIntOrNull() ?: return false
        val type = clothingData["type"]?.let { ClothingType.valueOf(it) } ?: return false
        val color = clothingData["color"] ?: return false
        val texture = clothingData["texture"] ?: return false
        return wardrobe.addClothing(Clothing(id, type, color, texture))
    }

    fun updateClothingInWardrobe(clothingId: Int, clothingData: Map<String, String>): Boolean {
        val color = clothingData["color"]?: return false
        val texture = clothingData["texture"]?: return false
        return wardrobe.updateClothing(clothingId, color, texture)
    }

    fun deleteClothingFromWardrobe(clothingId: Int): Boolean {
        return wardrobe.deleteClothing(clothingId)
    }

    fun getAllClothing(): List<Clothing> {
        return wardrobe.getAllClothing()
    }

    fun getClothingById(id: Int): Clothing? {
        return wardrobe.getClothingById(id)
    }

    fun getClothingByType(type: ClothingType): List<Clothing> {
        return wardrobe.getClothesByType(type)
    }

    fun searchClothingByColorAndType(color: String, type: ClothingType): List<Clothing> {
        return wardrobe.searchByColorAndType(color, type)
    }
}
