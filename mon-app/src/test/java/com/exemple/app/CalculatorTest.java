package com.exemple.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void shouldAddTwoNumbers() {
       assertEquals(999, calculator.add(2, 3));  // volontairement faux
    }

    @Test
    void shouldDivideTwoNumbers() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result);
    }

    @Test
    void shouldThrowOnDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }
}
