import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static char operation;

    public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите 2 целых числа \n и знак операции (+,-,*,/) между ними:");
            // чтение строки из консоли
            String Input = scanner.nextLine();
            //определение арифметического действия
            String[] actions = {"+", "-", "*", "/"};
            String[] regexActions = {"\\+", "-", "\\*", "/"};
            int actionIndex = -1;
            for (int i1 = 0; i1 < actions.length; i1++) {
                if (Input.contains(actions[i1])) {
                    actionIndex = i1;
                    break;
                }
            }
            //в случае, если арифметический символ введен не верно или отсутствует
            if (actionIndex == -1) {
                System.out.println("Некорректно введено арифметическое выражение");
                return;
            }
            //разделяем введенное выражение на два отдельных числа
            String[] numeric = Input.split(regexActions[actionIndex]);

            //задаем массив для обозначения арифметических операций
            char[] numbers = new char[10];
            for (int i = 0; i < Input.length(); i++) {
                numbers[i] = Input.charAt(i);
                if (numbers[i] == '+') {
                    operation = '+';
                }
                if (numbers[i] == '-') {
                    operation = '-';
                }
                if (numbers[i] == '*') {
                    operation = '*';
                }
                if (numbers[i] == '/') {
                    operation = '/';
                }
            }
            String digit0 = numeric[0];
            String digit1 = numeric[1];
            //удаляем пробелы при их наличии
            String digit2 = digit1.trim();
            String digit3 = digit0.trim();
            int number1;
            int number2;
            int result;

            number1 = RomanToNum(digit3);
            number2 = RomanToNum(digit2);

            if (number1 < 0 && number2 < 0);

                //необходимо определить, к какой системе исчисления относятся числа. Если числа римские, их необходимо
                //конвертировать в арабские для произведения операции и снова конвертивовать в римские на выходе
            else {
            result = calc(number1, number2, operation);
            System.out.println("Результат для римских чисел:");
            String resultRoman = converterNumToRoman(result);
            System.out.println(digit3 + " " + operation + " " + digit2 + " = " + resultRoman);
                if (!(1 <= result && result <= 100)) {
                    throw new IOException("В римской системе исчисления отсутствуют числа менее 1");


                }
            }

            number1 = Integer.parseInt(digit3);
            number2 = Integer.parseInt(digit2);
            result = calc(number1, number2, operation);
            System.out.println("Результат для арабских чисел:");
            System.out.println(number1 + " " + operation + " " + number2 + " = " + result);
            //System.out.println(result);
        }

    //создаем массив для выходных значений результатов операций над римскими числами,
    // ограниченный  числом С =  100, максимальным из возможных результатов
    private static String converterNumToRoman(int numArabian) {
        String[] Roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String res = Roman[numArabian];
        return res;
    }

    //создание массива для конвертации римских цифр а арабские при вводе
    private static int RomanToNum(String Roman) {
        try {
            switch (Roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }

        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат ввода данных");
        }
        return -1;
    }

    public static int calc(int number1, int number2, char numbers) throws IOException {
        int result = 0;
        switch (numbers) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                try {
                    result = number1 / number2;
                } catch (ArithmeticException e) {
                    System.out.println("Деление на 0 не допускается!");
                    break;
                }
                try {
                    result = number1 / number2;
                } catch (InputMismatchException e) {
                    System.out.println("Результатом деления допускаются только целые числа");
                }
                break;
        }
        if (!(number1 > 0 && number1 <= 10 && number2 > 0 && number2 <= 10)) {
            throw new IOException("Ведите число от 1 до 10 в единой системе исчисления");
        }
        return result;
    }
}
