package com.safaricom.tdd_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SummationUnitTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
            1,2,3,
            8,7,15
            9,-3,6
            -4,-3,-7
            """)
    public void test_sum(int num1, int num2, int output){
       //Arrange
        Summation summation = new Summation();
        //Execute
       int sum= summation.sum(num1,num2);
        //Verify
        Assertions.assertEquals(sum,output);
    }
}
