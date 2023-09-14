package Blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerInputs {

    public static int takePlayerBet(Player bettingPlayer) {

        System.out.println(bettingPlayer.name + " should input a number for a bet now. Don't go broke. ");
        int desiredBet = ensureInputIsNumber(getInputFromTerminal());
        while (checkAgainstMinimumBet(desiredBet) || checkAgainstPlayerFunds(desiredBet, bettingPlayer)) {
            desiredBet = ensureInputIsNumber(getInputFromTerminal());
            //TODO make it so you cant keep going
        }
        System.out.println(bettingPlayer.name + " has chosen to bet: " + desiredBet);
        return desiredBet;

    }
    private static boolean checkAgainstMinimumBet(int desiredBet) {
        if (desiredBet < Blackjack.MINIMUM_BET) {
            System.out.println("The table has a minimum bet of " + Blackjack.MINIMUM_BET + ". Do try and bet more next time");
            return true;
        }
        return false;
    }

    private static boolean checkAgainstPlayerFunds(int desiredBet, Player bettingPlayer) {
        if (desiredBet > bettingPlayer.getFunds()) {
            System.out.println("eRRoR, Not a Number you can play");
            return true;
        }
        return false;
    }
    private static String getInputFromTerminal() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Could not find what you're looking for. Whoops");
            return "";
        }

    }

    private static int ensureInputIsNumber(String inputFromTerminal) {
        try {
            return Integer.parseInt(inputFromTerminal);
        } catch (NumberFormatException e) {
            System.out.println("Hey dumbass. It should be a number. If you fuck this up");
            try {
                return Integer.parseInt(inputFromTerminal);
            }catch (NumberFormatException e2) {
                return 0;
            }
        }
    }

    public static GameActions.PlayerAction takePlayerAction(Player actingPlayer) {

        System.out.println(actingPlayer.name + " would you like to Hit, Doubledown or Stand?");

        while (true) {

            switch (getInputFromTerminal().toLowerCase()) {
                case "hit":
                    return GameActions.PlayerAction.HIT;
                case "stand":
                    return GameActions.PlayerAction.STAND;
                case "doubledown":
                    return GameActions.PlayerAction.DOUBLE_DOWN;
                default:
                    System.out.println("enter appropriate variables");
                    break;

            }
        }
    }

    public static GameActions.GameAction takeGameResolution() {

        System.out.println(" Do you want to play another game? Yes / No ");

        while (true) {

            switch (getInputFromTerminal().toLowerCase()) {
                case "yes":
                    return GameActions.GameAction.RESTART_GAME;
                case "no":
                    return GameActions.GameAction.END_GAME;
                default:
                    System.out.println("enter appropriate variables");
                    break;

            }
        }

    }

    public static int getPlayerReBuyIn(Player player, int minimumBet) {
        System.out.println("How much would you like to add to your wallet? Leave blank to quit.");
                while (true) {
                    String playerInput = getInputFromTerminal();
                    try {
                        Integer.parseInt(playerInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Too big bitch. Try again? The table wont accept more than" + Blackjack.TABLE_MAX + " anyway.");
                        continue;
                    }
                    if (playerInput.equals("")) {
                        return 0;
                    }
                    try {
                        int reBuyInAmount = Integer.parseInt(playerInput);
                        if (reBuyInAmount + player.getFunds() > minimumBet) {
                            System.out.println("making good choices are we?");
                            return reBuyInAmount;
                        }else {
                            System.out.println("You need to have more than the min bet");
                        }

                    }catch (NumberFormatException e) {
                        System.out.println("that wasnt a number");
                                return 0;
                    }
                }
    }


}
