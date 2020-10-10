package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader readFromConsole = new BufferedReader(new InputStreamReader(System.in));
        String inputData = readFromConsole.readLine();

        // находим позицию операнда
        int operandPosition = findOperand(inputData);
        if (operandPosition == -1) {
            System.out.println("Не найден операнд в строке или количество операндов больше 1");
            return;
        }

        Number numberOne = new Number(inputData.substring(0, operandPosition));
        Number numberTwo = new Number(inputData.substring(operandPosition + 1));

        if (numberOne.getNumberType() == numberTwo.getNumberType()) {
            if (numberOne.getNumberType() == Number.NumberType.arabic) {
                int result = calculate(numberOne.getValue(), numberTwo.getValue(), inputData.charAt(operandPosition));
                System.out.println(result);
            } else if (numberOne.getNumberType() == Number.NumberType.roman) {
                int result = calculate(numberOne.getValue(), numberTwo.getValue(), inputData.charAt(operandPosition));
                String sResult = arabicToRoman(result);
                System.out.println(sResult);
            }
        } else if (numberOne.getNumberType() == Number.NumberType.invalid
                || numberTwo.getNumberType() == Number.NumberType.invalid) {
            System.out.println("не верные входные данные");
        } else if (numberOne.getNumberType() != numberTwo.getNumberType()) {
            System.out.println("Одно число арабское, а другое - римское");
        }
    }

    private static int findOperand(String inputData) {
        int position = 0;
        int count = 0;

        for(int i = 0; i < inputData.length(); i++) {
            char c = inputData.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                count++;
                position = i;
            }
        }

        if(count == 1) {
            return position;
        } else {
            return -1;
        }
    }

    private static int calculate(int firstValue, int secondValue, char operand) {
        switch (operand) {
            case '+':
                return firstValue + secondValue;
            case '-':
                return firstValue - secondValue;
            case '/':
                return Math.round(firstValue / secondValue);
            case '*':
                return firstValue * secondValue;
        }
        return Integer.MIN_VALUE;
    }

    private static String arabicToRoman(int input) {
        //максимальное значение может быть 100 (10*10)

        String result = "";
        while (input != 0) {
            if(input >= 100) {
                input -= 100;
                result += 'C';
            } else if (input >= 50) {
                input -= 50;
                result += 'L';
            } else if (input >= 9) {
                input -= 10;
                result += 'X';
            } else if (input >= 4) {
                input -= 5;
                result += "V";
            } else if (input >= 1) {
                input -= 1;
                result += "I";
            } else {
                input += 1;
                result = result.substring(0, result.length() - 1) + "I" + result.substring(result.length() - 1);
            }
        }
        return result;
    }
}
