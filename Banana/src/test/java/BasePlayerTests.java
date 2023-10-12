import Blackjack.BasePlayer;
import Blackjack.Deck;
import Blackjack.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BasePlayerTests {

    @Test
    public void testGetHandValueNoAces() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.TWO));
        assertEquals("Is this test passing?", 7, player.getHandValue());
    }
    @Test
    public void testGetHandValueOneAceBelowTwentyOne() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.ACE));
        assertEquals("Is this test passing?", 16, player.getHandValue());
    }
    @Test
    public void testGetHandValueOneAceAboveTwentyOne() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.KING));
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.ACE));
        assertEquals("Is this test passing?", 16, player.getHandValue());
    }
    @Test
    public void testGetHandValueTwoAceBelowTwentyOne() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.ACE));
        player.addCardToHand(new Deck.Card(Deck.Suit.SPADES, Deck.Face.ACE));
        assertEquals("Is this test passing?", 17, player.getHandValue());
    }
}
