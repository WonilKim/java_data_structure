package chapter06;

import java.util.Random;

// 단순 삽입 정렬

import java.util.Scanner;

class InsertionSort {
    //--- 단순 삽입 정렬 ---//
    static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int j;
            int tmp = a[i];
            for (j = i; j > 0 && a[j - 1] > tmp; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Random rand = new Random(System.currentTimeMillis());

        System.out.println("삽입 정렬");

        int nx = 10;
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            x[i] = rand.nextInt(100);
            System.out.print(" " + x[i]);
        }
        System.out.println();

        insertionSort(x, nx);

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++) {
            System.out.print(" " + x[i]);
        }
        System.out.println();
    }
}
