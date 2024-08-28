package Day1;

import java.util.Arrays;
import java.util.Scanner;

public class Day1_Bai9 {
    static Scanner sc = new Scanner(System.in);
    static int m = 0, n = 0;
    static int[][] a;

    public static void enterMatrix() {
        System.out.print("Nhap vao so hang: ");
        m = sc.nextInt();
        System.out.print("Nhap vao so cot: ");
        n = sc.nextInt();
        a = new int[m][n];
        System.out.println("Nhap vao gia tri cho ma tran:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("\tPhan tu thu [" + (i + 1) + "][" + (j + 1) + "]: ");
                a[i][j] = sc.nextInt();
            }
        }
    }

    public static void display() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void tichBoiSo3DongDauTien(){
        int result = 1;
        for(int i = 0; i < n; i++){
            if(a[0][i] % 3 == 0) result *= a[0][i];
        }
        System.out.println("Tich Boi So Cua 3 dong dau tien: " + result);
    }

    public static void gtlnTrenTungDong(){
        int[] maxArr = new int[m];
        for(int i = 0; i < m; i++){
            int max = a[i][0];
            for(int j = 0; j < n; j++){
                if(a[i][j] > max){
                    max = a[i][j];
                }
            }
            maxArr[i] = max;
        }
        System.out.println(Arrays.toString(maxArr));
    }

    public static void main(String[] args) {
        enterMatrix();
        display();
//        tichBoiSo3DongDauTien();
        gtlnTrenTungDong();
    }
}
