package Day1;

import java.util.Scanner;

public class Day1_Bai2 {

    public static void main(String[] args) {
        System.out.println("Nhap so nguyen duong n: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        double sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += (double) 1/i;
        }
        System.out.println(sum);
    }
}
