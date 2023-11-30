package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClothingTest {

    @Test
    fun `test default values`() {
        val clothing = Clothing()
        assertEquals(-1, clothing.id)
        assertEquals(ClothingType.UNKNOWN, clothing.type)
        assertEquals("Unknown", clothing.brand)
        assertEquals("Unknown", clothing.name)
        assertEquals("Unknown", clothing.color)
        assertEquals("Unknown", clothing.texture)
    }

    @Test
    fun `test parameterized constructor`() {
        val clothing = Clothing(1, ClothingType.JUMPER, "Nike", "Nike Jumper", "blue", "cotton")
        assertEquals(1, clothing.id)
        assertEquals(ClothingType.JUMPER, clothing.type)
        assertEquals("Nike", clothing.brand)
        assertEquals("Nike Jumper", clothing.name)
        assertEquals("blue", clothing.color)
        assertEquals("cotton", clothing.texture)
    }

    @Test
    fun `test toString format`() {
        val clothing = Clothing(1, ClothingType.JUMPER, "Nike", "Nike Jumper", "blue", "cotton")
        val expectedString = "Clothing Details:\nID: 1\nType: JUMPER\nBrand: Nike\nName: Nike Jumper\nColor: blue\nTexture: cotton\n"
        assertEquals(expectedString, clothing.toString())
    }
}
