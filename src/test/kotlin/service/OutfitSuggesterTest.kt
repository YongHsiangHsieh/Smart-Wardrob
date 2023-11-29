package service

import model.Clothing
import model.ClothingType
import model.Wardrobe
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OutfitSuggesterTest {

    @Test
    fun `suggestOutfit should return appropriate outfit for spring months`() {
        val wardrobe = createTestWardrobe()
        val springMonths = 2..4

        springMonths.forEach { month ->
            val suggestedOutfit = OutfitSuggester.suggestOutfit(wardrobe, month)
            assertTrue(suggestedOutfit.containsType(ClothingType.SHIRT))
            assertTrue(suggestedOutfit.containsType(ClothingType.TRACKSUIT))
        }
    }

    @Test
    fun `suggestOutfit should return appropriate outfit for summer months`() {
        val wardrobe = createTestWardrobe()
        val summerMonths = 5..7

        summerMonths.forEach { month ->
            val suggestedOutfit = OutfitSuggester.suggestOutfit(wardrobe, month)
            assertTrue(suggestedOutfit.containsType(ClothingType.SHIRT))
            assertTrue(suggestedOutfit.containsType(ClothingType.SHORTS))
        }
    }

    @Test
    fun `suggestOutfit should return appropriate outfit for autumn months`() {
        val wardrobe = createTestWardrobe()
        val autumnMonths = 8..10

        autumnMonths.forEach { month ->
            val suggestedOutfit = OutfitSuggester.suggestOutfit(wardrobe, month)
            assertTrue(suggestedOutfit.containsType(ClothingType.JUMPER))
            assertTrue(suggestedOutfit.containsType(ClothingType.TRACKSUIT))
        }
    }

    @Test
    fun `suggestOutfit should return appropriate outfit for winter months`() {
        val wardrobe = createTestWardrobe()
        val winterMonths = listOf(1, 11, 12)

        winterMonths.forEach { month ->
            val suggestedOutfit = OutfitSuggester.suggestOutfit(wardrobe, month)
            assertTrue(suggestedOutfit.containsType(ClothingType.JUMPER))
            assertTrue(suggestedOutfit.containsType(ClothingType.TRACKSUIT))
            assertTrue(suggestedOutfit.containsType(ClothingType.JACKET))
        }
    }

    private fun createTestWardrobe(): Wardrobe {
        val wardrobe = Wardrobe()
        listOf(
            ClothingType.SHIRT,
            ClothingType.SHORTS,
            ClothingType.TRACKSUIT,
            ClothingType.JACKET,
            ClothingType.JUMPER
        ).forEach { type ->
            wardrobe.addClothing(Clothing(id = 1, type = type, brand = "TestBrand", name = "TestName", color = "TestColor", texture = "TestTexture"))
        }
        return wardrobe
    }

    private fun List<Clothing>.containsType(type: ClothingType): Boolean {
        return this.any { it.type == type }
    }
}
