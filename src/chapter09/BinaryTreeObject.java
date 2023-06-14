package chapter09;

/*
 * 23.6.7 3회차 실습코드 
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject() {
		no = null;
		name = null;
	}

	public SimpleObject(String no, String name) {
		this.no = no;
		this.name = name;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

// 객체를 저정하는 이진트리 만들기 실습
class TreeNodeObject {
	TreeNodeObject LeftChild;
	SimpleObject data;
	TreeNodeObject RightChild;

	public TreeNodeObject() {
		LeftChild = RightChild = null;
	}

	public TreeNodeObject(SimpleObject so) {
		data = so;
		LeftChild = RightChild = null;
	}
}

class TreeObject {
	private static int branchMode = 1;

	private static final int CONSOLE_WIDTH = 100;
	private static final int CELL_COUNT = 16;
	private static final int CELL_CENTER = CELL_COUNT / 2;
	private static final int CELL_WIDTH = 8;

	int maxNodeIndex = 15;
	SimpleObject[] nodeArray = new SimpleObject[32];

	TreeNodeObject root;

	TreeObject() {
		root = null;
	}

	void resetNodeArray() {
		for (int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = null;
		}
		resetNodeArray(root, 1);
	}

	void resetNodeArray(TreeNodeObject n, int nodeIndex) {
		if (n == null)
			return;

		nodeArray[nodeIndex] = n.data;
		if (n.LeftChild != null)
			resetNodeArray(n.LeftChild, nodeIndex * 2);
		if (n.RightChild != null)
			resetNodeArray(n.RightChild, nodeIndex * 2 + 1);

	}

	TreeNodeObject inorderSucc(TreeNodeObject current) {
		TreeNodeObject temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null) {
				temp = temp.LeftChild;
			}
		return temp;
	}

	TreeNodeObject findParent(TreeNodeObject current, Comparator<? super SimpleObject> c) {
		TreeNodeObject p = root, temp = null;
		while (p != null) {
			if (c.compare(p.data, current.data) == 0) {
				return temp;
			} else if (c.compare(p.data, current.data) < 0) {
				temp = p;
				p = p.RightChild;
			} else {
				temp = p;
				p = p.LeftChild;
			}
		}
		return null;
	}

	boolean isLeafNode(TreeNodeObject current) {
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

	void inorder(TreeNodeObject CurrentNode) {
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNodeObject CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNodeObject CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	public boolean insert(SimpleObject obj, Comparator<? super SimpleObject> c) {
		// 설계하여 구현
		int nodeIndex = 1;
		TreeNodeObject p = root;
		if (p == null) {
			root = new TreeNodeObject(obj);
			this.nodeArray[nodeIndex] = obj;
			return true;
		}

		TreeNodeObject q = null;

		while (p != null) {
			// p를 바꾸기 전에 q 를 사용해야 한다.
			q = p;
			if (c.compare(obj, p.data) < 0) {
				nodeIndex = nodeIndex * 2;
				p = p.LeftChild;

			} else if (c.compare(obj, p.data) > 0) {
				nodeIndex = nodeIndex * 2 + 1;
				p = p.RightChild;

			} else {
				// 같은 데이터가 있는 경우
				return false;
			}

		}

		// if branchMode == 1 이면 왼쪽에, 2 이면 오른쪽에 넣어라
		if (c.compare(obj, q.data) < 0) {
			q.LeftChild = new TreeNodeObject(obj);
			this.nodeArray[nodeIndex] = obj;
		} else if (c.compare(obj, q.data) > 0) {
			q.RightChild = new TreeNodeObject(obj);
			this.nodeArray[nodeIndex] = obj;
		} else {
			// 같은 데이터가 있는 경우
			return false;
		}

		return true;

	}

	public boolean delete(SimpleObject obj, Comparator<? super SimpleObject> c) {
		TreeNodeObject p = root;
		if (p == null) {

			return false;
		}

		TreeNodeObject q = null;

		while (p != null) {

			if (c.compare(obj, p.data) < 0) {
				q = p;
				p = p.LeftChild;

			} else if (c.compare(obj, p.data) > 0) {
				q = p;
				p = p.RightChild;

			} else {
				// 데이터를 찾은 경우
				if ((p.LeftChild == null) && (p.RightChild == null)) {
					// 리프 노드
					if (p == root)
						root = null;
					else {
						if (q.LeftChild == p)
							q.LeftChild = null;
						else
							q.RightChild = null;
					}
					p = null;

				} else if ((p.LeftChild != null) && (p.RightChild != null)) {
					// 차일드가 2개인 경우
					// TreeNodeObject insuc = inorderSucc(p);
					TreeNodeObject insuc = p.RightChild;
					TreeNodeObject insucParent = p;
					while (insuc.LeftChild != null) {
						insucParent = insuc;
						insuc = insuc.LeftChild;
					}

					p.data = insuc.data;
					// insuc 이 right child 이고 insuc 이 right child 를 가지고 있을때 문제 생김.
					// 지정한 트리 생성 후 루트만 2번 삭제시 문제 발생.
					if ((insuc == insucParent.RightChild) && (insuc.RightChild != null)) {
						p.RightChild = insuc.RightChild;
					} else {
						if (insuc == insucParent.LeftChild) {
							insucParent.LeftChild = null;
						} else {
							insucParent.RightChild = null;
						}
					}
					insuc = null;

				} else {
					// 차일드가 1개인 경우
					if (p.LeftChild != null) {
						// left 차일드가 있는 경우
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

	boolean search(SimpleObject obj, Comparator<? super SimpleObject> c) {
		TreeNodeObject p = root;
		if (p == null) {

			return false;
		}

		while (p != null) {

			if (c.compare(obj, p.data) < 0) {
				p = p.LeftChild;

			} else if (c.compare(obj, p.data) > 0) {
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
			if (((i * 2) <= maxNodeIndex) && (null != nodeArray[i * 2]))
				System.out.print(".".repeat((rowStart / 2) - 2));
			else
				System.out.print(" ".repeat((rowStart / 2) - 2));

			if (null != nodeArray[i]) {
				System.out.print(StringUtils.center(String.format("%s", nodeArray[i]), 4));
			} else {
				System.out.print(" ".repeat(4));
			}

			if (((i * 2 + 1) <= maxNodeIndex) && (null != nodeArray[i * 2 + 1]))
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

public class BinaryTreeObject {

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬인쇄"), Exit("종료");

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
		Scanner sc2 = new Scanner(System.in);
		TreeObject t = new TreeObject();
		Menu menu; // 메뉴
		String sno1, sname1;
		SimpleObject so;
		int count = 0;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
				case Add: // 머리노드 삽입
					SimpleObject[] sox = { new SimpleObject("33", "ee"), new SimpleObject("55", "tt"),
							new SimpleObject("22", "ww"), new SimpleObject("66", "yy"), new SimpleObject("21", "wq") };
					for (SimpleObject soz : sox) {
						t.insert(soz, SimpleObject.NO_ORDER);
						t.display();
					}
					break;
				case Delete: // 머리 노드 삭제
					so = new SimpleObject();
					so.scanData("삭제", SimpleObject.NO);
					t.delete(so, SimpleObject.NO_ORDER);
					t.display();
					break;
				case Search: // 회원 번호 검색
					so = new SimpleObject();
					so.scanData("검색", SimpleObject.NO);
					result = t.search(so, SimpleObject.NO_ORDER);
					if (result == false)
						System.out.println("검색 값 = " + so + "데이터가 없습니다.");
					else
						System.out.println("검색 값 = " + so + "데이터가 존재합니다.");
					break;

				case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
					t.inorder();
					System.out.println();
					break;
				case Exit:
					break;
			}
		} while (menu != Menu.Exit);
	}
}
