package study.study1;

import java.util.Random;

public class Matrix {

    private int[][] data;
    private int rowCount;
    private int colCount;

    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rows) {
        this.rowCount = rows;
    }

    public Matrix(int rows, int cols) {

        this.data = new int[rows][cols];
        this.rowCount = rows;
        this.colCount = cols;
    }

    protected static Matrix addMatrix(Matrix m1, Matrix m2) {

        Matrix result = new Matrix(m1.rowCount, m1.colCount);

        for (int r = 0; r < m1.rowCount; r++) {
            for (int c = 0; c < m1.colCount; c++) {
                result.data[r][c] = m1.data[r][c] + m2.data[r][c];
            }
        }

        return result;
    }

    protected void setData() {
        Random rand = new Random(System.currentTimeMillis());

        for (int r = 0; r < this.rowCount; r++) {
            for (int c = 0; c < this.colCount; c++) {
                data[r][c] = rand.nextInt(100);
            }
        }

        try {
            Thread.sleep(1);
        } catch (Exception e) {

        }

    }

    protected void showData(String name) {

        System.out.println(name + " 행렬의 데이터");

        for (int r = 0; r < this.rowCount; r++) {
            for (int c = 0; c < this.colCount; c++) {
                System.out.print(data[r][c] + "\t");
            }
            System.out.println();
            // System.out.print("\n");
        }

    }

    protected Matrix Transpose() {

        Matrix result = new Matrix(colCount, rowCount);

        for (int r = 0; r < this.rowCount; r++) {
            for (int c = 0; c < this.colCount; c++) {
                result.data[c][r] = this.data[r][c];
            }
        }

        return result;

    }

    protected static Matrix multiplyMatrix(Matrix m1, Matrix m2) {

        Matrix result = new Matrix(m1.rowCount, m2.colCount);

        for (int r = 0; r < result.rowCount; r++) {
            for (int c = 0; c < result.colCount; c++) {

                int value = 0;
                for(int e = 0; e < m1.colCount; e++) {
                    value += m1.data[r][e] * m2.data[e][c];
                }
                result.data[r][c] = value;
            }
        }
        return result;
    }

}
