package model

class Wardrobe {
    private val clothes: MutableList<Clothing> = mutableListOf()

    fun addClothing(clothing: Clothing): Boolean {
        return clothes.add(clothing)
    }

    fun updateClothing(id: Int, color: String? = null, texture: String? = null): Boolean {
        val clothingIndex = clothes.indexOfFirst { it.id == id }
        if (clothingIndex != -1) {
            val clothing = clothes[clothingIndex]
            if (color != null) clothing.color = color
            if (texture != null) clothing.texture = texture
            clothes[clothingIndex] = clothing
            return true
        } else {
            return false
        }
    }

    fun deleteClothing(clothingId: Int): Boolean {
        return clothes.removeIf { it.id == clothingId }
    }

    fun getClothesByType(type: ClothingType): List<Clothing> {
        return clothes.filter { it.type == type }
    }

    fun searchByColorAndType(color: String, type: ClothingType): List<Clothing> {
        return clothes.filter { it.type == type && it.color.equals(color, ignoreCase = true) }
    }
}
