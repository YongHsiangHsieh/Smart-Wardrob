package service

import controller.WardrobeAPI
import model.Clothing
import model.ClothingType


class WardrobeManager (private val wardrobeAPI: WardrobeAPI){
    fun addClothing(id: Int, type: ClothingType, brand: String, name: String, color: String, texture: String): Boolean {
        if (id <= 0 || brand.isBlank() || name.isBlank() || color.isBlank() || texture.isBlank()) {
            return false
        }

        val clothingData = mapOf(
            "id" to id.toString(),
            "type" to type.toString(),
            "brand" to brand,
            "name" to name,
            "color" to color,
            "texture" to texture
        )

        return wardrobeAPI.addClothingToWardrobe(clothingData)
    }


    fun updateClothing(color: String, texture: String, id: Int): Boolean {
        val clothingData = mapOf(
            "color" to color,
            "texture" to texture
        )
        return wardrobeAPI.updateClothingInWardrobe(id, clothingData)
    }

    fun removeClothing(id: Int) = wardrobeAPI.deleteClothingFromWardrobe(id)

    fun getAllClothing(): List<Clothing> =  wardrobeAPI.getAllClothing()

    fun getClothingByType(type: ClothingType): List<Clothing> = wardrobeAPI.getClothingByType(type)

    fun getClothingByTypeAndColor(type: ClothingType, color: String): List<Clothing> = wardrobeAPI.searchClothingByColorAndType(color, type)




}