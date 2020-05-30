package utils

import models.Card

fun createDeck() {
    /**
     *
    '\u2663' - club
    '\u2660' - spade
    '\u2666' - diamond
    '\u2665’ - heart
     * */

    val suitArray = arrayOf('\u2663', '\u2660', '\u2666', '\u2665')
    val pipsArray = arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

    val deck = mutableSetOf<Card>()

    for (suit in suitArray) {
        for (pip in pipsArray) {

        }
    }
}