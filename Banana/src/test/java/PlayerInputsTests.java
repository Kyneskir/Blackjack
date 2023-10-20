import Blackjack.GameActions;
import Blackjack.Player;
import Blackjack.PlayerInputs;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class PlayerInputsTests extends PlayerInputs {

    static InputStream originalSystemIn;


    @BeforeClass
    public static void runBeforeTest() {
        originalSystemIn = System.in;
    }

    @After
    public void runAfterTest() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testTakeGameResolution() {

        prepTerminal("yes");
        assertEquals(GameActions.GameAction.RESTART_GAME, PlayerInputs.takeGameResolution());

        prepTerminal("no");
        assertEquals(GameActions.GameAction.END_GAME, PlayerInputs.takeGameResolution());

    }

    @Test
    public void testEnsureInputIsNumber() {
        assertEquals(123, ensureInputIsNumber("123"));

        System.setIn(new ByteArrayInputStream("no".getBytes()));
        assertEquals(0, ensureInputIsNumber("not a number"));

        System.setIn(new ByteArrayInputStream("1234".getBytes()));
        assertEquals(1234, ensureInputIsNumber(""));

    }

    @Test
    public void testGetPlayerReBuyIn() {
        Player testPlayer = new Player(0);

        prepTerminal("1");
        getPlayerReBuyIn(testPlayer);
        assertTrue(didGetPlayerReBuyInElseLoopOccur());

        prepTerminal("0");
        assertEquals(0, getPlayerReBuyIn(testPlayer));

        prepTerminal("50");
        assertEquals(50, getPlayerReBuyIn(testPlayer));


    }

    private void prepTerminal(String terminalEntry) {
        InputStream noFromTerminal = new ByteArrayInputStream(terminalEntry.getBytes());
        System.setIn(noFromTerminal);
    }
    @Test
    public void testTakePlayerAction() {

        Player testPlayer = new Player(100);

        prepTerminal("HIT");
        assertEquals(GameActions.PlayerAction.HIT, PlayerInputs.takePlayerAction(testPlayer));

        prepTerminal("stAnD");
        assertEquals(GameActions.PlayerAction.STAND, PlayerInputs.takePlayerAction(testPlayer));

        prepTerminal("DOUBLEdown");
        assertEquals(GameActions.PlayerAction.DOUBLE_DOWN, PlayerInputs.takePlayerAction(testPlayer));

        prepTerminal("incorrect answer");
        try {
            PlayerInputs.takePlayerAction(testPlayer);
            fail();
        }catch (NullPointerException e) {
            // test passed
        }
    }
    @Test
    public void testTakePlayerBet() {

        prepTerminal("0");
        assertEquals(100, takePlayerBet(new Player(100)));

        prepTerminal("1000");
        assertEquals(100, takePlayerBet(new Player(100)));

        prepTerminal("50");
        assertEquals(50, takePlayerBet(new Player(100)));
    }
}
