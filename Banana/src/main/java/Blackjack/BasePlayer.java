package Blackjack;

import java.util.ArrayList;
import java.util.List;

public class BasePlayer {


    private List<Deck.Card> currentHand = new ArrayList<>();

    private boolean busted;
    private boolean endedTurn;
    private int handValue;
    private int numberOfAces;
    String name;


    public void introduceThemselves(String name) {

        this.name = name;
    }



    public void addCardToHand(Deck.Card newCard) {
        currentHand.add(newCard);
    }

    public boolean isBusted() {
        return busted;
    }

    public void getBusted() {
        busted = true;
        System.out.println(name + " has busted. ");
    }

    public void unBust() {
        busted = false;
    }

    public int countCardsInHand() {
        return currentHand.size();
    }

    public  void resetPlayerHand() {
        currentHand.clear();
    }

    public void endPlayerTurn() {
        endedTurn = true;
    }

    public void resetPlayerTurn() {
        endedTurn = false;
    }
    public boolean playerHasEndedThierTurn() {
        return endedTurn;
    }

    public void showHand() {
        if (this.getClass() == Player.class){
            System.out.println();
            System.out.println(name + " has the cards...");
            currentHand.forEach(System.out::println);
            System.out.println(name + " has a value of " + getHandValue() + ".");
        }
    }
    public int getHandValue() {
        handValue = 0;
        numberOfAces = 0;
        valueOfNonAcesAndAmountOfAces();
        addAcesToHandValue();
        return handValue;
    }
    private void valueOfNonAcesAndAmountOfAces() {
        for (Deck.Card card : currentHand) {
            if (card.face != Deck.Face.ACE) {
                handValue += card.getCardValue();
            } else {
                numberOfAces++;
            }
        }
    }
    private void addAcesToHandValue() {
        for (int i = 0; i < numberOfAces; i++) {
            if (handValue + 10 + numberOfAces <= 21) {
                handValue += 10 + 1;
            } else {
                handValue++;
            }
        }
    }

}

