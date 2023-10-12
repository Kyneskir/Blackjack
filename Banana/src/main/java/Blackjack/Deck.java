package Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> listOfRemainingCards;

    private Card[] fullDeck;

    private int numberOfCardsDrawn = 0;
    public Deck() {

        listOfRemainingCards = createNewDeckList();
        shuffleRemainingCards();
    }

    public int remainingCardCount() {
        return listOfRemainingCards.size();
    }
    public Card drawRandomCard() throws IndexOutOfBoundsException {
        int randomNumber = new Random().nextInt(listOfRemainingCards.size() - 1);

        Card returnThisCard = listOfRemainingCards.get(randomNumber);
        listOfRemainingCards.remove(randomNumber);
        return returnThisCard;

    }

    public void shuffleRemainingCards() {

        Collections.shuffle(listOfRemainingCards);
    }

    public Card drawTopCard() throws IndexOutOfBoundsException {

        Card returnThisCard = listOfRemainingCards.get(0);
        listOfRemainingCards.remove(0);
        return returnThisCard;
    }

    public void fullDeckShuffle() {
        listOfRemainingCards.clear();
        fillListOfCards(listOfRemainingCards);
        shuffleRemainingCards();

    }

    public void remainingCardsShuffle() {

    }

//    private Card[] createNewDeckArray() {
//        Card[] deck = new Card[52];
//        int iterator = 0;
//
//        for (Suit cardSuit: Suit.values()) {
//            for (Face cardFace: Face.values()) {
//                deck[iterator] = new Card(cardSuit, cardFace);
//                iterator++;
//            }
//        }
//
//        return deck;
    //}

    private List<Card> createNewDeckList() {
        List<Card> cardList = new ArrayList<>();
        fillListOfCards(cardList);
        return cardList;

        }

        private void fillListOfCards(List<Card> cardList) {
            for (Suit cardSuit: Suit.values()) {
                for (Face cardFace : Face.values()) {
                    Card card = new Card(cardSuit, cardFace);
                    cardList.add(card);
                    System.out.println(card.face.toString() + "of" + card.suit.toString());
                }
            }
    }
    public static class Card {
        int cardValue;
        Suit suit;
        Face face;

        @Override
        public String toString() {
            return face.toString() + " of " + suit.toString();
        }

        public Card(Suit suit, Face face) {
            this.cardValue = cardValue;
            this.suit = suit;
            this.face = face;
        }

        public int getCardValue() {
            return switch (face) {
                case TWO -> 2;
                case THREE -> 3;
                case FOUR -> 4;
                case FIVE -> 5;
                case SIX -> 6;
                case SEVEN -> 7;
                case EIGHT -> 8;
                case NINE -> 9;
                case TEN, JACK, QUEEN, KING -> 10;
                default -> 1; //Ace's value determined in BasePlayer getHandValue as it needs to know the current hand value (infinite loop if done here)
            };
        }

        public int getAceValue(int playerHandValue) {
            return playerHandValue > 21 ? 1 : 11;

        }
    }

    public enum Suit {
        HEARTS, CLUBS, SPADES, DIAMONDS
    }

    public enum Face {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
}

