package service

import controller.WardrobeAPI
import model.Clothing
import model.ClothingType
import model.Wardrobe


class WardrobeManager(private val wardrobeAPI: WardrobeAPI) {
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

    fun removeClothing(id: Int) =
        if (id <= 0) {
            false
        } else wardrobeAPI.deleteClothingFromWardrobe(id)

    fun getAllClothing(): List<Clothing> = wardrobeAPI.getAllClothing()

    fun getClothingByType(type: Int): List<Clothing> {
        val clothingType = convertToClothingType(type)
        return wardrobeAPI.getClothingByType(clothingType)
    }

    fun getClothingByTypeAndColor(type: Int, color: String): List<Clothing> =
        if (color.isBlank()) {
            emptyList()
        } else wardrobeAPI.searchClothingByColorAndType(color, convertToClothingType(type))

    fun convertToClothingType(type: Int): ClothingType =
        when (type) {
            1 -> ClothingType.JUMPER
            2 -> ClothingType.SHIRT
            3 -> ClothingType.SHORTS
            4 -> ClothingType.TRACKSUIT
            5 -> ClothingType.JACKET
            else -> ClothingType.UNKNOWN
        }
    fun setWardrobe(newWardrobe: Wardrobe) {
        wardrobeAPI.setWardrobe(newWardrobe)
    }

}