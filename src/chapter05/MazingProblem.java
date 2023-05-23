package chapter05;

import java.util.ArrayList;

import chapter04.GenericObjectStack;

enum Directions {
	N(0), NE(1), E(2), SE(3), S(4), SW(5), W(6), NW(7);

	private int index;

	Directions(int i) {
		index = i;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index % Directions.values().length;
	}
}

class Items { // 스택에 좌표와 방향을 저장함
	protected int r;
	protected int c;
	protected Directions dir;

	public Items(int r, int c, Directions dir) {
		this.r = r;
		this.c = c;
		this.dir = dir;
	}

	@Override
	public String toString() {
		return this.r + ", " + this.c + ", " + this.dir;
	}

}

class Offsets { // 진행 방향에 따라 더해줄 좌표
	protected int r;
	protected int c;

	public Offsets(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class MazingProblem {

	static Offsets[] moves = new Offsets[8];// static을 선언하는 이유를 알아야 한다

	static GenericObjectStack<Items> stack;

	public static void path(int[][] maze, int[][] mark, int goalr, int goalc) {

		mark[1][1] = 1;
		stack = new GenericObjectStack<Items>(maze.length * maze[0].length);
		Items temp = new Items(0, 0, Directions.N); // N :: 0
		temp.r = 1;
		temp.c = 1;
		temp.dir = Directions.E; // E:: 2
		mark[temp.r][temp.c] = 2; // 미로 찾기 궤적은 2로 표시
		stack.push(temp);

		// 알고리즘은 cpp 파일 참고
		while (!stack.isEmpty()) // stack not empty
		{
			Items tmp = stack.pop(); // unstack
			System.out.println(" pop(" + tmp + ")");
			// System.out.println("stack = " + stack);
			int r = tmp.r;
			int c = tmp.c;
			int d = tmp.dir.getIndex();
			mark[r][c] = 1; // backtracking 궤적은 1로 표시

			while (d < 8) // moves forward
			{
				int nextr = r + moves[d].r;
				int nextc = c + moves[d].c;

				if ((nextr < 0) || (maze.length <= nextr) ||
						(nextc < 0) || (maze[0].length <= nextc)) {
					//
					System.out.println(" " + Directions.values()[d] + " : border");
					d++; // try next direction
					continue;
				}

				if ((nextr == goalr) && (nextc == goalc)) { // reached exit

					temp.r = r;
					temp.c = c;
					temp.dir.setIndex(d + 1);
					Items pushItem = new Items(temp.r, temp.c, temp.dir);
					stack.push(pushItem); // stack it
					
					System.out.println(stack); // output path

					System.out.println("the term near the exit: " + r + " " + c);
					System.out.println("exit: " + goalr + " " + goalc);

					return;
				}

				if ((maze[nextr][nextc] == 0) && (mark[nextr][nextc] == 0)) { // new position
					System.out.println(" " + Directions.values()[d] + " : ok");
					mark[nextr][nextc] = 1;
					// push the old temp to the stack, but the direction changes.
					// Because the neighbor in the direction of d has been checked.
					temp.r = r;
					temp.c = c;
					temp.dir.setIndex(d + 1);
					Items pushItem = new Items(temp.r, temp.c, temp.dir);
					stack.push(pushItem); // stack it

					System.out.println(" push(" + temp + ")");
					// System.out.println("stack = " + stack);
					r = nextr;
					c = nextc;
					d = Directions.N.getIndex(); // moves to (g,h)
				} else {
					System.out.println(" " + Directions.values()[d] + " : wall");
					d++; // try next direction
				}
			}
			System.out.println(" no direction to go");
		}
		System.out.println("no path in maze ");
	}

	public static void main(String[] args) {
		int[][] maze = new int[14][17]; // 미로
		int[][] mark = new int[14][17]; // 내가 갔던곳
		int[][] stackData = new int[14][17]; // 내가 갔던곳

		// 출구에 도달하면 경로를 2로 출력
		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 } };

		// X, Y, 다음 진행 방향
		// 0, 0, SE
		// 1, 1, NE
		// 0, 3, E

		for (int ia = 0; ia < 8; ia++)
			moves[ia] = new Offsets(0, 0);// 배열에 offsets 객체를 치환해야 한다.

		//
		moves[0].r = -1;
		moves[0].c = 0;
		moves[1].r = -1;
		moves[1].c = 1;
		moves[2].r = 0;
		moves[2].c = 1;
		moves[3].r = 1;
		moves[3].c = 1;
		moves[4].r = 1;
		moves[4].c = 0;
		moves[5].r = 1;
		moves[5].c = -1;
		moves[6].r = 0;
		moves[6].c = -1;
		moves[7].r = -1;
		moves[7].c = -1;
		// Directions d;
		// d = Directions.N;
		// d = d + 1;//java는 지원안됨
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {

				if ((0 < i) && (0 < j) && (i <= 12) && (j <= 15)) {
					maze[i][j] = input[i - 1][j - 1];
					// mark[i][j] = input[i - 1][j - 1];
				} else {
					maze[i][j] = 1;
					// mark[i][j] = 1;
				}
				mark[i][j] = 0;
				stackData[i][j] = 0;

			}
		}
		System.out.println("maze[12,15]::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(maze[i][j] + " ");

			}
			System.out.println();
		}
		System.out.println("mark::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(mark[i][j] + " ");

			}
			System.out.println();
		}

		path(maze, mark, 12, 15);

		System.out.println("mark::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(mark[i][j] + " ");

			}
			System.out.println();
		}

		//
		ArrayList<Items> data = stack.getAll();

		for(Items d : data) {
			stackData[d.r][d.c] = 2;
		}

		System.out.println("stack::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(stackData[i][j] + " ");

			}
			System.out.println();
		}

		
	}
}
