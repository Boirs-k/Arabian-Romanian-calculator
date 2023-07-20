import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
            "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
            "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII",
            "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII",
            "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
            "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public static void main(String[] args) throws Exception {
        char operation;
        boolean isRomanian;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение в формате \"Х Y Z\", где X и Z числа от 1 до 10, а Y один из знаков операции (+,-,/,*). В одном выражении может быть СТРОГО либо два числа арабскими цифрами, либо римскими ");
        String input = scanner.nextLine();

        isRomanian = checkRomanian(input);

        operation = operationDetector(input);

        printResult(calculatingResult(input, operation, isRomanian), isRomanian);
    }

    static char operationDetector(String input) throws Exception {
        if (input.contains("-")) {
            return '-';
        } else if (input.contains("*")) {
            return '*';
        } else if (input.contains("/")) {
            return '/';
        } else if (input.contains("+")) {
            return '+';
        } else {
            throw new Exception("Неправильная операция");
        }
    }

    static boolean checkRomanian(String input) throws Exception {
        if ((input.matches("(.*)[IVX](.*)")) && input.matches("(.*)\\d(.*)")) {
            throw new Exception("Нельзя использовать разные типы цифр!");
        } else return input.matches("(.*)[IVX](.*)");
    }

    static byte calculatingResult(String input, char operation, boolean isRomanian) throws Exception {
        byte workNumber1, workNumber2;
        byte result = 0;

        if (input.contains(".") || input.contains(",")) {
            throw new Exception("Калькулятор не умеет работать с дробными числами!");
        }

        String[] workString = input.split("[+-/*]");

        if (workString.length > 2) {
            throw new Exception("Пример составлен некооректно, возможно слишком много операционных знаков!");
        }


        try {
            if (isRomanian) {
                workNumber1 = (byte) Arrays.stream(roman).toList().indexOf(workString[0].trim());
                workNumber2 = (byte) Arrays.stream(roman).toList().indexOf(workString[1].trim());
            } else {
                workNumber1 = Byte.parseByte(workString[0].trim());
                workNumber2 = Byte.parseByte(workString[1].trim());
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Неверный формат введенных данных!");
        }

        if ((workNumber1 > 0 && workNumber1 <= 10) && (workNumber2 > 0 && workNumber2 <= 10)) {
            switch (operation) {
                case '+' -> result = (byte) (workNumber1 + workNumber2);
                case '-' -> result = (byte) (workNumber1 - workNumber2);
                case '*' -> result = (byte) (workNumber1 * workNumber2);
                case '/' -> result = (byte) (workNumber1 / workNumber2);
            }
        } else {
            throw new Exception("Числа лежат вне диапазона или выражение неполное!");
        }

        return result;
    }

    static void printResult(int result, boolean isRomanian) throws Exception {
        if (isRomanian) {
            if (result > 0) {
                System.out.println(roman[result]);
            } else {
                throw new Exception("Значение в выражении из римских чисел не может быть меньше единицы!");
            }
        } else {
            System.out.println(result);
        }
    }
}