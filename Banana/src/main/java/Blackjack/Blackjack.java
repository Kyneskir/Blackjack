package Blackjack;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {

    public static final int MINIMUM_BET = 20;
    public static final int TABLE_MAX = 20000;

    //change 2
    // rules
// deck 13 x 4
// ace is 1 or 11
// closest to 21 without going over
// face cards are 10
// suits don't matter
// hit and stand
// bust
// bet
// double down
// dealer
// player(s)
//
    public static void main(String[] bananaBaby) {

        Player one = new Player(300);
        one.introduceThemselves("Bannananan");

        Player two = new Player(100);
        two.introduceThemselves("Potato");

        List<Player> gameParticipants = new ArrayList<>();
        gameParticipants.add(one);
        gameParticipants.add(two);

        boolean replayGame = true;

        while (replayGame) {
            GameActions.initialDeal(gameParticipants);

//        List<Integer> playerBets = new ArrayList<>();
//        for (int i = 0; i < gameParticipants.size(); i++) {
//            playerBets.add(PlayerInputs.takePlayerBet(gameParticipants.get(i)));
//        }

            for (Player player : gameParticipants) {
                boolean playerHasntBet = true;
                while (!player.isBusted() && !player.playerHasEndedThierTurn()) {
                    player.showHand();
                    System.out.println(player.name + "has" + player.getFunds());
                    if (playerHasntBet) {
                        GameActions.bet(player, PlayerInputs.takePlayerBet(player));
                        playerHasntBet = false;
                    }
                    switch (PlayerInputs.takePlayerAction(player)) {
                        case HIT -> GameActions.hit(player);
                        case STAND -> GameActions.stand(player);
                        case DOUBLE_DOWN -> {
                            if (player.countCardsInHand() == 2 && player.getFunds() >= GameActions.dealer.getPlayerBet(player)) {
                                GameActions.doubleDown(player);
                            } else {
                                System.out.println("number cards in hand" + player.countCardsInHand());
                                System.out.println("player funds " + player.getFunds());
                                System.out.println("dealer shows this as the bet so far " + GameActions.dealer.getPlayerBet(player));

                                System.out.println("You cant doubledown. Either you lack funds or you have ");
                            }
                        }
                    }

                    //TODO player choice
                }

            }

            GameActions.dealersLogic();

            for (Player player : gameParticipants) {
                if (!player.isBusted()) {
                    if (GameActions.dealer.isBusted()) {
                        GameActions.playerWon(player);
                    } else {
                        if (player.getHandValue() > GameActions.dealer.getHandValue()) {
                            GameActions.playerWon(player);
                        } else if (player.getHandValue() == GameActions.dealer.getHandValue()) {
                            GameActions.playerTied(player);
                        } else {
                            System.out.println(player.name + " fucking lost. It's time to take out a loan. ");
                            //player loses
                        } // note i need the payment!!!!
                    }
                }
            }

            switch (PlayerInputs.takeGameResolution()) {
                case END_GAME:
                    replayGame = false;
                    System.out.println("Game Over");
                    break;
                case RESTART_GAME:
                    for (Player player : gameParticipants) {
                        if (!GameActions.checkIfPlayerCanContinue(player, MINIMUM_BET)) {
                            System.out.println(player.name + " doesnt have enough money to continue. It's time to take out a loan. ");
                            int reBuyInAmount = PlayerInputs.getPlayerReBuyIn(player);
                            if (reBuyInAmount < MINIMUM_BET) {
                                gameParticipants.remove(player);
                            } else if (reBuyInAmount > TABLE_MAX) {
                                System.out.println(player.name + "you cant come to the table with more than" + TABLE_MAX);
                                System.out.println("so thats how much you have now!");
                                player.addFundsToPlayer(TABLE_MAX);
                            } else
                                player.addFundsToPlayer(reBuyInAmount);
                        }
                    }

            GameActions.resetPlayerValues(gameParticipants);
            break;
            default:
            }
        }

            //TODO add restart replay logic

//        Deck deck = new Deck();
//        deck.drawTopCard();
//        GameActions.hit();
        /*
        every player bets
         if they have money

        everyone is dealt cards
         one at a time
         both cards at a time
         dealers second card does not show

         in player order, player takes actions
          hit
          stand
          double down
            draw one last card, bet double
          split maybe
            player plays with 2 hands

         potential consequences of hit
          bust

         dealer actions
          hit
          stand/bust

         round over
          non busted players compare value against dealer
          if player is higher than dealer player wins
          if  player is lower they llose
          if tied bet is returned

         */
        }

    }


