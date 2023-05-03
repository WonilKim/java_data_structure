package study.study1;

public class MatrixTest {
    public static void main(String[] args) {
        
        Matrix A = new Matrix(2, 3);
        Matrix A1 = new Matrix(2, 3);

        A.setData();
        A1.setData();

        A.showData("A");
        A1.showData("A1");

        Matrix A2 = Matrix.addMatrix(A, A1);
        A2.showData("A2");

        // System.out.println(A2.rowCount); 

        int row = A2.getRowCount();
        System.out.println(row);

        A2.setRowCount(A2.getRowCount());
        System.out.println(A2.getRowCount());

        Matrix D = A.Transpose();
        D.showData("D");

        Matrix B = new Matrix(3, 4);
        B.setData();
        B.showData("B");

        Matrix C = Matrix.multiplyMatrix(A, B);
        C.showData("C");

       
    }
}
