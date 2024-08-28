package Day1;

public class Day1_Bai7 {
    public static String reverseString(String s){
        return new StringBuilder(s).reverse().toString();
    }

    public static int[] tanSuatKiTu(String s){
        int[] a = new int[127];
        for(int i = 0; i < s.length(); i++){
            a[s.charAt(i)]++;
        }
        for(int i = 0; i < a.length; i++){
            if(a[i] != 0) System.out.println((char) i + ": " + a[i]);
        }
        return a;
    }

    public static String getSubString(String s, int m, int n){
        if(n < 1 || n > s.length() || m < 0 || m >= s.length() || m >= n){
            return "Position invalid!";
        }
        return s.substring(m, n);
    }

    public static void main(String[] args) throws Exception {
        String s = "phan dinh khang";

        // Dao nguoc chuoi
        System.out.println(reverseString(s));

        // Chuyen sang in hoa
        System.out.println(s.toUpperCase());

        // Chuyen sang in thuong
        System.out.println(s.toLowerCase());

        // Tan suat cua cac ki tu
        tanSuatKiTu(s);

        // Lay ra chuoi con trong s
        System.out.println(getSubString(s, 0, 0));
    }
}