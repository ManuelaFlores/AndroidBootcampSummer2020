import utils.*

fun main() {
    val globalDeck = createDeck()

    val hand = dealHand(globalDeck.toMutableList())
    printResults(hand = hand, totalSumOfHand = evaluateHand(hand))
}