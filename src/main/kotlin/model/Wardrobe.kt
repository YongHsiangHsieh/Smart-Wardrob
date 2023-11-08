package model

class Wardrobe {
    private val clothes: MutableList<Clothing> = mutableListOf()

    fun addClothing(clothing: Clothing) {
    }

    fun updateClothing(clothing: Clothing) {
    }

    fun deleteClothing(clothingId: Int) {
    }

    fun getClothesByType(type: ClothingType): List<Clothing> {
        return clothes.filter { it.type == type }
    }

    fun searchByColorAndType(color: String, type: ClothingType): List<Clothing> {
        return clothes.filter { it.type == type && it.color.equals(color, ignoreCase = true) }
    }
}
