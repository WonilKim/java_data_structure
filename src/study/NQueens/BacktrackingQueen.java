package study.NQueens;
//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp

//N Queen problem / backtracking

import chapter04.ObjectStack;
import chapter04.Point;

public class BacktrackingQueen {

	static final int qCount = 8;

	public static void SolveQueen(int[][] data) {
		int count = 0, mode = 0;
		int icol = 0, irow = 0;
		ObjectStack st = new ObjectStack(10);

		// 초기 0, 0 위치에 퀸 설정
		Point p = new Point();
		p.setRow(irow);
		p.setCol(icol);

		data[icol][irow] = 1;
		count++;
		st.push(p);
		System.out.println("push(" + p + ")");

		while (count < qCount) { // 퀸 qCount 개를 다 놓을때 까지 반복
			icol++;
			int crow = 0;
			while (icol < data[0].length) // 체스판의 가로축 끝까지 반복
			{
				while (crow < data.length) { // 체스판의 세로축 끝까지 반복

					if (CheckMove(data, crow, icol) == true){
						p = new Point();
						p.setRow(crow);
						p.setCol(icol);

						st.push(p);
						System.out.println("push(" + p + ")");
						count++;
						data[crow][icol] = 1;
						break;	
					}
					crow++;

				}
				if (crow < data.length) {
					break;
				} else {
					p = st.pop();
					System.out.println("pop(" + p + ")");
					count--;
					data[p.getRow()][p.getCol()] = 0;

					icol = p.getCol();
					crow = p.getRow() + 1;

				}
			}
		}
	}

	public static boolean checkRow(int[][] data, int row) {

		for (int c = 0; c < data[row].length; c++) {
			if(data[row][c] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkCol(int[][] data, int col) {

		for(int r = 0; r < data.length; r++) {
			if(data[r][col] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDiagSW(int[][] data, int row, int col) { // x++, y-- or x--, y++ where 0<= x,y <= 7

		int r = row;
		int c = col;

		while(true) {
			r++;
			c--;

			if((c < 0) || (r >= data.length)) {
				break;
			}

			if(data[r][c] == 1) {
				return false;
			}
		}

		r = row;
		c = col;

		while(true) {
			r--;
			c++;

			if((r < 0) || (c >= data[0].length)) {
				break;
			}

			if(data[r][c] == 1) {
				return false;
			}
		}

		return true;
	}

	public static boolean checkDiagSE(int[][] data, int row, int col) {// x++, y++ or x--, y--

		int r = row;
		int c = col;

		while(true) {
			r++;
			c++;

			if((c >= data[0].length) || (r >= data.length)) {
				break;
			}

			if(data[r][c] == 1) {
				return false;
			}
		}

		r = row;
		c = col;

		while(true) {
			r--;
			c--;

			if((r < 0) || (c < 0)) {
				break;
			}

			if(data[r][c] == 1) {
				return false;
			}
		}

		return true;
	}

	public static boolean CheckMove(int[][] data, int row, int col) {// (x,y)로 이동 가능한지를 check

		if ((checkRow(data, row) == true)
				&& (checkCol(data, col) == true)
				&& (checkDiagSE(data, row, col) == true)
				&& (checkDiagSW(data, row, col) == true)
				&& data[row][col] == 0) {

			return true;
		}

		return false;
	}

	public static boolean NextMove(int[][] data, int row, int col) {// 다음 row에 대하여 이동할 col을 조사

		return true;
	}

	public static void main(String[] args) {
		int row = qCount, col = qCount;
		int[][] data = new int[qCount][qCount];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		SolveQueen(data);

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(" " + data[i][j]);
			}
			System.out.println();
		}
	}
}
