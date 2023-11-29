package model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WardrobeTest {

    private lateinit var wardrobe: Wardrobe
    private val testClothing = Clothing(id = 1, type = ClothingType.SHIRT, brand = "TestBrand", name = "TestName", color = "Red", texture = "Cotton")

    @BeforeEach
    fun setup() {
        wardrobe = Wardrobe()
    }

    @Test
    fun `add clothing should successfully add item to wardrobe`() {
        assertTrue(wardrobe.addClothing(testClothing))
    }

    @Test
    fun `update clothing should correctly update clothing attributes`() {
        wardrobe.addClothing(testClothing)
        assertTrue(wardrobe.updateClothing(1, color = "Blue"))
        assertEquals("Blue", wardrobe.getClothingById(1)?.color)
    }

    @Test
    fun `delete clothing should successfully remove item from wardrobe`() {
        wardrobe.addClothing(testClothing)
        assertTrue(wardrobe.deleteClothing(1))
        assertNull(wardrobe.getClothingById(1))
    }

    @Test
    fun `get all clothing should return correct number of items`() {
        wardrobe.addClothing(testClothing)
        assertEquals(1, wardrobe.getAllClothing().size)
    }

    @Test
    fun `get clothing by ID should return the correct item`() {
        wardrobe.addClothing(testClothing)
        assertNotNull(wardrobe.getClothingById(1))
    }

    @Test
    fun `get clothes by type should return items of specified type`() {
        wardrobe.addClothing(testClothing)
        assertEquals(1, wardrobe.getClothesByType(ClothingType.SHIRT).size)
    }

    @Test
    fun `search by color and type should return matching items`() {
        wardrobe.addClothing(testClothing)
        assertEquals(1, wardrobe.searchByColorAndType("Red", ClothingType.SHIRT).size)
    }
}
