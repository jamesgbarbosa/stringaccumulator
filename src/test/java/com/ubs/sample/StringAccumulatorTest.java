package com.ubs.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class StringAccumulatorTest
{
    StringAccumulator stringAccumulator;

    @Before
    public void init() {
         stringAccumulator = new StringAccumulator();
    }

    @Test
    public void stringAccumilatorAddTest() throws NegativeInputException  {
        // Should ignore numbers greater than 1000
        String str = "//$!\n1003$!1$!2";
        int numbers = stringAccumulator.processString(str);
        assertTrue(numbers == 3);
    }

    @Test
    public void stringAccumilatorAdd2Test() throws NegativeInputException  {
        String str = "//***2***3***5";
        int numbers = stringAccumulator.processString(str);
        assertTrue(numbers == 10);
    }

    @Test
    public void stringAccumilatorMultipleDelimeterTest() throws NegativeInputException  {
        String str = "//!|@|#|$|%\n1@2!3$\n\t\n4";
        int numbers = stringAccumulator.processString(str);
        assertTrue(numbers == 10);
    }

    @Test(expected = NegativeInputException.class)
    public void stringAccumulatorNegativeInputAddTest() throws NegativeInputException  {
        // Should ignore numbers greater than 1000
        String str = "//$!\n1003$!1$!-2";
        stringAccumulator.processString(str);
    }

    @Test
    public void removeAllTabsAndNewLinesTest()  {
        // Should ignore numbers greater than 1000
        String input = "\n1\t\n\n2\n\n3";
        String numbers = stringAccumulator.removeAllTabsAndNewLines(input);
        assertTrue(numbers.equals("123"));
    }

    @Test
    public void getAllNumbersTest()  {
        // Should ignore numbers greater than 1000
        String input = "1000,2,3,4,2000,3000";
        String delimeter = ",";
        List<Integer> numbers = stringAccumulator.getAllNumbers(input, delimeter);
        assertEquals(numbers.size(), 4);
        assertTrue(numbers.get(0) == 1000);
    }

    @Test
    public void getAllNumbersNoInputTest()  {
        // Should ignore numbers greater than 1000
        String input = "";
        String delimeter = ",";
        List<Integer> numbers = stringAccumulator.getAllNumbers(input, delimeter);
        assertEquals(numbers.size(), 0);
    }

    @Test
    public void extractDelimeterFromInputTest()  {
        // Should ignore numbers greater than 1000
        String input = "//*|*";
        String delimeter = stringAccumulator.getCustomDelimeter(input);
        assertEquals(delimeter, "*|*");
    }

    @Test
    public void extractDelimeterFromInputWithoutDelimeterTest()  {
        // Should ignore numbers greater than 1000
        String input = "1,2,5,7";
        String delimeter = stringAccumulator.getCustomDelimeter(input);
        assertEquals(delimeter, "");
    }

    @Test
    public void wrapMultiDelimeterInput() {
        String input = "//*|&";
        String result = stringAccumulator.wrapDelimeter(input);
        assertEquals(result, "[//*|&]");
    }

    @Test
    public void wrapDelimeterInput() {
        String input = "//^%$#";
        String result = stringAccumulator.wrapDelimeter(input);
        assertEquals(result, "\\Q//^%$#\\E");
    }
}
