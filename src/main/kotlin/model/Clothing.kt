package model

enum class ClothingType {
    JUMPER, SHIRT, SHORTS, TRACKSUIT, JACKET
}

data class Clothing(
    val id: Int,
    val type: ClothingType,
    val brand: String,
    val name: String,
    val color: String,
    val texture: String,
)
