package service

import model.Clothing
import model.ClothingType
import model.Wardrobe

object OutfitSuggester {

    fun suggestOutfit(wardrobe: Wardrobe, currentMonth: Int): List<Clothing> {
        val season = determineSeason(currentMonth)
        return getSeasonalOutfit(season, wardrobe)
    }

    private fun determineSeason(month: Int): Season {
        return when (month) {
            in 2..4 -> Season.SPRING
            in 5..7 -> Season.SUMMER
            in 8..10 -> Season.AUTUMN
            else -> Season.WINTER
        }
    }


    private fun selectRandomOutfit(clothingTypes: ClothingType, wardrobe: Wardrobe): Clothing? {
        val clothing = wardrobe.getClothesByType(clothingTypes)
        return if (clothing.isNotEmpty()) {
            clothing.random()
        } else {
            null
        }
    }

    private fun getSeasonalOutfit(season: Season, wardrobe: Wardrobe): List<Clothing> {
        return when (season) {
            Season.SPRING -> listOfNotNull(
                selectRandomOutfit(ClothingType.SHIRT, wardrobe),
                selectRandomOutfit(ClothingType.TRACKSUIT, wardrobe),
            )

            Season.SUMMER -> listOfNotNull(
                selectRandomOutfit(ClothingType.SHIRT, wardrobe),
                selectRandomOutfit(ClothingType.SHORTS, wardrobe),
            )

            Season.AUTUMN -> listOfNotNull(
                selectRandomOutfit(ClothingType.JUMPER, wardrobe),
                selectRandomOutfit(ClothingType.TRACKSUIT, wardrobe),
            )
            Season.WINTER -> listOfNotNull(
                selectRandomOutfit(ClothingType.JUMPER, wardrobe),
                selectRandomOutfit(ClothingType.TRACKSUIT, wardrobe),
                selectRandomOutfit(ClothingType.JACKET, wardrobe),
            )
        }
    }

}


enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}