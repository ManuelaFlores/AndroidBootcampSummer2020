import utils.*

fun main() {
    val globalDeck = createDeck().toMutableList()

    val hand = dealHand(globalDeck)

    val totalSumOfHand = evaluateHand(hand)

    printResults(hand = hand, totalSumOfHand = totalSumOfHand)
    if (totalSumOfHand < 20) {
        val handUpdated = hand.toMutableList()
        askForAnotherCard(deck = globalDeck, hand = handUpdated)
    }
}