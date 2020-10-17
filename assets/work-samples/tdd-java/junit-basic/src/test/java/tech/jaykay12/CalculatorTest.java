package tech.jaykay12;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    static Calculator calculator = null;

    @BeforeAll
    public static void initialiser(){
        calculator = new Calculator();
    }

    @Test
    public void Adder2Words(){
        Assertions.assertEquals(4, calculator.adder(2, 2));
    }

    @Test
    public void Adder3Words(){
        Assertions.assertEquals(0, calculator.adder(-1,2,-1));
    }

    @AfterAll
    public static void cleaner(){
        calculator = null;
    }
}
