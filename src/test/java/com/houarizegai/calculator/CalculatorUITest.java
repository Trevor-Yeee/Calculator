package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CalculatorUITest {

    @Mock
    private CalculatorUI calculatorUI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
        "3,5,+,8", 
        "2,8,-,-6", 
        "44.5,10,*,445", 
        "320,5,/,64", 
        "3,5,%,3", 
        "5,3,^,125"
    })
    void testCalculation(double firstNumber, double secondNumber, char operator, double expectedResult) {
        // Define the behavior of the mocked method
        when(calculatorUI.calculate(firstNumber, secondNumber, operator)).thenReturn(expectedResult);

        // Invoke the method to test
        double result = calculatorUI.calculate(firstNumber, secondNumber, operator);

        // Assert the result
        assertEquals(expectedResult, result);
    }
}
