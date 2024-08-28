package Day1;

import java.util.Arrays;

public class Day1_Bai8 {
    public static int tongSoDuongLe(int[] arr){
        return Arrays.stream(arr).reduce(0, (sum, ele) -> ele % 2 == 1 ? sum + ele : sum);
    }

    public static int timPhanTu(int[] arr, int k){
        int position = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == k){
                position = i;
                break;
            }
        }
        return position;
    }

    public static int[] sortAsc(int[] arr){
        Arrays.sort(arr);
        return arr;
    }

    public static int[] insertToArr(int[] arr, int k){
        int[] newArr = new int[arr.length + 1];
        if(arr.length == 1){
            if(k >= arr[0]){
                newArr[0] = arr[0];
                newArr[1] = k;
            }
            else{
                newArr[0] = k;
                newArr[1] = arr[0];
            }
        }

        for(int i = 0; i < arr.length - 1; i++){
//            1,2,3,4,5
            if(k >= arr[i] && k <= arr[i + 1]){
                System.out.println(arr[i]);
                newArr[i] = arr[i];
                newArr[i + 1] = k;
                for(int j = i + 1; j < arr.length; j++){
                    newArr[j + 1] = arr[j];
                }
                break;
            }
            else newArr[i] = arr[i];
        }
        return newArr;
    }

    public static void main(String[] args) {
        int[] arrSorted = {1};
        int[] arr = {6,1,3,4,5,2,7,9,8};



        // Tong cac so le
        // System.out.println("Tong so le: " + tongSoDuongLe(arr));

        // Tim vi tri phan tu
        int k = 10;
        int position = timPhanTu(arr, k);
        if(position != -1){
             System.out.println("Phan tu " + k + " is: " + position);
        }
        else System.out.println("Khong tim thay k");

        // Sort mang tang dan
        // System.out.println("Mang tang dan: " + Arrays.toString(sortAsc(arr)));

        // Chen
        System.out.println(Arrays.toString(insertToArr(arrSorted, 2)));
    }
}
