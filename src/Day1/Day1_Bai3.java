package Day1;

import java.math.BigInteger;
import java.util.Scanner;

public class Day1_Bai3 {
    public static BigInteger getFactorial(int f) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= f; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public static double calculate(int n){
        double result = 0;
        for(int i = 1; i <= 2 * n - 1; i = i + 2){
            result += 1.0 / getFactorial(i).doubleValue();
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap n: ");
        Integer n = scanner.nextInt();

        System.out.println("Ket qua: " + calculate(n));

    }
}
