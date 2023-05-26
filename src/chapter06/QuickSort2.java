package chapter06;

import java.util.Random;

// 퀵 정렬(비재귀 버전)

import java.util.Scanner;

import chapter04.IntStack;
import chapter04.ObjectStack;
import chapter04.Point;

class QuickSort2 {
    static int count = 0;
    
    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
    }

    //--- 퀵 정렬(비재귀 버전)---//
    static void quickSort(int[] a, int left, int right) {
        IntStack lstack = new IntStack(right - left + 1);
        IntStack rstack = new IntStack(right - left + 1);

        lstack.push(left);
        rstack.push(right);

        while (lstack.isEmpty() != true) {
            int pl = left  = lstack.pop();        // 왼쪽 커서
            int pr = right = rstack.pop();        // 오른쪽 커서
            int x = a[(left + right) / 2];        // 피벗은 가운데 요소

            do {
                while (a[pl] < x) pl++;
                while (a[pr] > x) pr--;
                if (pl <= pr)
                    swap(a, pl++, pr--);
            } while (pl <= pr);

            if (left < pr) {
                lstack.push(left);           // 왼쪽 그룹 범위의
                rstack.push(pr);             // 인덱스를 푸시
            }
            if (pl < right) {
                lstack.push(pl);             // 오른쪽 그룹 범위의
                rstack.push(right);          // 인덱스를 푸시
            }
        }
    }

    static void quickSortOneStack(int[] a, int left, int right) {

        ObjectStack stack = new ObjectStack(right - left + 1);
        Point p = new Point(left, right);
        stack.push(p);

        while (stack.isEmpty() != true) {
            p = stack.pop();
            int pl = left  = p.getX();        // 왼쪽 커서
            int pr = right = p.getY();        // 오른쪽 커서
            int x = a[(left + right) / 2];        // 피벗은 가운데 요소

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
    
            if (left < pr) {
                // lstack.push(left);           // 왼쪽 그룹 범위의
                // rstack.push(pr);             // 인덱스를 푸시
                stack.push(new Point(left, pr));
            }
            if (pl < right) {
                // lstack.push(pl);             // 오른쪽 그룹 범위의
                // rstack.push(right);          // 인덱스를 푸시
                stack.push(new Point(pl, right));
            }
        }
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
        quickSortOneStack(x, 0, nx - 1);            // 배열 x를 퀵정렬
        System.out.println("count = " + count);

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++) {
            System.out.print(" " + x[i]);
        }
        System.out.println();
    }
}
