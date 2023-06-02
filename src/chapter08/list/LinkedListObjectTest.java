package chapter08.list;

/*
 * 정수 리스트 > 객체 리스트: 2번째 실습 대상 
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

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		// sw 가 1인 경우는 NO만 실행, 2인 경우는 NAME만 실행, 3인 경우는 NO와 NAME 둘다 실행.
		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	@Override
	public boolean equals(Object obj) {
		SimpleObject so = (SimpleObject)obj;

		// if (this.no.equals(so.no) && this.name.equals(so.name))
		if ((Integer.parseInt(this.no) - Integer.parseInt(so.no) == 0) && this.name.equals(so.name))
			return true;
		else
			return false;

	}

	public boolean equalsNo(Object obj) {
		SimpleObject so = (SimpleObject)obj;

		// if (this.no.equals(so.no) && this.name.equals(so.name))
		if (Integer.parseInt(this.no) - Integer.parseInt(so.no) == 0)
			return true;
		else
			return false;

	}

	public boolean equalsName(Object obj) {
		SimpleObject so = (SimpleObject)obj;

		// if (this.no.equals(so.no) && this.name.equals(so.name))
		if (this.name.equals(so.name))
			return true;
		else
			return false;

	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			// return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
			return Integer.parseInt(d1.no) - Integer.parseInt(d2.no);
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.name.compareTo(d2.name);
		}
	}
} // class SimpleObject

class NodeObject {
	SimpleObject data;
	NodeObject link;

	public NodeObject(SimpleObject element) {
		data = element;
		link = null;
	}

	@Override
	public String toString() {
		return data.toString();
	}
} // class NodeObject

class LinkedListObject {
	NodeObject first;

	public LinkedListObject() {
		first = null;
	}

	public NodeObject Delete(SimpleObject element, Comparator<SimpleObject> cc) // delete the element
	{
		NodeObject delNode = null;
		NodeObject q = null;
		NodeObject p = first;
		boolean found = false;
		while (p != null) {
			
			if ((cc == null) &&(p.data.equals(element))) { 
				found = true;
			} else if ((cc == SimpleObject.NO_ORDER) && (p.data.equalsNo(element))) { 
				found = true;
			} else if ((cc == SimpleObject.NAME_ORDER) && (p.data.equalsName(element))) { 
				found = true;
			}

			if (found) {
				delNode = new NodeObject(p.data);

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
		NodeObject p = first;

		while (p != null) {
			System.out.print(" " + p);
			p = p.link;
		}
		System.out.println();

	}

	public void Add(SimpleObject element, Comparator<SimpleObject> cc) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		NodeObject nd = new NodeObject(element);
		NodeObject p = this.first;
		NodeObject q = null;
		if (p == null) {
			first = nd;
			return;
		}

		while (p != null) {
			if (cc.compare(element, p.data) < 0) {
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
			} else if (0 <= cc.compare(element, p.data)) {
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

	public boolean Search(SimpleObject element, Comparator<SimpleObject> cc) { // 
		NodeObject p = first;
		while (p != null) {
			if ((cc == null) &&(p.data.equals(element))) { 
				return true;
			} else if ((cc == SimpleObject.NO_ORDER) && (p.data.equalsNo(element))) { 
				return true;
			} else if ((cc == SimpleObject.NAME_ORDER) && (p.data.equalsName(element))) { 
				return true;
			}
			p = p.link;
		}

		return false;
	}
} // class LinkedListObject

public class LinkedListObjectTest {

	enum Menu {
		Add("삽입"),
		Delete("삭제"),
		Show("인쇄"),
		Search("검색"),
		Exit("종료");

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
				if ((m.ordinal() % 3) == 2 &&
						m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() ||
				key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		System.out.println("Linked List");
		LinkedListObject l = new LinkedListObject();
		Scanner sc = new Scanner(System.in);
		SimpleObject data;
		// System.out.println("inserted");
		// l.Show();
		do {
			switch (menu = SelectMenu()) {
				case Add: // 삽입
					data = new SimpleObject();
					data.scanData("입력", 3);
					l.Add(data, SimpleObject.NO_ORDER);
					break;
				case Delete: // 삭제
					data = new SimpleObject();
					data.scanData("삭제", SimpleObject.NO);
					NodeObject num = l.Delete(data, SimpleObject.NO_ORDER);
					System.out.println("삭제된 데이터 성공은 " + num);
					break;
				case Show: // 
					l.Show();
					break;
				case Search: // 검색
					data = new SimpleObject();
					data.scanData("탐색", SimpleObject.NO);
					boolean result = l.Search(data, SimpleObject.NO_ORDER);
					if (result == true)
						System.out.println("검색 성공 = " + result);
					else
						System.out.println("검색 실패 = " + result);
					break;
				case Exit: // 
					break;
			}
		} while (menu != Menu.Exit);

		sc.close();
	} // public static void main(String[] args)
} // public class LinkedListObjectTest
