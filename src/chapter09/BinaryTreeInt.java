package chapter09;

/*
 * 23.6.7 3회차 실습 코드
 */
import java.util.Random;
import java.util.Scanner;

//정수를 저정하는 이진트리 만들기 실습
class TreeNodeInt {
	TreeNodeInt LeftChild;
	int data;
	TreeNodeInt RightChild;
	int nodeIndex;

	public TreeNodeInt(int data) {
		this.data = data;
		LeftChild = RightChild = null;
	}

	public TreeNodeInt(int data, int nodeIndex) {
		this.data = data;
		LeftChild = RightChild = null;
		this.nodeIndex = nodeIndex;
	}
}

class TreeInt {
	private static int branchMode = 1;

	private static final int CONSOLE_WIDTH = 100;
	private static final int CELL_COUNT = 16;
	private static final int CELL_CENTER = CELL_COUNT / 2;
	private static final int CELL_WIDTH = 8;

	int maxNodeIndex = 15;
	int[] nodeArray = new int[32];

	TreeNodeInt root;

	TreeInt() {
		root = null;

		for (int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = 0;
		}
	}

	void resetNodeArray() {
			for (int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = 0;
		}
		resetNodeArray(root);
	}

	void resetNodeArray(TreeNodeInt n) {
		if (n == null)
			return;

		nodeArray[n.nodeIndex] = n.data;
		if (n.LeftChild != null)
			resetNodeArray(n.LeftChild);
		if (n.RightChild != null)
			resetNodeArray(n.RightChild);

	}

	TreeNodeInt inorderSucc(TreeNodeInt current) {
		TreeNodeInt temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		return temp;
	}

	boolean isLeafNode(TreeNodeInt current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNodeInt CurrentNode) {
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data + "(" + CurrentNode.nodeIndex + ")");
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNodeInt CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNodeInt CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	boolean insert(int x) {// binary search tree를 만드는 입력 => A + B * C을 tree로 만드는 방법: 입력 해결하는 알고리즘 작성 방법을
							// 설계하여 구현
		int nodeIndex = 1;
		TreeNodeInt p = root;
		if (p == null) {
			root = new TreeNodeInt(x, nodeIndex);
			this.nodeArray[nodeIndex] = x;
			return true;
		}

		TreeNodeInt q = null;

		while (p != null) {
			// p를 바꾸기 전에 q 를 사용해야 한다.
			q = p;
			if (x < p.data) {
				nodeIndex = nodeIndex * 2;
				p = p.LeftChild;

			} else if (p.data < x) {
				nodeIndex = nodeIndex * 2 + 1;
				p = p.RightChild;

			} else {
				// 같은 데이터가 있는 경우
				return false;
			}

		}

		// if branchMode == 1 이면 왼쪽에, 2 이면 오른쪽에 넣어라
		if (x < q.data) {
			q.LeftChild = new TreeNodeInt(x, nodeIndex);
			this.nodeArray[nodeIndex] = x;
		} else if (q.data < x) {
			q.RightChild = new TreeNodeInt(x, nodeIndex);
			this.nodeArray[nodeIndex] = x;
		} else {
			// 같은 데이터가 있는 경우
			return false;
		}

		return true;

	}

	boolean delete(int num) {
		TreeNodeInt p = root;
		if (p == null) {

			return false;
		}

		TreeNodeInt q = null;

		while (p != null) {

			if (num < p.data) {
				q = p;
				p = p.LeftChild;

			} else if (p.data < num) {
				q = p;
				p = p.RightChild;

			} else {
				// 데이터를 찾은 경우
				if ((p.LeftChild == null) && (p.RightChild == null)) {
					// 리프 노드
					nodeArray[p.nodeIndex] = 0;
					p = null;
				} else if ((p.LeftChild != null) && (p.RightChild != null)) {
					// 차일드가 2개인 경우
					// TreeNodeInt insuc = inorderSucc(p);
					TreeNodeInt insuc = p.RightChild;
					TreeNodeInt insucParent = p;
					while (insuc.LeftChild != null) {
						insucParent = insuc;
						insuc = insuc.LeftChild;
					}

					p.data = insuc.data; // insuc 이 right child 이고 insuc 이 right child 를 가지고 있을때 문제 생김.
					// 지정한 트리 생성 후 루트만 2번 삭제시 문제 발생.

					nodeArray[p.nodeIndex] = nodeArray[insuc.nodeIndex];
					nodeArray[insuc.nodeIndex] = 0;
					if (insuc == insucParent.LeftChild) {
						insucParent.LeftChild = null;
					} else {
						insucParent.RightChild = null;
					}
					insuc = null;

				} else {
					// 차일드가 1개인 경우
					if (p.LeftChild != null) {
						// left 차일드가 있는 경우
						nodeArray[p.nodeIndex] = nodeArray[p.LeftChild.nodeIndex];
						nodeArray[p.LeftChild.nodeIndex] = 0;
						p.LeftChild.nodeIndex = p.nodeIndex;
						if (q != null) {
							// 부모가 null 이 아니면, (루트가 아니면)
							if (p == q.LeftChild) {
								// 자신이 부모의 left 차일드인 경우
								q.LeftChild = p.LeftChild;
							} else {
								// 자신이 부모의 right 차일드인 경우
								q.RightChild = p.LeftChild;
							}
						} else {
							// 삭제할 노드가 루트이면
							root = p.LeftChild;
						}

					} else {
						// right 차일드가 있는 경우
						nodeArray[p.nodeIndex] = nodeArray[p.RightChild.nodeIndex];
						nodeArray[p.RightChild.nodeIndex] = 0;
						p.RightChild.nodeIndex = p.nodeIndex;
						if (q != null) {
							// 부모가 null 이 아니면, (루트가 아니면)
							if (p == q.LeftChild) {
								// 자신이 부모의 left 차일드인 경우
								q.LeftChild = p.RightChild;
							} else {
								// 자신이 부모의 right 차일드인 경우
								q.RightChild = p.RightChild;
							}
						} else {
							// 삭제할 노드가 루트이면
							root = p.RightChild;
						}
					}

					p = null;
				} // end of 차일드가 1개인 경우

				return true;

			} // end of 데이터를 찾은 경우

		} // while (p != null)

		return false;
	}

