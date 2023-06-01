
package chapter06.heap;

import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);

	public int DeleteMax();
}

class Heap implements MaxHeap {
	// private int heapSize = 10;

	private int[] heap;
	private int n = 0; // current size of MaxHeap
	private int maxSize; // Maximum allowable size of MaxHeap

	private static final int CONSOLE_WIDTH = 100;
	private static final int CELL_COUNT = 16;
	private static final int CELL_CENTER = CELL_COUNT / 2;
	private static final int CELL_WIDTH = 8;

	public Heap(int maxSize) {
		this.maxSize = maxSize;

		this.heap = new int[maxSize + 1];
	}

	public void display() {
		System.out.println("-".repeat(CONSOLE_WIDTH));
		for (int i=1; i<=n; i++) {
			System.out.print(" " + heap[i]);
		}
		System.out.println();

		System.out.println("-".repeat(CONSOLE_WIDTH));

		for (int i=1; i<=n; i++) {
			int depth = getDepth(i);
			int rowStart = CELL_CENTER / (int)(Math.pow(2, depth - 1)) * CELL_WIDTH;
			if ((i == 1) || (i == (int)(Math.pow(2, depth - 1)))) {
				System.out.println();
			}

			System.out.print(" ".repeat((rowStart) / 2));
			if((i * 2) <= n)
				System.out.print(".".repeat((rowStart / 2) - 2));
			else
				System.out.print(" ".repeat((rowStart / 2) - 2));

			System.out.print(StringUtils.center(String.format("%s", heap[i]), 4));
			
			if((i * 2 + 1) <= n)
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
			while(1 < index) {
				index /= 2;
				depth++;
			}
			return depth;
		}
	}

	@Override
	public void Insert(int x) {
		int i;
		if (n == maxSize) {
			PrintHeapFull();
			return;
		}
		n++;
		for (i = n; i >= 1;) {
			if (i == 1)
				break; // at root
			if (x <= heap[i / 2]) // 넣을 자리의 부모보다 작은지 확인
				break;// 자바에서 generic array 사용 안됨
			// move from parent to i					
			heap[i] = heap[i / 2];	// 부모를 현재 자리로 옮기고
			i /= 2;		// 현재 자리를 부모 위치로 변경
		}
		heap[i] = x;

	}

	@Override
	public int DeleteMax() {
		int i, j;
		if (n == 0) {
			PrintHeapEmpty();
			
			return -1;	// -1이면 empty 이다
		}
		int x = heap[1];
		int k = heap[n];
		n--;

		for (i = 1, j = 2; j <= n;) {
			if (j < n)
				if (heap[j] < heap[j + 1])
					j++;
			// j points to the larger child
			if (k >= heap[j])
				break;
			heap[i] = heap[j];
			i = j;
			j *= 2;
		}
		heap[i] = k;
		return x;

	}

	private void PrintHeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void PrintHeapFull() {
		System.out.println("Heap Full");
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

public class HeapSort {
	
	public static void main(String[] args) {
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(100);
	    final int count = 12;
	    int[] x = new int[count+1];
	    int []sorted = new int[count];

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1:
			     for (int i = 0; i < count; i++) {
			         x[i] = (int)(Math.random() * 130);
						heap.Insert(x[i]);
			     }
				break;
			case 2:
				heap.display();
				break;
			case 3:
				for (int i = 1; i <= count; i++)
					sorted[i-1] = heap.DeleteMax();
				for(int num: sorted)
					System.out.print(" " + num);
				System.out.println();
				break;

			case 4:
				return;

			}
		} while (select < 5);

		return;
	}
}
