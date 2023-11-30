package utils

import mu.KotlinLogging

/**
 * Utility object for logging purposes.
 * Utilizes [KotlinLogging] to provide a simple logging mechanism.
 *
 * This object can be used throughout the application to log information messages.
 * It encapsulates the logging implementation, offering a centralized logging solution.
 */
object LoggerUtil {
    private val logger = KotlinLogging.logger {}

    /**
     * Logs the provided prompt as an information message.
     * This function can be used to log general information, such as the progress or state of the application.
     *
     * @param prompt The message to be logged. This should contain the information to be conveyed in the log.
     */
    fun printLogger(prompt: String) {
        logger.info { prompt }
    }
}
