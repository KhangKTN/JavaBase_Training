package Day1;

import java.util.Scanner;

public class Day1_Bai4 {

    public static int sumOfDigits(int m) {
        int sum = 0;
        while (m > 0) {
            sum += m % 10;
            m /= 10;
        }
        return sum;
    }

    public static int productOfDigits(int m) {
        int product = 1;
        while(m > 0){
            product *= m % 10;
            m /= 10;
        }
        return product;
    }

    public static boolean isNumber(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Nhap m tu ban phim: ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        boolean isNumber = isNumber(s);
        if(isNumber){
            int m = Integer.parseInt(s);

            // Tinh tong cac chu so trong m
             System.out.println("Tong cac chu so cua " + m + ": " + sumOfDigits(m));

            // Tinh tich cac chu so trong m
             System.out.println("Tich cac chu so cua " + m + ": " + productOfDigits(m));
        }
        else System.err.println("Khong the tinh toan. So vua nhap la chuoi!");
    }
}
