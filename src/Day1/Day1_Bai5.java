package Day1;

import java.util.Scanner;

public class Day1_Bai5 {
    public static int UCLN(int a, int b){
        if (b == 0) {
            return a;
        } else {
            return UCLN(b, a % b);
        }
    }

    public static int BCNN(int a, int b){
        int UCLN = UCLN(a,b);
        return a*b/UCLN;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();

        // UCLN cua a va b
        System.out.println(UCLN(a,b));

        // BCNN cua a va
        System.out.println(BCNN(a,b));
    }
}
