
package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAddition() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testSubtraction() {
        int result = calculator.subtract(4, 3);
        assertEquals(1, result);
    }
}
 