package chapter06;

import java.util.Random;

// 퀵 정렬

class QuickSort {
    static int count = 0;

    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
    }

    //--- 퀵 정렬 ---//
    static void quickSort(int[] a, int left, int right) {
        
        int pl = left;                   // 왼쪽 커서
        int pr = right;                  // 오른쪽 커서
        int x = a[(pl + pr) / 2];        // 피벗(가운데 요소)

        do {
            while (a[pl] < x) { 
                pl++; 
                count++;
            }
            while (a[pr] > x) { 
                pr--; 
                count++;
            }
            if (pl <= pr)
                swap(a, pl++, pr--);
            count++;
        } while (pl <= pr);

        if (left < pr)  quickSort(a, left, pr);
        if (pl < right) quickSort(a, pl, right);
    }

    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis());

        System.out.println("퀵 정렬");

        int nx = 100;
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            x[i] = rand.nextInt(100);
            System.out.print(" " + x[i]);
        }
        System.out.println();

        count = 0;
        quickSort(x, 0, nx - 1);            // 배열 x를 퀵정렬
        System.out.println("count = " + count);

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++) {
            System.out.print(" " + x[i]);
        }
        System.out.println();
    }
}
