/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculator;

/**
 *
 * @author Asange
 */
public class Calculator {

    public double calculate(double num1, double num2, char operator) {

        if (operator == '+') {
            return num1 + num2;
        }
        else if (operator == '-') {
            return num1 - num2;
        }
        else if (operator == '*') {
            return num1 * num2;
        }
        else if (operator == '/') {
            if (num2 == 0) {
                System.out.println("Error: Cannot divide by zero");
                return 0;
            }
            return num1 / num2;
        }
        else {
            System.out.println("Error: Invalid operator");
            return 0;
        }
    }
}
            

   

