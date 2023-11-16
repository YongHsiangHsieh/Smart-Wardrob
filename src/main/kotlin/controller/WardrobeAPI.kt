package controller

import model.Clothing
import model.ClothingType
import model.Wardrobe

class WardrobeAPI(private var wardrobe: Wardrobe? = null) {

    fun addClothingToWardrobe(clothingData: Map<String, String>): Boolean {
        val id = clothingData["id"]?.toIntOrNull() ?: return false
        val type = clothingData["type"]?.let { ClothingType.valueOf(it) } ?: return false
        val color = clothingData["color"] ?: return false
        val texture = clothingData["texture"] ?: return false
        return wardrobe?.addClothing(Clothing(id, type, color, texture)) ?: false
    }

    fun updateClothingInWardrobe(clothingId: Int, clothingData: Map<String, String>): Boolean {
        val color = clothingData["color"]?: return false
        val texture = clothingData["texture"]?: return false
        return wardrobe?.updateClothing(clothingId, color, texture) ?: false
    }

    fun deleteClothingFromWardrobe(clothingId: Int): Boolean {
        return wardrobe?.deleteClothing(clothingId) ?:  false
    }

    fun getAllClothing(): List<Clothing> {
        return wardrobe?.getAllClothing() ?: emptyList()
    }

    fun getClothingById(id: Int): Clothing? {
        return wardrobe?.getClothingById(id)
    }

    fun getClothingByType(type: ClothingType): List<Clothing> {
        return wardrobe?.getClothesByType(type) ?: emptyList()
    }

    fun searchClothingByColorAndType(color: String, type: ClothingType): List<Clothing> {
        return wardrobe?.searchByColorAndType(color, type) ?: emptyList()
    }

    fun setWardrobe(newWardrobe: Wardrobe) {
        wardrobe = newWardrobe
    }
}
