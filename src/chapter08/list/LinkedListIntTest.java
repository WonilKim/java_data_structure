package chapter08.list;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class NodeInt {
	int data;
	NodeInt link;

	public NodeInt(int element) {
		data = element;
		link = null;
	}

	@Override
	public String toString() {
		return String.format("%d", this.data);
	}
}

class LinkedListInt {
	NodeInt first;

	public LinkedListInt() {
		first = null;
	}

	public NodeInt Delete(int element) // delete the element
	{
		NodeInt delNode = null;
		NodeInt q = null;
		NodeInt p = first;
		while (p != null) {
			if (p.data == element) {
				delNode = new NodeInt(p.data);

				if (p == first) {
					first = first.link;
					break;
				} else if (p.link == null) {
					q.link = null;
					p = null;
					break;
				} else {
					q.link = p.link;
					p = null;
					break;
				}
			}
			q = p;
			p = p.link;
		}

		return delNode;
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		NodeInt p = first;

		while (p != null) {
			System.out.print(" " + p);
			p = p.link;
		}
		System.out.println();
	}

	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		NodeInt nd = new NodeInt(element);
		NodeInt p = this.first;
		NodeInt q = null;
		if (p == null) {
			first = nd;
			return;
		}

		while (p != null) {
			if (element < p.data) {
				if (p == first) {
					// first 보다 작으면
					nd.link = p;
					first = nd;
					return;
				} else {
					// first 가 아닌 것 보다 작으면
					nd.link = p;
					q.link = nd;
					return;
				}
			} else if (p.data <= element) {
				q = p;
				p = p.link;
				if (p == null) {
					// 마지막 노드
					p = nd;
					q.link = p;
					return;
				}
			}
		}
	}

	public boolean Search(int element) { // 전체 리스트를 순서대로 출력한다.

		NodeInt p = first;
		while (p != null) {
			if (p.data == element) {
				return true;
			}
			p = p.link;
		}

		return false;
	}

} // class LinkedListInt

public class LinkedListIntTest {

	enum Menu {

		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

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

	} // enum Menu

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		Random rand = new Random();
		System.out.println("Linked List");
		LinkedListInt l = new LinkedListInt();
		Scanner sc = new Scanner(System.in);
		int data = 0;
		int[] arrData = { 10, 20, 30, 5, 25, 40, 15, 3, 32, 8, 55, 11, 75 };
		int index = 0;
		// System.out.println("inserted");
		// l.Show();
		do {
			switch (menu = SelectMenu()) {
				case Add: // 노드 삽입

					// 지정한 배열 먼저 삽입 후 임의의 정수 삽입
					if (index < arrData.length)
						l.Add(arrData[index++]);
					else
						l.Add(rand.nextInt(100));

					break;
				case Delete: // 노드 삭제
					System.out.println("삭제할 데이터를 입력하세요.");
					data = sc.nextInt();
					NodeInt num = l.Delete(data);
					if (num == null)
						System.out.println("데이터가 없습니다.");
					else
						System.out.println("삭제된 데이터는 " + num.data);
					break;
				case Show: // 리스트 출력
					l.Show();
					break;
				case Search: // 노드 검색
					int n = sc.nextInt();
					boolean result = l.Search(n);
					if (result == false)
						System.out.println("검색 값 = " + n + "데이터가 없습니다.");
					else
						System.out.println("검색 값 = " + n + "데이터가 존재합니다.");
					break;
				case Exit: // 종료
					break;
			}
		} while (menu != Menu.Exit);

		sc.close();

	} // public static void main(String[] args)

} // public class LinkedListIntTest
