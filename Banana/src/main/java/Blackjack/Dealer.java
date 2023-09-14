package Blackjack;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends BasePlayer {

    private Deck dealerDeck;

    private Deck.Card hiddenCard;

    private Map<Player, Integer> playerBetMap = new HashMap<>();


    public Dealer(Deck dealerDeck) {
        name = "Dealer";
        this.dealerDeck = dealerDeck;
    }
    public void clearPlayerBets() {
        playerBetMap.clear();
    }

    public void dealCards(BasePlayer player) {
        Deck.Card dealtCard = tryToDealCard(player);
        System.out.println(player.name + " was dealt the card: " + dealtCard.face.toString() + " of " + dealtCard.suit.toString());
    }

    public void reserveCard() {
        hiddenCard = tryToDealCard(GameActions.dealer);
        System.out.println(GameActions.dealer.name + " sets a card aside");
    }

    public void revealCard() {
        System.out.println(GameActions.dealer.name + "'s reserved card was " + hiddenCard.toString());
        System.out.println(GameActions.dealer.name + "'s starting hand value is " + this.getHandValue());
    }

    private Deck.Card tryToDealCard(BasePlayer player) {
        Deck.Card dealtCard;
        try {
            dealtCard = dealerDeck.drawTopCard();
            player.addCardToHand(dealtCard);
        } catch (IndexOutOfBoundsException e) {
            reShuffleDeck();
            dealtCard = dealerDeck.drawTopCard();
            player.addCardToHand(dealtCard);
        }
        return dealtCard;
    }

    public void reShuffleDeck() {
        dealerDeck.fullDeckShuffle();
    }

    public void collectBets(Player bettingPlayer, int betAmount) {
        playerBetMap.put(bettingPlayer, betAmount);

    }

    public int getPlayerBet(Player winningPlayer) {
        return playerBetMap.get(winningPlayer);
    }



}
