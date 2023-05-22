package chapter05;

import java.util.ArrayList;

// 8퀸 문제 풀이

class EightQueen {

    static final int countQueens = 8;
    static int index = 0;

    static boolean[] flag_a = new boolean[countQueens]; // 각 행에 퀸이 이미 배치되었는가?
    static boolean[] flag_b = new boolean[15]; // 대각선 ↙에 퀸이 이미 배치되었는가?
    static boolean[] flag_c = new boolean[15]; // 대각선 ↘에 퀸이 이미 배치되었는가?
    static int[] pos = new int[countQueens]; // 각 열의 퀸의 위치

    static ArrayList<int[]> list = new ArrayList<int[]>();

    // --- 각 열의 퀸의 위치를 출력 ---//
    static void print() {
        System.out.println("=".repeat(17));
        System.out.printf(" <%d>\n", index++);
        for (int i = 0; i < countQueens; i++)
            System.out.printf("%2d", pos[i]);

        System.out.println();
        System.out.println(" " + "-".repeat(15) + " ");
        showResult(pos);
    }

    // --- 각 열의 퀸의 위치를 출력 ---//
    static void print(int[] p) {
        for (int i = 0; i < countQueens; i++)
            System.out.printf("%2d", p[i]);
        System.out.println();
    }

    // --- i열의 알맞은 위치에 퀸을 배치 ---//
    static void set(int i) {
        for (int j = 0; j < countQueens; j++) {
            if (flag_a[j] == false && // 가로(j행)에 아직 배치하지 않음
                    flag_b[i + j] == false && // 대각선 ↙에 아직 배치하지 않음
                    flag_c[i - j + 7] == false) { // 대각선 ↘에 아직 배치하지 않음
                pos[i] = j; // 퀸을 j행에 배치함
                if (i == 7) { // 모든 열에 배치함
                    print();

                    setPosList();
                } else {
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = true;
                    set(i + 1);
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        set(0);
    }

    private static void showResult(int[] pos) {
        // Queen 문제 풀이 결과 디스플레이
        for (int r = 0; r < countQueens; r++) {
            for (int c = 0; c < countQueens; c++) {
                if (pos[c] == r)
                    System.out.print(" 1");
                else
                    System.out.print(" 0");
            }
            System.out.println();
        }

    }

    private static void setPosList() {
        // pos 를 list 에 저장, static pos 를 local 변수로 복사해야함.
        int[] posCopy = new int[countQueens];
        for (int c = 0; c < countQueens; c++)
            posCopy[c] = pos[c];
        list.add(posCopy);
    }
}