	boolean search(int num) {

		TreeNodeInt p = root;
		if (p == null) {

			return false;
		}

		while (p != null) {

			if (num < p.data) {
				p = p.LeftChild;

			} else if (p.data < num) {
				p = p.RightChild;

			} else {
				// 데이터가 있는 경우
				return true;
			}

		}

		return false;
	}

	public void display() {
		resetNodeArray();

		System.out.println("-".repeat(CONSOLE_WIDTH));
		for (int i = 1; i <= maxNodeIndex; i++) {
			System.out.print(" " + nodeArray[i]);
		}
		System.out.println();

		System.out.println("-".repeat(CONSOLE_WIDTH));

		for (int i = 1; i <= maxNodeIndex; i++) {
			int depth = getDepth(i);
			int rowStart = CELL_CENTER / (int) (Math.pow(2, depth - 1)) * CELL_WIDTH;
			if ((i == 1) || (i == (int) (Math.pow(2, depth - 1)))) {
				System.out.println();
			}

			System.out.print(" ".repeat((rowStart) / 2));
			if (((i * 2) <= maxNodeIndex) && (0 < nodeArray[i * 2]))
				System.out.print(".".repeat((rowStart / 2) - 2));
			else
				System.out.print(" ".repeat((rowStart / 2) - 2));

			if (0 < nodeArray[i]) {
				System.out.print(StringUtils.center(String.format("%s", nodeArray[i]), 4));
			} else {
				System.out.print(" ".repeat(4));
			}

			if (((i * 2 + 1) <= maxNodeIndex) && (0 < nodeArray[i * 2 + 1]))
				System.out.print(".".repeat((rowStart / 2) - 2));
			else
				System.out.print(" ".repeat((rowStart / 2) - 2));
			System.out.print(" ".repeat((rowStart) / 2));

		}
		System.out.println();

	}

	private int getDepth(int nodeIndex) {

		if (nodeIndex == 0) {
			return 0;
		} else if (nodeIndex == 1) {
			return 1;
		} else {
			int depth = 1;
			int index = nodeIndex;
			while (1 < index) {
				index /= 2;
				depth++;
			}
			return depth;
		}
	}

}

class StringUtils {

	public static String center(String s, int size) {
		return center(s, size, ' ');
	}

	public static String center(String s, int size, char pad) {
		if (s == null || size <= s.length())
			return s;

		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < (size - s.length()) / 2; i++) {
			sb.append(pad);
		}
		sb.append(s);
		while (sb.length() < size) {
			sb.append(pad);
		}
		return sb.toString();
	}
}

public class BinaryTreeInt {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("순차출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		Scanner stdIn = new Scanner(System.in);
		TreeInt t = new TreeInt();
		Menu menu; // 메뉴
		int count = 0;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
				case Add: // 노드 삽입
					System.out.println("The number of items = ");
					count = stdIn.nextInt();
					if (0 < count) {
						int[] input = new int[10];
						for (int ix = 0; ix < count; ix++) {
							input[ix] = rand.nextInt(20) + 1;
						}
						for (int i = 0; i < count; i++) {
							if (t.insert(input[i]) == false)
								System.out.println("Insert Duplicated data");

							t.display();
						}
					} else {
						//
						int[] arrData = { 20, 10, 30, 25, 40, 5 };
						for (int i = 0; i < arrData.length; i++) {
							boolean ret = t.insert(arrData[i]);
							if (ret == false) {
								System.out.println("Insert Duplicated data");
							}
							t.display();
						}
					}

					break;

				case Delete: // 노드 삭제
					System.out.println("삭제할 데이터:: ");
					num = stdIn.nextInt();
					if (t.delete(num) == true)
						System.out.println("데이터(" + num + ") 삭제 성공");
					else
						System.out.println("데이터(" + num + ") 삭제 실패");
					;
					t.display();
					break;

				case Search: // 노드 검색
					System.out.println("검색할 데이터:: ");

					num = stdIn.nextInt();
					result = t.search(num);
					if (result == true)
						System.out.println("해당 데이터(" + num + ")가 존재합니다.");
					else
						System.out.println("해당 데이터(" + num + ")가 없습니다.");
					break;

				case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
					t.inorder();
					System.out.println();

					t.display();
					break;
			}
		} while (menu != Menu.Exit);
	}
}
