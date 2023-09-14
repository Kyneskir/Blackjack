package Calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class testingTesting {

    @Test
    public void testPerformOperationAddition() {
        Calculate calculator = new Calculate();

        assertEquals(3.0, calculator.performOperation("+", 1, 2), 0.0);

    }

    @Test
    public void testPerformOperationSubtract() {
        Calculate calculator = new Calculate();

        assertEquals(-1.0, calculator.performOperation("-", 1, 2), 0.0);
    }

    @Test
    public void testPerformOperationMultiply() {
        Calculate calculator = new Calculate();

        assertEquals(2.0, calculator.performOperation("*", 1, 2), 0.0);

    }

    @Test
    public void testPerformOperationDivide() {
        Calculate calculator = new Calculate();

        assertEquals(.5, calculator.performOperation("/", 1, 2), 0.0);

    }

    @Test
    public void testPerformOperationExponent() {
        Calculate calculator = new Calculate();

        assertEquals(4, calculator.performOperation("^", 2, 2), 0.0);

    }

    @Test
    public void testPerformOperationDefault() {
        Calculate calculator = new Calculate();

        try {
            calculator.performOperation("asf", 200,2222222);
            fail();
        } catch (NumberFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testUnderstandEquation() {
        Calculate calculator = new Calculate();
        calculator.understandEquation("1 ^ 3");
        assertEquals(2, calculator.numbers.size());
        assertEquals(1, calculator.operators.size());

        assertEquals(1, (double) calculator.numbers.get(0), 0.0);
        assertEquals(3, (double) calculator.numbers.get(1), 0.0);

        assertEquals("^", calculator.operators.get(0));

        calculator.understandEquation("2 + 7 + 2");
        assertEquals(3, calculator.numbers.size());
        assertEquals(2, calculator.operators.size());

    }
}

