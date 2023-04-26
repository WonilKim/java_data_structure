package chapter02;

import java.util.Random;

public class Matrix {

    static int num = 0;

    public static void main(String[] args) {

        int[][] A = getMatrix(2, 3);
        int[][] A1 = getMatrix(2, 3);
        int[][] A2;
        int[][] B = getMatrix(3, 4);
        int[][] C;
        int[][] D;

        showMatrix("A", A);
        showMatrix("A1", A1);
        showMatrix("B", B);
        //
        A2 = addMatrix(A, A1);
        showMatrix("A2", A2);

        C = multiplyMatrix(A, B);
        showMatrix("C", C);

        D = transposeMatrix(A);
        showMatrix("D", D);
    }

    private static void showMatrix(String name, int[][] m) {
        System.out.println("-- 행렬 " + name + " 의 데이터");
        if (m == null) {
            System.out.println("행렬의 데이터가 없습니다.");
            return;
        }
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[0].length; c++) {
                System.out.print(m[r][c] + "\t");
            }
            System.out.println();
        }
    }

    private static int[][] transposeMatrix(int[][] m) {
        int[][] result = new int[m[0].length][m.length];

        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[0].length; c++) {
                result[c][r] = m[r][c];
            }
        }

        return result;
    }

    private static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];
        
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                
                int value = 0;
                for(int e=0; e<m1[0].length; e++){
                    value += m1[r][e] * m2[e][c];
                }
                result[r][c] = value;
                
            }
        }
        return result;
    }

    private static int[][] addMatrix(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m1[0].length];
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = m1[r][c] + m2[r][c];
            }
        }
        return result;
    }

    private static int[][] getMatrix(int rows, int cols) {

        int maxValue = 100;
        boolean rand = false;

        int[][] matrix = new int[rows][cols];

        Random random = new Random(System.currentTimeMillis());

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rand == true)
                    matrix[r][c] = random.nextInt(maxValue);
                else
                    matrix[r][c] = num++;
            }
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return matrix;
    }
}
