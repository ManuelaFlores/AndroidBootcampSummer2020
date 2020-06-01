package utils

import kotlin.text.StringBuilder

fun formatTextToRed(text: String) = StringBuilder("\u001b[31m$text")

fun assignColorsToSuits(char: Char): StringBuilder {
    return if (char == '\u2665' || char == '\u2666') {
        StringBuilder(
            "${27.toChar()}[31m$char${27.toChar()}[0m"
        )
    } else {
        StringBuilder(
            "${27.toChar()}[32m$char${27.toChar()}[0m"
        )
    }
}



