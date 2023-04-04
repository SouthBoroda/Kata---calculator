import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>();

    static {
        ROMAN_NUMERALS.put("I", 1);
        ROMAN_NUMERALS.put("II", 2);
        ROMAN_NUMERALS.put("III", 3);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("VI", 6);
        ROMAN_NUMERALS.put("VII", 7);
        ROMAN_NUMERALS.put("VIII", 8);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("X", 10);
    }

    public static String calc(String input) throws IllegalArgumentException {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Недопустимый формат ввода");
        }

        String op = tokens[1];
        int a = parseNumber(tokens[0]);
        int b = parseNumber(tokens[2]);

        int result;
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + op);
        }

        if (isRomanNumber(input)) {
            if (result <= 0) {
                throw new IllegalArgumentException("Римские цифры не могут представлять ноль или отрицательные числа");
            }
            return toRomanNumeral(result);
        } else {
            return Integer.toString(result);
        }
    }

    private static int parseNumber(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный номер: " + s);
        }
    }

    private static boolean isRomanNumber(String s) {
        return s.contains("I") || s.contains("V") || s.contains("X");
    }

    private static String toRomanNumeral(int n) {
        if (n < 1 || n > 3999) {
            throw new IllegalArgumentException("Число не допустимое для Римских цифр: " + n);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ROMAN_NUMERALS.entrySet()) {
            String numeral = entry.getKey();
            int value = entry.getValue();
            while (n >= value) {
                sb.append(numeral);
                n -= value;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("напиши выражение ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                String result = calc(input);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
