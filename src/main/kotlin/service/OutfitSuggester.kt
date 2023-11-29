package service

import model.Clothing
import model.ClothingType
import model.Wardrobe

/**
 * Utility object for suggesting outfits based on the current season.
 */
object OutfitSuggester {

    /**
     * Suggests an outfit based on the given wardrobe and the current month.
     *
     * Determines the current season from the month and selects an appropriate outfit from the wardrobe.
     *
     * @param wardrobe The wardrobe from which to select the outfit.
     * @param currentMonth The current month, used to determine the season.
     * @return A list of [Clothing] items representing the suggested outfit.
     */
    fun suggestOutfit(wardrobe: Wardrobe, currentMonth: Int): List<Clothing>? {
        val season = determineSeason(currentMonth)
        return season?.let { getSeasonalOutfit(it, wardrobe) }
    }

    /**
     * Determines the season based on the given month.
     *
     * @param month The month for which to determine the season.
     * @return The [Season] corresponding to the given month.
     */
    private fun determineSeason(month: Int): Season? =
        when (month) {
            in 2..4 -> Season.SPRING
            in 5..7 -> Season.SUMMER
            in 8..10 -> Season.AUTUMN
            11, 12, 1 -> Season.WINTER
            else -> null
        }

    /**
     * Selects a random piece of clothing of a specified type from the wardrobe.
     *
     * @param clothingTypes The type of clothing to select.
     * @param wardrobe The wardrobe from which to select the clothing.
     * @return A randomly selected [Clothing] item of the specified type, or null if none are available.
     */
    private fun selectRandomOutfit(clothingTypes: ClothingType, wardrobe: Wardrobe): Clothing? {
        val clothing = wardrobe.getClothesByType(clothingTypes)
        return if (clothing.isNotEmpty()) {
            clothing.random()
        } else {
            null
        }
    }

    /**
     * Compiles an outfit suitable for the given season from the wardrobe.
     *
     * @param season The season for which to compile the outfit.
     * @param wardrobe The wardrobe from which to select the outfit.
     * @return A list of [Clothing] items representing the outfit suitable for the given season.
     */
    private fun getSeasonalOutfit(season: Season, wardrobe: Wardrobe): List<Clothing> =
        when (season) {
            Season.SPRING -> listOfNotNull(
                selectRandomOutfit(ClothingType.SHIRT, wardrobe),
                selectRandomOutfit(ClothingType.TRACKSUIT, wardrobe)
            )

            Season.SUMMER -> listOfNotNull(
                selectRandomOutfit(ClothingType.SHIRT, wardrobe),
                selectRandomOutfit(ClothingType.SHORTS, wardrobe)
            )

            Season.AUTUMN -> listOfNotNull(
                selectRandomOutfit(ClothingType.JUMPER, wardrobe),
                selectRandomOutfit(ClothingType.TRACKSUIT, wardrobe)
            )

            Season.WINTER -> listOfNotNull(
                selectRandomOutfit(ClothingType.JUMPER, wardrobe),
                selectRandomOutfit(ClothingType.TRACKSUIT, wardrobe),
                selectRandomOutfit(ClothingType.JACKET, wardrobe)
            )
        }
}

/**
 * Enum representing the four seasons.
 */
enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}
