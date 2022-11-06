import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифмитеческую операцию, два операнда и один оператор: a + b, a - b, a * b, a / b");
        String output = calc(scanner.nextLine());
        System.out.println(output);
    }

    public static String calc(String input) throws IOException {
        String[] strArray = input.split(" ");
        int a = 0;
        int b = 0;
        int result;
        String[] numRom1Array = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] numRom10Array = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String romResOutput = "";
        int romRes;
        boolean flagRom1 = false;
        boolean flagRom2 = false;
        if (strArray.length != 3) {
            throw new IOException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        try {
            a = Integer.parseInt(strArray[0]);
        } catch (NumberFormatException e) {
            for (int i = 0; i < numRom1Array.length; ++i) {
                if (strArray[0].equals(numRom1Array[i])) {
                    a = i;
                    flagRom1 = true;
                    break;
                }
            }
        }
        try {
            b = Integer.parseInt(strArray[2]);
        } catch (NumberFormatException e) {
            for (int i = 0; i < numRom1Array.length; ++i) {
                if (strArray[2].equals(numRom1Array[i])) {
                    b = i;
                    flagRom2 = true;
                    break;
                }
            }
        }
        if (flagRom1 && !flagRom2
                || !flagRom1 && flagRom2) {
            throw new IOException("т.к. используются одновременно разные системы счисления");
        }
        if (a < 1
                || a > 10
                || b < 1
                || b > 10) {
            throw new IOException("т.к. калькулятор принимает на вход числа от 1 до 10 включительно, не более");
        }
        switch (strArray[1]) {
            case "-":
                result = a - b;
                break;
            case "+":
                result = a + b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IOException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (flagRom1) {
            if (result < 1) {
                throw new IOException("т.к. в римской системе нет отрицательных чисел");
            }
            romRes = result / 10;
            romResOutput += numRom10Array[romRes];
            romRes = result % 10;
            romResOutput += numRom1Array[romRes];
            return romResOutput;
        }
        return String.valueOf(result);
    }
}