package controller

import model.Clothing
import model.ClothingType
import model.Wardrobe

class WardrobeController(private val wardrobe: Wardrobe) {

    fun addClothingToWardrobe(clothingData: Map<String, String>) {
    }

    fun updateClothingInWardrobe(clothingId: Int, clothingData: Map<String, String>) {
    }

    fun deleteClothingFromWardrobe(clothingId: Int) {
    }

    fun getClothingByType(type: ClothingType): List<Clothing> {
        return wardrobe.getClothesByType(type)
    }

    fun searchClothingByColorAndType(color: String, type: ClothingType): List<Clothing> {
        return wardrobe.searchByColorAndType(color, type)
    }
}
