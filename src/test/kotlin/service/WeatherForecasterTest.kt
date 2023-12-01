package service

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WeatherForecasterTest {

    @Test
    fun `willRainToday returns false when no rain is forecasted`() {
        val noRainData = """
    {
        "hourly": [
            {"weather": [{"id": 800}]},
            {"weather": [{"id": 801}]},
            {"weather": [{"id": 802}]},
            {"weather": [{"id": 803}]},
            {"weather": [{"id": 804}]},
            {"weather": [{"id": 805}]},
            {"weather": [{"id": 806}]},
            {"weather": [{"id": 807}]},
            {"weather": [{"id": 808}]},
            {"weather": [{"id": 809}]},
            {"weather": [{"id": 810}]},
            {"weather": [{"id": 811}]}
        ]
    }
        """.trimIndent()

        assertFalse(WeatherForecaster.willRainToday(noRainData))
    }

    @Test
    fun `willRainToday returns true when rain is forecasted`() {
        val rainData = """
    {
        "hourly": [
            {"weather": [{"id": 500}]},
            {"weather": [{"id": 800}]},
            {"weather": [{"id": 801}]},
            {"weather": [{"id": 802}]},
            {"weather": [{"id": 803}]},
            {"weather": [{"id": 804}]},
            {"weather": [{"id": 805}]},
            {"weather": [{"id": 806}]},
            {"weather": [{"id": 807}]},
            {"weather": [{"id": 808}]},
            {"weather": [{"id": 809}]},
            {"weather": [{"id": 810}]}
        ]
    }
        """.trimIndent()

        assertTrue(WeatherForecaster.willRainToday(rainData))
    }

    @Test
    fun `willRainToday returns false when hourly data is empty`() {
        val emptyData = """{"hourly": []}"""

        assertFalse(WeatherForecaster.willRainToday(emptyData))
    }

    @Test
    fun `willRainToday returns false when hourly data is missing`() {
        val missingData = "{}"

        assertFalse(WeatherForecaster.willRainToday(missingData))
    }
}
