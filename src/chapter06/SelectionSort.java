package chapter06;

import java.util.Random;

// 단순 선택 정렬

import java.util.Scanner;

class SelectionSort {
    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
    }

    //--- 단순 선택 정렬 ---//
    static void selectionSort(int[] a, int n) {
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            int min = i;                // 아직 정렬되지 않은 부분에서 가장 작은 요소의 인덱스를 저장
            for (int j = i + 1; j < n; j++) {
                count++;
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);           // 아직 정렬되지 않은 부분의 첫 요소와 가장 작은 요소를 교환
        }
        System.out.println("count = " + count);
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Random rand = new Random(System.currentTimeMillis());

        System.out.println("선택 정렬");

        int nx = 10;
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            x[i] = rand.nextInt(100);
            System.out.print(" " + x[i]);
        }
        System.out.println();

        selectionSort(x, nx);

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++) {
            System.out.print(" " + x[i]);
        }
        System.out.println();
    }
}
