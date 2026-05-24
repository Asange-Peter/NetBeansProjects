/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator;

/**
 *
 * @author Asange
 */
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.print("Enter first number: ");
        double num1 = input.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = input.nextDouble();

        System.out.print("Enter operator (+, -, *, /): ");
        char operator = input.next().charAt(0);

        double result = calc.calculate(num1, num2, operator);

        System.out.println("Result: " + result);
    }
}
        

