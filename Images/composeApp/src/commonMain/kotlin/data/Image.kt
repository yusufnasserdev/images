package data

import kotlinx.serialization.Serializable

/**
 * Represents the image storage unit
 *
 * Currently, this class might as well be a plain String called URL which will work fine
 * However, I believe that would be a bad practice, e.g. if an attribute needed to be added to the class,
 * it can be added easily with the current implementation.
 */

@Serializable
data class Image (
    val url: String
)