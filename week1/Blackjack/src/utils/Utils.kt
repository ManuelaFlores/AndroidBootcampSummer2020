package utils

import models.Card
import kotlin.text.StringBuilder

fun createDeck(): MutableSet<Card> {
    val suitArray = arrayOf('\u2663', '\u2660', '\u2666', '\u2665')
    val pipsArray = arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

    val deck = mutableSetOf<Card>()

    for (suit in suitArray) {
        for (pip in pipsArray) {
            deck.add(Card(pip, suit))
        }
    }
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

fun getOneMoreCard(deck: MutableList<Card>, hand: MutableList<Card>): List<Card> {
    deck.shuffle()
    hand.add(deck.random())
    return hand
}

fun evaluateHand(hand: List<Card>): Int {
    var totalSumOfHand = 0

    for (handValue in hand) {
        totalSumOfHand += when (handValue.pip) {
            "A" -> 11
            "J", "Q", "K" -> 10
            else -> handValue.pip.toInt()
        }
    }
    return totalSumOfHand
}

fun printResults(hand: List<Card>, totalSumOfHand: Int) {
    var message = "Your hand was:"

    for (handValue in hand) {
        message += "\n ${handValue.pip} ${assignColorsToSuits(handValue.suit)}"
    }
    message += "\n For a total of $totalSumOfHand"

    message += when {
        totalSumOfHand == 21 -> "\n YOU WIN! :D "
        totalSumOfHand > 21 -> "\n ${formatTextToRed("You lost the game.")}"
        else -> "\n Maybe next time :|"
    }
    println(message)
}

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

fun askForAnotherCard(deck: MutableList<Card>, hand: MutableList<Card>) {
    println("------***------")
    println("Would you like to take one more card?")
    println("Type 'y' if you want or 'n' if you don't")
    readUserInput(readLine() ?: "", deck, hand)
}

fun readUserInput(input: String, deck: MutableList<Card>, hand: MutableList<Card>) {
    when (input) {
        "y" -> {
            val handUpdated = getOneMoreCard(deck, hand)
            val totalSumOfHandUpdated = evaluateHand(hand)
            printResults(hand = handUpdated, totalSumOfHand = totalSumOfHandUpdated)
        }
        "n" -> println("Thanks for trying this game :)!")
        else -> println("I'm sorry, I can't understand your answer :(")
    }
}



