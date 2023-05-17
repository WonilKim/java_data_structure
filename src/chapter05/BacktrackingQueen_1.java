
package chapter05;
//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp

//N Queen problem / backtracking

import chapter04.ObjectStack;
import chapter04.Point;

public class BacktrackingQueen_1 {

	private static ObjectStack stack = new ObjectStack(10);

	public static void SolveQueen(int[][] data) {
		int count = 0, mode = 0;
		int ix = 0, iy = 0;

		Point p = new Point(ix, iy);

		data[iy][ix] = 1; // [0, 0] 에 초기 퀸 설정
		stack.push(p);
		System.out.println("push(" + p + ")");
		count++;

		while (count < data.length) {

			ix++; // x축 한 칸 이동
			int cy = 0; // y측 0으로 초기화

			while (ix < data.length) { // 체스판의 가로 길이보다 작을때 까지

				while (cy < data[0].length) { // 체스판의 세로 길이보다 작으면
					int c = NextMove(data, cy);

					if (0 <= c) {
						p = new Point(c, cy);
						System.out.println("push(" + p + ")");
						stack.push(p); // 스택에 위치를 저장하고
						data[cy][c] = 1;
						count++; // 카운트 증가
						break; // 루프 빠져나오기
					}
					cy++;
				}

				if (cy != data[0].length) { // y축이 체스판의 세로 길이 내부에 있으면
					break; // 가로 길이 루프 종료
				} else {
					p = stack.pop(); // 스택에 저장된 위치를 제거하고
					System.out.println("pop(" + p + ")");
					data[p.getY()][p.getX()] = 0;
					count--; // 카운트 감소
				}
			} // while (ix < d.length)
		} // while (count < data.length)

		for(Point pp : stack.getAll()) {
			data[pp.getY()][pp.getX()] = 1;
		}
	}

	public static boolean checkRow(int[][] data, int crow) {

		for (int c = 0; c < data.length; c++) {
			if (data[crow][c] == 1) {
				return false;
			}
		}

		return true;
	}

	public static boolean checkCol(int[][] data, int ccol) {

		for (int r = 0; r < data[0].length; r++) {
			if (data[r][ccol] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDiagSW(int[][] data, int x, int y) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		int cx = x;
		int cy = y;
		while ((cx < data[0].length) && (0 <= cy)) {
			cx++;
			cy--;
			if ((cx == data[0].length) || (cy < 0)) {
				break;
			}
			if (data[cy][cx] == 1) {
				return false;
			}
		}
		cx = x;
		cy = y;
		while ((0 <= cx) && (cy < data.length)) {
			cx--;
			cy++;
			if ((cx < 0) || (cy == data.length)) {
				break;
			}
			if (data[cy][cx] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDiagSE(int[][] data, int x, int y) {// x++, y++ or x--, y--
		int cx = x;
		int cy = y;
		while ((cx < data[0].length) && (cy < data.length)) {
			cx++;
			cy++;
			if ((cx == data[0].length) || (cy == data.length)) {
				break;
			}
			if (data[cy][cx] == 1) {
				return false;
			}

		}
		cx = x;
		cy = y;
		while ((0 <= cx) && (0 <= cy)) {
			cx--;
			cy--;
			if ((cx < 0) || (cy < 0)) {
				break;
			}
			if (data[cy][cx] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean CheckMove(int[][] data, int x, int y) {// (x,y)로 이동 가능한지를 check

		if ((data[y][x] == 0) && (checkRow(data, y)) && (checkCol(data, x))
				&& (checkDiagSW(data, x, y)) && (checkDiagSE(data, x, y)))
			return true;
		else
			return false;
	}

	public static boolean NextMove(int[][] data, int row, int col) {// 다음 row에 대하여 이동할 col을 조사
		int r = row + 1;
		for (int c = 0; c < data[0].length; c++) {
			if (CheckMove(data, r, c) == true) {
				return true;
			}
		}
		return false;
	}

	public static int NextMove(int[][] data, int row) {// row에 대하여 이동할수 있는 col을 조사
		int r = row;
		for (int c = 0; c < data[0].length; c++) {
			if (CheckMove(data, c, r) == true) {
				return c;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int count = 4;
		int row = count, col = count;
		int[][] data = new int[row][col];
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
