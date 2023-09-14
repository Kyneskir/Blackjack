package Blackjack;

import java.util.List;

public class Player extends BasePlayer {

    private int funds;
    private List<Deck.Card> splitHandOne;
    private List<Deck.Card> splitHandTwo;

    public Player(int personalFunds) {
        funds = personalFunds;
    }

    public boolean hasMoreThanMinimumBet(int minimumBet) {
        return funds >= minimumBet;
    }

    public void placeBet(int desiredBet) {
        funds -= desiredBet;
    }

    public void addFundsToPlayer(int winnings) {
        funds += winnings;
    }

    public int getFunds() {
        return funds;
    }

//    public void splitPlayerHand() {
//        List<Deck.Card> currentHand = showHand();
//        splitHandOne = new ArrayList<>();
//        splitHandOne.add(currentHand.get(0));
//        splitHandTwo = new ArrayList<>();
//        splitHandTwo.add(currentHand.get(0));
//
//    }
}
