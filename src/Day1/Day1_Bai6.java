package Day1;

import java.util.Scanner;

public class Day1_Bai6 {
    public static String convertToBinary(int n) {
        String s = "";
        while (n > 0)
        {
            s =  ((n % 2) == 0 ? "0" : "1") + s;
            n = n / 2;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.print("Nhap so nguyen duong n: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // System.out.println("Day so nhi phan: " + Integer.toBinaryString(n));
        System.out.println("Day so nhi phan: " + convertToBinary(n));
    }
}
