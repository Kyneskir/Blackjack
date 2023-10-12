import Blackjack.Deck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTests {
@Test
    public void testCardValue() {
        Deck.Card ace = new Deck.Card(Deck.Suit.SPADES, Deck.Face.ACE);
        Deck.Card two = new Deck.Card(Deck.Suit.SPADES, Deck.Face.TWO);
        Deck.Card three = new Deck.Card(Deck.Suit.SPADES, Deck.Face.THREE);
        Deck.Card four = new Deck.Card(Deck.Suit.SPADES, Deck.Face.FOUR);
        Deck.Card five = new Deck.Card(Deck.Suit.SPADES, Deck.Face.FIVE);
        Deck.Card six = new Deck.Card(Deck.Suit.SPADES, Deck.Face.SIX);
        Deck.Card seven = new Deck.Card(Deck.Suit.SPADES, Deck.Face.SEVEN);
        Deck.Card eight = new Deck.Card(Deck.Suit.SPADES, Deck.Face.EIGHT);
        Deck.Card nine = new Deck.Card(Deck.Suit.SPADES, Deck.Face.NINE);
        Deck.Card ten = new Deck.Card(Deck.Suit.SPADES, Deck.Face.TEN);
        Deck.Card jack = new Deck.Card(Deck.Suit.SPADES, Deck.Face.JACK);
        Deck.Card queen = new Deck.Card(Deck.Suit.SPADES, Deck.Face.QUEEN);
        Deck.Card king = new Deck.Card(Deck.Suit.SPADES, Deck.Face.KING);

        assertEquals("This yo", 1, ace.getCardValue());
        assertEquals("This yo", 2, two.getCardValue());
        assertEquals("This yo", 3, three.getCardValue());
        assertEquals("This yo", 4, four.getCardValue());
        assertEquals("This yo", 5, five.getCardValue());
        assertEquals("This yo", 6, six.getCardValue());
        assertEquals("This yo", 7, seven.getCardValue());
        assertEquals("This yo", 8, eight.getCardValue());
        assertEquals("This yo", 9, nine.getCardValue());
        assertEquals("This yo", 10, ten.getCardValue());
        assertEquals("This yo", 10, jack.getCardValue());
        assertEquals("This yo", 10, queen.getCardValue());
        assertEquals("This yo", 10, king.getCardValue());

    }
}
