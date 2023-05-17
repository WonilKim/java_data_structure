
package chapter05;
//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp

//N Queen problem / backtracking

import chapter04.ObjectStack;
import chapter04.Point;

public class BacktrackingQueen {

    public static void SolveQueen(int[][] data) {
        ObjectStack stack = new ObjectStack(10);

        int count = 0, mode = 0;
        int icol = 0, irow = 0;

        Point p = new Point();
        p.setRow(irow);
        p.setCol(icol);

        data[irow][icol] = 1; // [0, 0] 에 초기 퀸 설정
        stack.push(p);
        System.out.println("push(" + p + ")");
        count++;

        // x 축은 좌/우 = row 방향
        // y 축은 상/하 = column 방향

        while (count < data.length) {

            icol++; // x축 한 칸 이동 (우측)
            int r = 0; // y측 0으로 초기화

            while (icol < data.length) { // 체스판의 가로 길이보다 작을때 까지

                while (r < data[0].length) { // 체스판의 세로 길이보다 작은 동안

                    if (CheckMove(data, r, icol) == true) { // 이 위치에 퀸을 놓을 수 있는지 확인
                        p = new Point();
                        p.setRow(r);
                        p.setCol(icol);
                        System.out.println("push(" + p + ")");
                        stack.push(p); // 스택에 위치를 저장하고
                        data[r][icol] = 1;
                        count++; // 카운트 증가
                        break; // 루프 빠져나오기
                    }
                    r++; // y축 한 칸 이동 (아래)
                }

                if (r != data[0].length) { // y축이 체스판의 세로 길이 내부에 있으면 (퀸을 놓을 자리를 찾았으면)
                    break; // 가로 길이 루프 종료
                } else { // 퀸을 놓을 자리를 찾지 못했으면
                    p = stack.pop(); // 스택에 저장된 위치(마지막으로 놓은 퀸)를 제거하고
                    System.out.println("pop(" + p + ")");
                    data[p.getRow()][p.getCol()] = 0;
                    count--; // 카운트 감소

                    icol = p.getCol(); // x 축은 제거된 위치와 같은 곳에서 다시 시작
                    r = p.getRow() + 1; // y 축은 제거된 위치의 다음(아래) 부터 시작
                }
            } // while (ix < d.length)
        } // while (count < data.length)

    } // public static void SolveQueen(int[][] data)

    public static boolean checkRow(int[][] data, int row) {
        // 지정한 row 에 퀸이 놓여 있는지 확인
        for (int c = 0; c < data.length; c++) {
            if (data[row][c] == 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkCol(int[][] data, int col) {
        // 지정한 column 에 퀸이 놓여 있는지 확인
        for (int r = 0; r < data[0].length; r++) {
            if (data[r][col] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagSW(int[][] data, int row, int col) { // x++, y-- or x--, y++ where 0<= x,y <= 7
        int c = col;
        int r = row;
        // 지정한 위치의 북동쪽 방향에 퀸이 놓여 있는지 확인
        while ((c < data[0].length) && (0 <= r)) {
            c++;
            r--;
            if ((c == data[0].length) || (r < 0)) {
                break;
            }
            if (data[r][c] == 1) {
                return false;
            }
        }
        c = col;
        r = row;
        // 지정한 위치의 서남쪽 방향에 퀸이 놓여 있는지 확인
        while ((0 <= c) && (r < data.length)) {
            c--;
            r++;
            if ((c < 0) || (r == data.length)) {
                break;
            }
            if (data[r][c] == 1) {
                return false;
            }
        }
        return true;
    } // public static boolean checkDiagSW(int[][] data, int row, int col)

    public static boolean checkDiagSE(int[][] data, int row, int col) {// x++, y++ or x--, y--
        int c = col;
        int r = row;
        // 지정한 위치의 남동쪽 방향에 퀸이 놓여 있는지 확인
        while ((c < data[0].length) && (r < data.length)) {
            c++;
            r++;
            if ((c == data[0].length) || (r == data.length)) {
                break;
            }
            if (data[r][c] == 1) {
                return false;
            }

        }
        c = col;
        r = row;
        // 지정한 위치의 북서쪽 방향에 퀸이 놓여 있는지 확인
        while ((0 <= c) && (0 <= r)) {
            c--;
            r--;
            if ((c < 0) || (r < 0)) {
                break;
            }
            if (data[r][c] == 1) {
                return false;
            }
        }
        return true;
    } // public static boolean checkDiagSE(int[][] data, int row, int col)

    public static boolean CheckMove(int[][] data, int row, int col) {// (row, col) 위치로 이동 가능한지 확인

        if ((data[row][col] == 0) && (checkRow(data, row)) && (checkCol(data, col))
                && (checkDiagSW(data, row, col)) && (checkDiagSE(data, row, col)))
            return true;
        else
            return false;
    }

    // public static boolean NextMove(int[][] data, int row, int col) {// 다음 row에
    // 대하여 이동할 col을 조사
    // for (int c = 0; c < data[0].length; c++) {
    // if (CheckMove(data, row, c) == true) {
    // return true;
    // }
    // }
    // return false;
    // }

    public static int NextMove(int[][] data, int col) { // 지정한 column에 이동할 수 있는 row가 있는지 확인

        for (int r = 0; r < data.length; r++) {
            if (CheckMove(data, r, col) == true) {
                return r;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 체스판 정사각형 길이 (퀸의 수)지정
        final int N = 8;
        
        int row = N, col = N;

        // 체스판 0으로 리셋
        int[][] data = new int[row][col];
        for (int r = 0; r < row; r++)
            for (int c = 0; c < col; c++)
                data[r][c] = 0;

        SolveQueen(data);

        // Queen 문제 풀이 결과 디스플레이
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                System.out.print(" " + data[r][c]);
            }
            System.out.println();
        }
    }
}
