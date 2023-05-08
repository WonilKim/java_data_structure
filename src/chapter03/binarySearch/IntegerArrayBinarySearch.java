package chapter03.binarySearch;

//3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
//comparator 구현 실습
import java.util.Arrays;
import java.util.Random;

public class IntegerArrayBinarySearch {

	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);
		showData(data);
		sortData(data);
		System.out.println();
		for (int num : data) {
			System.out.print(num + " ");
		}
		int key = data[5];
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);

		key = data[7];
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + result);

	}

	private static int binarySearch(int[] data, int key) {

		int pl = 0;
		int n = data.length;
		int pr = n - 1;

		do {
			int pc = (pl + pr) / 2;
			if(data[pc] == key)
				return pc;
			else if(data[pc] < key)
				pl = pc + 1;
			else
				pr = pc - 1;

		} while(pl <= pr);

		return -1;
	}

	private static int linearSearch(int[] data, int key) {
		int i = 0;

		while (i < data.length) {
			if (data[i] == key) {
				return i;
			}
			i++;
		}
		return -1;

	}

	private static void sortData(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			// System.out.println("i = " + i);
			for (int j = i + 1; j < data.length; j++) {
				// System.out.println(" j = " + j);
				if (data[i] > data[j]) {
					swap(data, i, j);
				}
			}
		}

	}

	private static void showData(int[] data) {
		for (int i = 0; i < data.length; i++) {
			// System.out.println("data[" + i + "] = " + data[i]);
			System.out.print(data[i] + "  ");
		}
		System.out.print("\n");
	}

	private static void inputData(int[] data) {
		Random r = new Random(System.currentTimeMillis());

		for (int i = 0; i < data.length; i++) {
			data[i] = r.nextInt(100);
		}

	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

	}

}
