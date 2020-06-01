package test

import createDeck
import evaluateHand
import models.Card
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BlackjackFunctionsTest {
    @Test
    fun `createDeckShouldReturnMutableSetOfCards`() {
        val expectedDeck = mutableSetOf(
            Card("A", '\u2663'), Card("2", '\u2663'), Card("3", '\u2663'), Card("4", '\u2663'),
            Card("5", '\u2663'), Card("6", '\u2663'), Card("7", '\u2663'), Card("8", '\u2663'),
            Card("9", '\u2663'), Card("10", '\u2663'), Card("J", '\u2663'), Card("Q", '\u2663'),
            Card("K", '\u2663'),
            Card("A", '\u2660'), Card("2", '\u2660'), Card("3", '\u2660'), Card("4", '\u2660'),
            Card("5", '\u2660'), Card("6", '\u2660'), Card("7", '\u2660'), Card("8", '\u2660'),
            Card("9", '\u2660'), Card("10", '\u2660'), Card("J", '\u2660'), Card("Q", '\u2660'),
            Card("K", '\u2660'),
            Card("A", '\u2666'), Card("2", '\u2666'), Card("3", '\u2666'), Card("4", '\u2666'),
            Card("5", '\u2666'), Card("6", '\u2666'), Card("7", '\u2666'), Card("8", '\u2666'),
            Card("9", '\u2666'), Card("10", '\u2666'), Card("J", '\u2666'), Card("Q", '\u2666'),
            Card("K", '\u2666'),
            Card("A", '\u2665'), Card("2", '\u2665'), Card("3", '\u2665'), Card("4", '\u2665'),
            Card("5", '\u2665'), Card("6", '\u2665'), Card("7", '\u2665'), Card("8", '\u2665'),
            Card("9", '\u2665'), Card("10", '\u2665'), Card("J", '\u2665'), Card("Q", '\u2665'),
            Card("K", '\u2665')
        )
        assertEquals(expectedDeck, createDeck())
    }

    @Test
    fun `evaluateHandShouldReturnNumber`() {
        val expectedHand = listOf(Card("2", '\u2665'), Card("Q", '\u2665'))
        assertEquals(12, evaluateHand(expectedHand))
    }
}