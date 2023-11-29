package utils

import mu.KotlinLogging

object LoggerUtil {
    private val logger = KotlinLogging.logger {}

    fun printLogger(prompt: String){
        logger.info { prompt }
    }
}