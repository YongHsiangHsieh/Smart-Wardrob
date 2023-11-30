package model

import kotlinx.serialization.Serializable

/**
 * Represents different types of clothing items.
 *
 * @property UNKNOWN Represents an unidentified clothing type.
 * @property JUMPER A jumper or sweater.
 * @property SHIRT A shirt, typically with buttons and a collar.
 * @property SHORTS Short trousers, typically worn in warm weather or for sports.
 * @property TRACKSUIT A set of comfortable clothes for physical exercise, consisting of trousers and a jacket or top.
 * @property JACKET An outer garment extending either to the waist or the hips, typically having sleeves and a fastening down the front.
 */
@Serializable
enum class ClothingType {
    UNKNOWN, JUMPER, SHIRT, SHORTS, TRACKSUIT, JACKET
}

/**
 * Represents a piece of clothing with various attributes.
 *
 * @property id Unique identifier for the clothing item, defaulting to -1.
 * @property type The type of clothing, as defined in [ClothingType], defaulting to UNKNOWN.
 * @property brand Brand of the clothing, defaulting to "Unknown".
 * @property name Name of the clothing item, defaulting to "Unknown".
 * @property color Color of the clothing, mutable, defaulting to "Unknown".
 * @property texture Texture or material of the clothing, mutable, defaulting to "Unknown".
 */
@Serializable
data class Clothing(
    val id: Int = -1,
    val type: ClothingType = ClothingType.UNKNOWN,
    val brand: String = "Unknown",
    val name: String = "Unknown",
    var color: String = "Unknown",
    var texture: String = "Unknown"
) {
    override fun toString(): String =
        "Clothing Details:\n" + "ID: $id\n" + "Type: $type\n" + "Brand: $brand\n" + "Name: $name\n" +
                "Color: $color\n" + "Texture: $texture\n"

}
