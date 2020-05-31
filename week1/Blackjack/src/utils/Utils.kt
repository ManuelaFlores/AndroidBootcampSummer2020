package utils

import models.Card

fun createDeck(): MutableSet<Card> {
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
            deck.add(Card(pip, suit))
        }
    }
    println(deck)
    return deck
}

fun dealHand(deck: MutableList<Card>, handSize: Int = 2): List<Card> {

    val hand = mutableListOf<Card>()

    for (index in 0 until handSize) {
        val handValue = deck.random()
        hand.add(handValue)
        deck.remove(handValue)
    }

    return hand.toList()
}

fun evaluateHand(hand: List<Card>): Int {

    var totalSumOfHand = 0

    for (handValue in hand) {
        totalSumOfHand += when (handValue.pip) {
            "A"-> 11
            "J", "Q", "K" -> 10
            else -> handValue.pip.toInt()
        }
    }
    return totalSumOfHand
}



