/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.calculator;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author Asange
 */
public class CalculatorTest {
    
 Calculator calc = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5.0, calc.calculate(2, 3, '+'));
    }

    @Test
    public void testSubtract() {
        assertEquals(3.0, calc.calculate(5, 2, '-'));
    }

    @Test
    public void testMultiply() {
        assertEquals(12.0, calc.calculate(4, 3, '*'));
    }

    @Test
    public void testDivide() {
        assertEquals(3.0, calc.calculate(6, 2, '/'));
    }

    @Test
    public void testDivideByZero() {
        assertEquals(0.0, calc.calculate(5, 0, '/'));
    }

    @Test
    public void testInvalidOperator() {
        assertEquals(0.0, calc.calculate(5, 5, '%'));
    }
}

