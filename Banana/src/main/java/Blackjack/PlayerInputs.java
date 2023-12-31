package Blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ConcurrentModificationException;

public class PlayerInputs {

    public static int takePlayerBet(Player bettingPlayer) {

        System.out.println(bettingPlayer.name + " should input a number for a bet now. Don't go broke. ");
        int desiredBet = ensureInputIsNumber(getInputFromTerminal()); // is desired bet a number?
        int attempts = 0;
        while (checkAgainstMinimumBet(desiredBet) || checkAgainstPlayerFunds(desiredBet, bettingPlayer)) { // is desired bet higher than min bet and does the player have the funds?
            if (attempts > 2) {
                System.out.println("Hey dumbass. It should be a number. If you fuck this up");
                return bettingPlayer.getFunds();
            }
            desiredBet = ensureInputIsNumber(getInputFromTerminal()); // ask again cuz input is not correct.
            attempts++;
        }

        System.out.println(bettingPlayer.name + " has chosen to bet: " + desiredBet);
        return desiredBet; // return what they asked for because it passes both stipulations.

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

    protected static int ensureInputIsNumber(String inputFromTerminal) {
        try {
            return Integer.parseInt(inputFromTerminal);
        } catch (NumberFormatException e) {
            try {
                System.out.println("Not a number");
                return Integer.parseInt(getInputFromTerminal());
            }catch (NumberFormatException ee) {
                System.out.println("Not a number");
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

    public static int getPlayerReBuyIn(Player player) {
        System.out.println("How much would you like to add to your wallet? Enter 0 to quit.");
                while (true) {
                    int playerInput = ensureInputIsNumber(getInputFromTerminal());
                    if (playerInput == 0) {
                        return playerInput;
                    }
                        if (playerInput + player.getFunds() > Blackjack.MINIMUM_BET) {
                            System.out.println("making good choices are we?");
                            return playerInput;
                        }else {
                            getPlayerReBuyInElseLoopOccurred = true;
                            System.out.println("You need to have more than the min bet");
                        }

                    }
                }

                static boolean getPlayerReBuyInElseLoopOccurred = false;
    protected static boolean didGetPlayerReBuyInElseLoopOccur() {
        return getPlayerReBuyInElseLoopOccurred;
    }
    }



