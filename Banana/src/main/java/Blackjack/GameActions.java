package Blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameActions {

    static Dealer dealer = new Dealer(new Deck());
    static List<Player> playerList;

    private GameActions(List<Player> players) {
        playerList = players;
    }

    public static void hit(BasePlayer hittingPlayer) {
        dealer.dealCards(hittingPlayer);
        System.out.println(hittingPlayer.name + "'s new hand value is: " + hittingPlayer.getHandValue());
        if (hittingPlayer.getHandValue() > 21) {
            hittingPlayer.getBusted();
        }
    }

    public static void initialDeal(List<Player> gameParticipants) {
        System.out.println(" \n A new round begins \n");
        for (Player player: gameParticipants) {
            dealer.dealCards(player);
            dealer.dealCards(player);
            System.out.println(player.name + "'s starting hand is: " + player.getHandValue());
        }
        dealer.dealCards(dealer);
        dealer.reserveCard();
    }

    public static void dealersLogic() {
        dealer.revealCard();
        while (dealer.getHandValue() < 17) {
            hit(dealer);
        }
        if (!dealer.isBusted()) {
            stand(dealer);
        }
    }
//note no standing after bust!!!!
    public static void stand(BasePlayer player) {
        player.endPlayerTurn();
        System.out.println(player.name + " has stood. ");
    }

    public static void bust(Player player) {
        player.getBusted();
    }

    public static void doubleDown(Player player) {
        bet(player, dealer.getPlayerBet(player));
        hit(player);
        player.endPlayerTurn();
    }

    public static void split(Player splittingPlayer) {

    }

    public static void initialBet(List<Player> gameParticipants, List<Integer> playerBets) {
        for (int i = 0; i < gameParticipants.size(); i++) {
            bet(gameParticipants.get(i), playerBets.get(i));
            System.out.println(gameParticipants.get(i).name + "'s starting bet is: " + playerBets.get(i));
        }
    }

    public static void bet(Player bettingPlayer, int betAmount) {// todo fix doubledown tie not giving all the money bet back
        bettingPlayer.placeBet(betAmount);
        try {
            dealer.collectBets(bettingPlayer, dealer.getPlayerBet(bettingPlayer) + betAmount);
        }catch (NullPointerException e) {
            dealer.collectBets(bettingPlayer, betAmount);
        }

    }

    private static int payWinner(Player winningPlayer) {
        int amountToBePaid = dealer.getPlayerBet(winningPlayer) * 2;
        winningPlayer.addFundsToPlayer(amountToBePaid);
        return amountToBePaid;
    }
    private static void refundPlayerBecauseOfTie(Player tiedPlayer) {
        int amountToBePaid = dealer.getPlayerBet(tiedPlayer);
        tiedPlayer.addFundsToPlayer(amountToBePaid);
    }

    public static void  playerWon(Player winningPlayer) {
        int amountToBePaid = payWinner(winningPlayer);
        System.out.println(winningPlayer.name + " has beat the dealer, here are your winnings! $" + amountToBePaid);
    }
    public static void  playerTied(Player winningPlayer) {
        refundPlayerBecauseOfTie(winningPlayer);
        System.out.println(winningPlayer.name + " has tied the dealer, thier funds will be returned! $");
    }
    public enum PlayerAction {
        HIT, STAND, DOUBLE_DOWN, SPLIT
    }

    public enum GameAction {
        RESTART_GAME, END_GAME
    }

    public static void resetPlayerValues(List<Player> players) {
        List<BasePlayer> allPlayers = new ArrayList<>(players);
        allPlayers.add(dealer);
        for (BasePlayer basePlayer: allPlayers) {
            resetPlayerVariables(basePlayer);
        }
        dealer.clearPlayerBets();
    }
    private static void resetPlayerVariables(BasePlayer player) {
        player.unBust();
        player.resetPlayerTurn();
        player.resetPlayerHand();

    }

    public static boolean checkIfPlayerCanContinue(Player player, int minimumBet) {
        return player.getFunds() >= minimumBet;
    }
}
