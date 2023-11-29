package service

import model.Clothing
import model.ClothingType
import model.Wardrobe

object OutfitSuggester {
    private val seasonClothingMap = mapOf(
        Season.SPRING to listOf(ClothingType.SHIRT, ClothingType.TRACKSUIT),
        Season.SUMMER to listOf(ClothingType.SHIRT, ClothingType.SHORTS),
        Season.AUTUMN to listOf(ClothingType.JUMPER, ClothingType.TRACKSUIT),
        Season.WINTER to listOf(ClothingType.JUMPER, ClothingType.TRACKSUIT, ClothingType.JACKET)
    )

    fun suggestOutfit(wardrobe: Wardrobe, currentMonth: Int): List<Clothing> {
        val season = determineSeason(currentMonth) ?: return emptyList()
        return getSeasonalOutfit(season, wardrobe)
    }

    private fun determineSeason(month: Int): Season? {
        return when (month) {
            in 3..5 -> Season.SPRING
            in 6..8 -> Season.SUMMER
            in 9..11 -> Season.AUTUMN
            12, 1, 2 -> Season.WINTER
            else -> null
        }
    }

    private fun selectRandomOutfit(clothingType: ClothingType, wardrobe: Wardrobe): Clothing? =
        wardrobe.getClothesByType(clothingType).randomOrNull()

    private fun getSeasonalOutfit(season: Season, wardrobe: Wardrobe): List<Clothing> =
        seasonClothingMap[season]?.mapNotNull { selectRandomOutfit(it, wardrobe) }.orEmpty()
}

enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}
