package com.company;

public class Number {
    enum NumberType {
        undefined, arabic, roman, invalid
    }

    public NumberType getNumberType() {
        return numberType;
    }

    public int getValue() {
        return value;
    }

    private NumberType numberType;
    private String inputData;
    private int value;

    public Number(String inputData) {
        numberType = NumberType.undefined;
        this.inputData = inputData;
        buildNumber();
    }

    private void buildNumber() {
        String sNumber = "";
        for (int i = 0; i < inputData.length(); i++) {
            char aChar = inputData.charAt(i);

            // нашли пробел идем на следующую итерацию
            if (aChar == ' ') {
                continue;
            }

            // поиск римской или арабской цифры, если есть смешивание римской и арабской цифр, то значение недействительное
            if (aChar == 'I' || aChar == 'V' || aChar == 'X') {
                if (numberType == NumberType.undefined) {
                    numberType = NumberType.roman;
                } else if (numberType == NumberType.arabic) {
                    System.out.println("В числе, которое начинается на арабскую цифру найдена римская");
                    numberType = NumberType.invalid;
                }
            } else if (Character.isDigit(aChar)) {
                if (numberType == NumberType.undefined) {
                    numberType = NumberType.arabic;
                } else if (numberType == NumberType.roman) {
                    System.out.println("В числе, которое начинается на римскую цифру найдена арабская");
                    numberType = NumberType.invalid;
                }
            } else {
                System.out.println("Число содержит символ не относящийся к арабской и римской цифрам");
                numberType = NumberType.invalid;
                return;
            }
            sNumber += aChar;
        }

        if (numberType == NumberType.roman) {
            setRomanNumber(sNumber);
        } else if (numberType == NumberType.arabic) {
            setArabicNumber(sNumber);
        } else {
            System.out.println("Не найдено арабских и римских цифр");
            numberType = NumberType.invalid;
        }
    }

    private void setRomanNumber(String sNumber) {
        switch (sNumber) {
            case "I":
                value = 1;
                break;
            case "II":
                value = 2;
                break;
            case "III":
                value = 3;
                break;
            case "IV":
                value = 4;
                break;
            case "V":
                value = 5;
                break;
            case "VI":
                value = 6;
                break;
            case "VII":
                value = 7;
                break;
            case "VIII":
                value = 8;
                break;
            case "IX":
                value = 9;
                break;
            case "X":
                value = 10;
                break;
            default:
                System.out.println("Римская цифра вне диапазона от 1 до 10 включительно " + sNumber);
                numberType = NumberType.invalid;
                break;
        }
    }

    private void setArabicNumber(String sNumber) {
        try {
            value = Integer.parseInt(sNumber);
            if (value < 0 || value > 10) {
                System.out.println("Арабская цифра вне диапазона от 1 до 10 включительно");
                numberType = NumberType.invalid;
            }
        } catch (NumberFormatException e) {
            numberType = NumberType.invalid;
            System.err.println("Неверный формат подстроки: " + inputData);
        }
    }
}

// римские цифры I II III IV V VI VII VIII IX X